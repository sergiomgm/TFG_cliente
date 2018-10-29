package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl;

import com.eduardosergio.TFG_cliente.seguridad.ClientPasswordCallback;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class Delegado_EmpleadoImpl extends Delegado_Empleado {

    private static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImpl.class);

    private IBroker_SA_Empleado portEmpleados;

    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";


    private final String URL_WSDL = "http://localhost:8080/TFG_server/wsdl/SA_Empleado.wsdl";

    private final String URL_SERVICE = HOST + ":"+ PORT + APP_URI + "/SA_Empleado";


    private final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Empleado.Negocio.TFG_server.rodrigo.com/";

    private final String SERVICE_NAME = "Broker_SA_EmpleadoImpl";


    public Delegado_EmpleadoImpl() throws ProxyException {
    	/*
        log.info("Creando Delegado_EmpleadoImpl");

        log.info("Creando Qname del servicio");
        QName SERVICE_EMPLEADO = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.info("Creando URL_WSDL de enlace");
        log.info("URL_WSDL: " + URL_WSDL);
        URL wsdlURLEmpleados;
        try {

            wsdlURLEmpleados = new URL(URL_WSDL);
            log.info("wsdlURLEmpleados = '" + wsdlURLEmpleados + "'");
        } catch (MalformedURLException e) {
            log.error("Error al crear el WSDL", e);

            throw new ProxyException("Error al conectar con servicio Empleado");
        }


        log.info("Creando servicio Empleado");
        Service ssEmpleados = Service.create(wsdlURLEmpleados, SERVICE_EMPLEADO);


        log.info("Creando puerto de enlace para el servicio");
        portEmpleados =  ssEmpleados.getPort(IBroker_SA_Empleado.class);



        log.info("Asignando usuario y contraseña");
        //
        Map<String, Object> req_ctx2= ((BindingProvider) portEmpleados).getRequestContext();
        req_ctx2.put(BindingProvider.USERNAME_PROPERTY, "usuario");
        req_ctx2.put(BindingProvider.PASSWORD_PROPERTY, "contra");

        req_ctx2.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_SERVICE);
        */	
	    
    	
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:cxf.xml");
        
        portEmpleados = (IBroker_SA_Empleado) context.getBean("SA_Empleado");
        
        ((BindingProvider) portEmpleados).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "usuario");
        ((BindingProvider) portEmpleados).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "contra");
        
        ((BindingProvider) portEmpleados).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_SERVICE);

/*
    	 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        
	        factory.setAddress(URL_SERVICE);
	        
	        factory.setServiceClass(IBroker_SA_Empleado.class);
	        
	        Object client = factory.create();
	        
	        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
	        loggingOutInterceptor.setPrettyLogging(true);
	        ClientProxy.getClient(client).getOutInterceptors().add(loggingOutInterceptor);
	        
	        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
	        loggingInInterceptor.setPrettyLogging(true);
	        ClientProxy.getClient(client).getInInterceptors().add(loggingInInterceptor);
	        
	        //Configuracion encriptacion de salida WS-Security
	        Map<String, Object> props = new HashMap<String, Object>();
	        props.put(WSHandlerConstants.USER, "testkey");
	        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
	        props.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
	        props.put(WSHandlerConstants.ENC_PROP_FILE, "clientkeystore.properties");
	        props.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
	        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());

	        WSS4JOutInterceptor wss4jOut = new WSS4JOutInterceptor(props);
	        ClientProxy.getClient(client).getOutInterceptors().add(wss4jOut);
	        
	        //Configuracion encriptacion de entrada WS-Security
	        Map<String, Object> inProps = new HashMap<String, Object>();
	        inProps.put(WSHandlerConstants.USER, "clienttestkey");
	        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
	        inProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
	        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "clientkeystore.properties"); 
	        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "clientkeystore.properties");
	        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());
	        
	        
	        WSS4JInInterceptor wss4jIn= new WSS4JInInterceptor(inProps);
	        ClientProxy.getClient(client).getInInterceptors().add(wss4jIn);
	   		
	        portEmpleados = (IBroker_SA_Empleado) client;
	        */

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TEmpleadoCompleto crearEmpleado(TEmpleado empleadoNuevo) throws EmpleadoYaExisteExcepcion, EmpleadoFieldInvalidException, EmpleadoException{
        return portEmpleados.crearEmpleado(empleadoNuevo);
    }

    @Override
    public TEmpleadoCompleto buscarByID(Long id) throws EmpleadoException {
        return portEmpleados.buscarByID(id);
    }

    @Override
    public boolean eliminarEmpleado(Long id) throws EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.eliminarEmpleado(id);
    }

    @Override
    public List<TEmpleado> listarEmpleados() {
        return portEmpleados.listarEmpleados();
    }


    @Override
    public TEmpleadoCompleto buscarByEmail(String email) throws EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.buscarByEmail(email);
    }


}

package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
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

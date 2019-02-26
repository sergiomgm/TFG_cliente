package com.eduardosergio.TFG_cliente.negocio.holaMundo.Delegado.impl;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.Delegado_HelloWorld;
import com.eduardosergio.TFG_cliente.negocio.helloWorld.Serv_aplicacion.HelloWorld;
import com.eduardosergio.TFG_cliente.negocio.holaMundo.Delegado.Delegado_HolaMundo;
import com.eduardosergio.TFG_cliente.negocio.holaMundo.Serv_aplicacion.HolaMundo;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;

public class Delegado_HolaMundoImpl extends Delegado_HolaMundo {

    private static Logger log = LoggerFactory.getLogger(Delegado_HolaMundoImpl.class);

    private HolaMundo portHola;

    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";


    private final String URL_WSDL = "http://localhost:8080/TFG_server/wsdl/holamundo.wsdl";

    private final String URL_SERVICE = HOST + ":"+ PORT + APP_URI + "/holamundo";


    private final String NAMESPACE_URI = "http://impl.holaMundo.negocio.TFG_server.eduardosergio.com/";

    private final String SERVICE_NAME = "HolaMUndoService";


    public Delegado_HolaMundoImpl() throws ProxyException {
        log.info("Creando Delegado_HelloWorld");
    	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cxf.xml");
		
		portHola = (HolaMundo) context.getBean("HolaMundo");
		
		((BindingProvider) portHola).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "usuario");
		((BindingProvider) portHola).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "contra");
		
		((BindingProvider) portHola).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_SERVICE);

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public String saludar() {
    	return portHola.saludar();
    }

}

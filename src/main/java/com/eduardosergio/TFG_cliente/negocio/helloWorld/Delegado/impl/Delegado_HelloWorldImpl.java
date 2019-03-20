package com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.impl;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.Delegado_HelloWorld;
import com.eduardosergio.TFG_cliente.negocio.helloWorld.Serv_aplicacion.HelloWorld;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;

public class Delegado_HelloWorldImpl extends Delegado_HelloWorld {

    private static Logger log = LoggerFactory.getLogger(Delegado_HelloWorldImpl.class);

    private HelloWorld portHello;

    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";


    private final String URL_WSDL = "http://localhost:8080/TFG_server/wsdl/helloWorld.wsdl";

    private final String URL_SERVICE = HOST + ":"+ PORT + APP_URI + "/helloworld";


    private final String NAMESPACE_URI = "http://impl.helloWorld.negocio.TFG_server.eduardosergio.com/";

    private final String SERVICE_NAME = "HelloWorldService";


    public Delegado_HelloWorldImpl() throws ProxyException {
        log.info("Creando Delegado_HelloWorld");
    	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cxf.xml");
		
		portHello = (HelloWorld) context.getBean("HelloWorld");
		

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public String salute() {
    	return portHello.salute();
    }

}

package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.impl;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Serv_aplicacion.HelloWorld;
import com.eduardosergio.TFG_cliente.negocio.seguridad.departamento.credentialTokenizer.CredentialTokenizer;
import com.eduardosergio.TFG_cliente.negocio.seguridad.departamento.credentialTokenizer.UserToken;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Serv_aplicacion.CiaoMondo;
import com.eduardosergio.TFG_cliente.negocio.holaMundo.Serv_aplicacion.HolaMundo;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Authenticator;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SSODelegatorImpl extends SSODelegator {

    private static Logger log = LoggerFactory.getLogger(SSODelegatorImpl.class);
    
    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";

    private final String SERVICE_NAME = "/SA_Departamento/departamento";

    private final String URL_SERVICIO = HOST + ":" + PORT + APP_URI + SERVICE_NAME;

    private Client cliente;

    private HelloWorld portHello;

    private HolaMundo portHola;
    
    private CiaoMondo portCiao;

    public SSODelegatorImpl() throws ProxyException {
        log.info("Creando SSODelegator");
        
        Service service;
		try {
			service = Service.create(new URL("https://localhost:8443/TFG_server/services/helloworld?wsdl"), new QName("http://impl.helloWorld.negocio.TFG_server.eduardosergio.com/", "HelloWorldImplService"));

			portHello = service.getPort(new QName("http://impl.helloWorld.negocio.TFG_server.eduardosergio.com/", "HelloWorldImplPort"), HelloWorld.class);
			
			service = Service.create(new URL("https://localhost:8443/TFG_server/services/holamundo?wsdl"), new QName("http://impl.holaMundo.negocio.TFG_server.eduardosergio.com/", "HolaMundoImplService"));

			portHola = service.getPort(new QName("http://impl.holaMundo.negocio.TFG_server.eduardosergio.com/", "HolaMundoImplPort"), HolaMundo.class);
			
			service = Service.create(new URL("https://localhost:8443/TFG_server/services/ciaomondo?wsdl"), new QName("http://impl.ciaoMondo.negocio.TFG_server.eduardosergio.com/", "CiaoMondoImplService"));

			portCiao = service.getPort(new QName("http://impl.ciaoMondo.negocio.TFG_server.eduardosergio.com/", "CiaoMondoImplPort"), CiaoMondo.class);

			UserToken credenciales = new CredentialTokenizer().getUserToken();
	        
	        cliente = ClientBuilder
	                .newBuilder()
	                .newClient()
	                .register(new Authenticator(credenciales.getUser(), credenciales.getPassword()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

        log.info("SSODelegator creado");
    }


    @Override
    public String salute() {
    	return portHello.salute();
    }
    
    @Override
    public String saludar() {
    	return portHola.saludar();
    }
    
    @Override
    public String salutare() {
    	return portCiao.saludar();
    }
    
    @Override
    public TDepartamento[] listarDepartamentos() {

        String urlFinal = URL_SERVICIO + "/listar";

        System.out.println("urlFinal = [" + urlFinal + "]");

        TDepartamento[] res = cliente
                .target(urlFinal)
                .path("")
                .request()
                .get(TDepartamento[].class);
    	
        System.out.println("res = [" + res + "]");

        return res;
    }
}

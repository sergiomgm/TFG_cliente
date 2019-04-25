package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.impl;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Serv_aplicacion.HelloWorld;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Serv_aplicacion.CiaoMondo;
import com.eduardosergio.TFG_cliente.negocio.holaMundo.Serv_aplicacion.HolaMundo;
import com.eduardosergio.TFG_cliente.negocio.passwordSynchronizerSTS1.PasswordSynchronizerSTS1;
import com.eduardosergio.TFG_cliente.negocio.passwordSynchronizerSTS2.PasswordSynchronizerSTS2;
import com.eduardosergio.TFG_cliente.negocio.seguridad.credentialTokenizer.CredentialTokenizer;
import com.eduardosergio.TFG_cliente.negocio.seguridad.credentialTokenizer.UserToken;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Authenticator;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SSODelegatorImpl extends SSODelegator {

    private static Logger log = LoggerFactory.getLogger(SSODelegatorImpl.class);
    
    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";

    private final String SERVICE_NAME = "/SA_psrest/psrest";

    private final String URL_SERVICIO = HOST + ":" + PORT + APP_URI + SERVICE_NAME;

    private Client cliente;

    private PasswordSynchronizerSTS1 portSTS1;

    private PasswordSynchronizerSTS2 portSTS2;

    public SSODelegatorImpl() throws ProxyException {
        log.info("Creando SSODelegator");
        
        Service service;
		try {
			service = Service.create(new URL("https://localhost:8443/TFG_server/services/pssts1?wsdl"), new QName("http://impl.passwordSynchronizerSTS1.negocio.TFG_server.eduardosergio.com/", "PasswordSynchronizerSTS1ImplService"));

			portSTS1 = service.getPort(new QName("http://impl.passwordSynchronizerSTS1.negocio.TFG_server.eduardosergio.com/", "PasswordSynchronizerSTS1ImplPort"), PasswordSynchronizerSTS1.class);
			
			service = Service.create(new URL("https://localhost:8443/TFG_server/services/pssts2?wsdl"), new QName("http://impl.passwordSynchronizerSTS2.negocio.TFG_server.eduardosergio.com/", "PasswordSynchronizerSTS2ImplService"));

			portSTS2 = service.getPort(new QName("http://impl.passwordSynchronizerSTS2.negocio.TFG_server.eduardosergio.com/", "PasswordSynchronizerSTS2ImplPort"), PasswordSynchronizerSTS2.class);

			UserToken credenciales = new CredentialTokenizer().getUserToken();
	        
	        cliente = ClientBuilder.newClient().register(new Authenticator(credenciales.getUser(), credenciales.getPassword()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

        log.info("SSODelegator creado");
    }


    @Override
    public void syncSts1(String user, String pass) {
    	portSTS1.synchronize(user, pass);
    }
    
    @Override
    public void syncSts2(String user, String pass) {
    	portSTS2.synchronize(user, pass);
    }
    
    @Override
    public void syncRest(String user, String pass) {

        String urlFinal = URL_SERVICIO + "/synchronize";
        
        Form f = new Form();
		f.param("user", user);
		f.param("pass", pass);

        cliente.target(urlFinal).request().post(Entity.form(f), String.class);

    }
}

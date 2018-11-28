package com.eduardosergio.TFG_cliente.negocio.delegado.impl;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eduardosergio.TFG_cliente.negocio.delegado.Delegado_DepartamentoSOAP;
import com.eduardosergio.TFG_cliente.negocio.serv_aplicacion.SSP_SA_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl.Delegado_EmpleadoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

public class Delegado_DepartamentoSOAPImpl extends Delegado_DepartamentoSOAP {
	
	private static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImpl.class);

    private SSP_SA_Departamento portDepartamento;

    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";


    private final String URL_WSDL = "http://localhost:8080/TFG_server/wsdl/SA_Departamento.wsdl";

    private final String URL_SERVICE = HOST + ":"+ PORT + APP_URI + "/SA_Departamento";


    private final String NAMESPACE_URI = "http://Serv_aplicacion.modulo_departamento.negocio.TFG_server.eduardosergio.com/";

    private final String SERVICE_NAME = "Broker_SA_DepartamentoImpl";
	
	@Override
	public TDepartamentoCompleto buscarByID(Long id) throws DepartamentoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Delegado_DepartamentoSOAPImpl() throws ProxyException {
        log.info("Creando Delegado_DepartamentoImpl");	    
    	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cxf.xml");
		
		portDepartamento = (SSP_SA_Departamento) context.getBean("SA_Departamento");
		
		((BindingProvider) portDepartamento).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "usuario");
		((BindingProvider) portDepartamento).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "contra");
		
		((BindingProvider) portDepartamento).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_SERVICE);


        log.info("DelegadoDelNegocio creado");
    }
}

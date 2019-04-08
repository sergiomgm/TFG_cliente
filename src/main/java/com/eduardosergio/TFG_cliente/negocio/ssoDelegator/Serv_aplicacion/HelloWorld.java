package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Serv_aplicacion;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

@WebService(name = "HelloWorldImplService", targetNamespace = "http://helloWorld.negocio.TFG_server.eduardosergio.com/")
public interface HelloWorld {
	
	@WebMethod
	public String salute();
}
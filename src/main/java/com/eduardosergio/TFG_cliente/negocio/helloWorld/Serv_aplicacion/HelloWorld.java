package com.eduardosergio.TFG_cliente.negocio.helloWorld.Serv_aplicacion;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "HelloWorldImplService", targetNamespace = "http://helloWorld.negocio.TFG_server.eduardosergio.com/")
public interface HelloWorld {
	
	@WebMethod
	public String salute();
}
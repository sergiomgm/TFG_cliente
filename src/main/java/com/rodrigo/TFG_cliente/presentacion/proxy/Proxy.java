package com.rodrigo.TFG_cliente.presentacion.proxy;

import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;

import java.net.MalformedURLException;

public interface Proxy {

    final String HOST = "http://localhost" ;

    final String PORT = "55556";

    final String APP_URI = "/TFG_server/services";


    public void initProxy() throws MalformedURLException, ProxyException;


}

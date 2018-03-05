package com.rodrigo.TFG_cliente.presentacion.Proxy;

import com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones.ProxyException;

import java.net.MalformedURLException;

public interface Proxy {

    final String HOST = "http://localhost" ;

    final String PORT = "55555";

    final String APP_URI = "/TFG_server/services";


    public void initProxy() throws MalformedURLException, ProxyException;


}

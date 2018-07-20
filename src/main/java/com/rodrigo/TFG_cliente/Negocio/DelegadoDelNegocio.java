package com.rodrigo.TFG_cliente.Negocio;

import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

import java.net.MalformedURLException;

public interface DelegadoDelNegocio {

    String HOST = "http://localhost" ;
//    String HOST = "https://127.0.0.1" ;

    String PORT = "8080";
//    String PORT = "8443";

    String APP_URI = "/TFG_server/services";


}

package com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones;

public class ProxyException extends Exception {

    private static String message = "Error en Proxy";

    public ProxyException() {
        super(message);

    }

    public ProxyException(String message) {
        super(message);
    }
}

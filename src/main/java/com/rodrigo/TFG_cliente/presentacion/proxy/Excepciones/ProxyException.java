package com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones;

public class ProxyException extends Exception {

    private static String message = "Error en proxy";

    public ProxyException() {
        super(message);

    }

    public ProxyException(String message) {
        super(message);
    }
}

package com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class ProxyException extends Exception {

    private static String message = "Error en DelegadoDelNegocio";

    public ProxyException() {
        super(message);

    }

    public ProxyException(String message) {
        super(message);
    }
}

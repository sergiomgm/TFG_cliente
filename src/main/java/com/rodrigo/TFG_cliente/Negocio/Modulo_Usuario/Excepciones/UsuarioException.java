package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class UsuarioException extends Exception{
    public UsuarioException(String message) {
        super(message);
    }


    public UsuarioException(Throwable cause) {
        super(cause);
    }


    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
}

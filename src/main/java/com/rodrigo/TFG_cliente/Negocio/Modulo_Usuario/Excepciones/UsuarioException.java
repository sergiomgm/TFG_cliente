package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones;

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

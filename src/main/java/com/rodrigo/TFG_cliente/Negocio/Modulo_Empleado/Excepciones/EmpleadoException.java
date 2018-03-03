package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones;

public class EmpleadoException extends Exception{
    public EmpleadoException(String message) {
        super(message);
    }


    public EmpleadoException(Throwable cause) {
        super(cause);
    }
}

package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones;

public class DepartamentoNoEncontradoException extends DepartamentoException {


    public DepartamentoNoEncontradoException() {
        super("Departamento no encontrado.");
    }
    public DepartamentoNoEncontradoException(String message) {
        super(message);
    }
}

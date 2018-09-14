package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class DepartamentoNoEncontradoException extends DepartamentoException {


    public DepartamentoNoEncontradoException() {
        super("Departamento no encontrado.");
    }
    public DepartamentoNoEncontradoException(String message) {
        super(message);
    }
}

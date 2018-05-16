package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones;

public class DepartamentoConEmpleadosException extends DepartamentoException {


    public DepartamentoConEmpleadosException() {
        super("El departamento tiene empleados asignados.");
    }


    public DepartamentoConEmpleadosException(String message) {
        super(message);
    }

    public DepartamentoConEmpleadosException(Throwable cause) {
        super(cause);
    }

    public DepartamentoConEmpleadosException(String message, Throwable cause) {
        super(message, cause);
    }
}

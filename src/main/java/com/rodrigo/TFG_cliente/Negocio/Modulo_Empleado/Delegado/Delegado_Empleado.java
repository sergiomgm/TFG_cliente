package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl.Delegado_EmpleadoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public abstract class Delegado_Empleado  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Empleado.class);



    private static Delegado_Empleado ourInstance;

    static {

        try {
            log.info("Delegado_Empleado.static initializer");
            ourInstance = new Delegado_EmpleadoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Empleado getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract TEmpleadoCompleto crearEmpleado(TEmpleado empleadoNuevo) throws EmpleadoYaExisteExcepcion, EmpleadoFieldInvalidException, EmpleadoException;


    public abstract TEmpleadoCompleto buscarByID(Long id) throws EmpleadoFieldInvalidException, EmpleadoException;


    public abstract boolean eliminarEmpleado(Long id) throws EmpleadoFieldInvalidException, EmpleadoException;


    public abstract List<TEmpleado> listarEmpleados();


    public abstract TEmpleadoCompleto buscarByEmail(String email) throws EmpleadoFieldInvalidException, EmpleadoException;


}

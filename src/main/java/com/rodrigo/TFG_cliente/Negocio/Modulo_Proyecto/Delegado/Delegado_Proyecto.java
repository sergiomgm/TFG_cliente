package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.impl.Delegado_ProyectoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TEmpleadoProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoYaExistenteException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public abstract class Delegado_Proyecto  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Proyecto.class);



    private static Delegado_Proyecto ourInstance;

    static {
        try {
            ourInstance = new Delegado_ProyectoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Proyecto getInstance() {
        return ourInstance;
    }



    public abstract TProyecto crearProyecto(TProyecto proyectoNuevo) throws ProyectoYaExistenteException, ProyectoFieldInvalidException, ProyectoException;

    public abstract TProyectoCompleto buscarByID(Long id) throws ProyectoFieldInvalidException, ProyectoException;

    public abstract TProyectoCompleto buscarByNombre(String nombre) throws ProyectoFieldInvalidException, ProyectoException;


    public abstract boolean eliminarProyecto(Long id) throws ProyectoFieldInvalidException, ProyectoException;

    public abstract List<TProyecto> listarProyectos();

    public abstract TEmpleadoProyecto agregarEmpleadoAProyecto(TEmpleado e, TProyecto p, int horas) throws ProyectoException, EmpleadoException;

    public abstract boolean eliminarEmpleadoAProyecto(Long idEmple, Long idProy) throws ProyectoException, EmpleadoException;


}

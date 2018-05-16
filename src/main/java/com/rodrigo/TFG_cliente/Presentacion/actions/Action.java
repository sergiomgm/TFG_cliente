package com.rodrigo.TFG_cliente.Presentacion.actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "Action")
@SessionScoped
public class Action implements Serializable {


    protected static Accion accion = null;


    protected static boolean hayError;

    protected static String mensajeError;

    protected static String mensajeSuccess;

    protected static String mensajeWarning;


    public enum Accion {

        ACCION_BUSCAR_EMPLEADO_ID,
        ACCION_BUSCAR_EMPLEADO_EMAIL,
        ACCION_CREAR_EMPLEADO_TIEMPO_COMPLETO,
        ACCION_CREAR_EMPLEADO_TIEMPO_PARCIAL,
        ACCION_ELIMINAR_EMPLEADO,
        ACCION_LISTAR_EMPLEADOS,
        ACCION_MOSTRAR_EMPLEADO,


        BUSCAR_EMPLEADO_ID,
        BUSCAR_EMPLEADO_EMAIL,
        CREAR_EMPLEADO,
        ELIMINAR_EMPLEADO,
        LISTAR_EMPLEADOS,


        ACCION_BUSCAR_DEPARTAMENTO_ID,
        ACCION_BUSCAR_DEPARTAMENTO_SIGLAS,
        ACCION_CREAR_DEPARTAMENTO,
        ACCION_ELIMINAR_DEPARTAMENTO,
        ACCION_LISTAR_DEPARTAMENTOS,


        BUSCAR_DEPARTAMENTO_ID,
        BUSCAR_DEPARTAMENTO_SIGLAS,
        CREAR_DEPARTAMENTO,
        ELIMINAR_DEPARTAMENTO,
        LISTAR_DEPARTAMENTOS,


        ACCION_BUSCAR_PROYECTO_ID,
        ACCION_BUSCAR_PROYECTO_NOMBRE,
        ACCION_CREAR_PROYECTO,
        ACCION_ELIMINAR_PROYECTO,
        ACCION_LISTAR_PROYECTOS,
        ACCION_AGREGAR_EMPLEADO_A_PROYECTO,


        BUSCAR_PROYECTO_ID,
        BUSCAR_PROYECTO_NOMBRE,
        CREAR_PROYECTO,
        ELIMINAR_PROYECTO,
        LISTAR_PROYECTOS,
        AGREGAR_EMPLEADO_A_PROYECTO


    }


    public boolean getHayError() {
        return hayError;
    }

    public void setHayError(boolean hayError) {
        this.hayError = hayError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeSuccess() {
        return mensajeSuccess;
    }

    public void setMensajeSuccess(String mensajeSuccess) {
        this.mensajeSuccess = mensajeSuccess;
    }

    public String getMensajeWarning() {
        return mensajeWarning;
    }

    public void setMensajeWarning(String mensajeWarning) {
        this.mensajeWarning = mensajeWarning;
    }
}

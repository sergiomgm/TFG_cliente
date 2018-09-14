package com.rodrigo.TFG_cliente.Presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
@ManagedBean(name = "AccionVista")
@SessionScoped
public class AccionVista implements Serializable {


    private static AccionEnum accion = null;


    private static boolean hayError;

    private static String mensajeError;

    private static String mensajeSuccess;

    private static String mensajeWarning;

    public void inicializarAtributos() {
        hayError = false;
        mensajeError = null;
        mensajeWarning = null;
        mensajeSuccess = null;
    }


    public enum AccionEnum {

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
        ACCION_MOSTRAR_DEPARTAMENTO,
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
        //ACCION_AGREGAR_EMPLEADO_A_PROYECTO,


        BUSCAR_PROYECTO_ID,
        BUSCAR_PROYECTO_NOMBRE,
        ACCION_MOSTRAR_PROYECTO,
        CREAR_PROYECTO,
        ELIMINAR_PROYECTO,
        LISTAR_PROYECTOS,
        AGREGAR_EMPLEADO_A_PROYECTO,

        ACCION_ASIGNAR_EMPELADO_A_PROYECTO,
        ACCION_ELIMINAR_EMPLEADO_DE_PROYECTO


    }

    public static AccionEnum getAccion() {
        return accion;
    }

    public static void setAccion(AccionEnum accion) {
        AccionVista.accion = accion;
    }

    public static boolean getHayError() {
        return hayError;
    }

    public static void setHayError(boolean hayError) {
        AccionVista.hayError = hayError;
    }

    public static String getMensajeError() {
        return mensajeError;
    }

    public static void setMensajeError(String mensajeError) {
        AccionVista.mensajeError = mensajeError;
    }

    public static String getMensajeSuccess() {
        return mensajeSuccess;
    }

    public static void setMensajeSuccess(String mensajeSuccess) {
        AccionVista.mensajeSuccess = mensajeSuccess;
    }

    public static String getMensajeWarning() {
        return mensajeWarning;
    }

    public static void setMensajeWarning(String mensajeWarning) {
        AccionVista.mensajeWarning = mensajeWarning;
    }


    @Override
    public String toString() {
        return accion.toString();
    }
}

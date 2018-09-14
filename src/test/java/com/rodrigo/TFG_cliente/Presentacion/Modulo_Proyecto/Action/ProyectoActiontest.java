package com.rodrigo.TFG_cliente.Presentacion.Modulo_Proyecto.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Modulo_Proyecto.Bean.ProyectoBean;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class ProyectoActiontest  {

    private final static Logger log = LoggerFactory.getLogger(ProyectoActiontest.class);


    @BeforeAll
    static void initSA() throws DepartamentoException, EmpleadoException, ProyectoException, ProxyException {

    }

    @BeforeEach
    void iniciarContexto() throws EmpleadoException {

    }


    @AfterEach
    void finalizarContexto() throws EmpleadoException {
    }


    /******************************************************************
     ********************   TEST CREAR PROYECTO   *********************
     ******************************************************************/


    @Test
    void crearDepartamento() throws ParseException {

        ProyectoBean pb = new ProyectoBean();

        pb.setViewRequest("vista_admin");

        pb.setNombre("prueba proyecto");
        pb.setFechaInicio(new SimpleDateFormat("dd-MM-yyyy HH").parse("2-09-2018 1"));
        pb.setFechafin(new SimpleDateFormat("dd-MM-yyyy HH").parse("31-12-2019 1"));
        pb.setDescripcion("sdvdfvdvdfaf");

        pb.setAccion("ACCION_CREAR_PROYECTO");

        String view = pb.crearProyecto();

        System.out.println(view);

    }
}

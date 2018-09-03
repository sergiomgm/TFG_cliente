package com.rodrigo.TFG_cliente.Presentacion.Modulo_Departamento.Bean;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DepartamentoBeanEnumTest {

    private final static Logger log = LoggerFactory.getLogger(DepartamentoBeanEnumTest.class);






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
     ********************   TEST CREAR DEPARTAMENTO   *********************
     ******************************************************************/


    @Test
    void crearDepartamento() {

        DepartamentoBean db = new DepartamentoBean();

        db.setViewRequest("vista_admin");
        db.setNombre("Nuevo de Pruebas 3");
        db.setAccion("ACCION_CREAR_DEPARTAMENTO");

        String view = db.crearDepartamento();

        System.out.println(view);

    }
}

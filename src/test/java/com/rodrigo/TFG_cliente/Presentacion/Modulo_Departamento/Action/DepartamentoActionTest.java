package com.rodrigo.TFG_cliente.Presentacion.Modulo_Departamento.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


public class DepartamentoActionTest  {

    private final static Logger log = LoggerFactory.getLogger(DepartamentoActionTest.class);






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



}

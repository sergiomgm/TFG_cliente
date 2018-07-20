package com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Modulo_Proyecto.Action.ProyectoAction;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmpleadoActionTest {

    final static Logger log = LoggerFactory.getLogger(EmpleadoActionTest.class);



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
     ********************   TEST CREAR EMPLEADO   *********************
     ******************************************************************/


    @Test
    void buscarById() {


        EmpleadoAction ea = new EmpleadoAction();

        ea.setId(20L);

        String view = ea.buscarById();


        TEmpleadoCompleto ec = ea.getEmpleadoCompleto();


//        ProyectoAction pa = new ProyectoAction();
//        pa.setId(1L);
//        String view = pa.buscarById();
//        TProyectoCompleto ec = pa.getProyectoCompleto();


    }
}

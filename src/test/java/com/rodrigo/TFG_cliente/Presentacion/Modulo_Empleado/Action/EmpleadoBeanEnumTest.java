package com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Bean.EmpleadoBean;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class EmpleadoBeanEnumTest {

    final static Logger log = LoggerFactory.getLogger(EmpleadoBeanEnumTest.class);



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


        EmpleadoBean ea = new EmpleadoBean();

        ea.setId(20L);

        String view = ea.buscarById();


        TEmpleadoCompleto ec = ea.getEmpleadoCompleto();


//        ProyectoBean pa = new ProyectoBean();
//        pa.setId(1L);
//        String view = pa.buscarById();
//        TProyectoCompleto ec = pa.getProyectoCompleto();


    }
}

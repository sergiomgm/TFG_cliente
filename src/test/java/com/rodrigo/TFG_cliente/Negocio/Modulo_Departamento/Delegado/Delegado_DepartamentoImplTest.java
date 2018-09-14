package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl.Delegado_DepartamentoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.*;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class Delegado_DepartamentoImplTest {

    static Delegado_Departamento deleg;

    static private TDepartamentoCompleto d1;
    static private TEmpleadoCompleto e1;

    static TEmpleadoCompleto empld1;

    static TDepartamentoCompleto dept;
    static TProyectoCompleto proy1;

    final static Logger log = LoggerFactory.getLogger(Delegado_DepartamentoImplTest.class);


    /*******************************************************************
     **********************   METODOS INICIALES   **********************
     *******************************************************************/


    @BeforeAll
    static void initSA() throws DepartamentoException, EmpleadoException, ProyectoException {
        log.info("Creando SA...");
        deleg = new Delegado_DepartamentoImpl();


        empld1 = Delegado_Empleado.getInstance().buscarByID(20L);

        dept = deleg.buscarBySiglas("DdP");

        proy1 = Delegado_Proyecto.getInstance().buscarByID(1L);


    }

    @BeforeEach
    void iniciarContexto() throws DepartamentoException, EmpleadoException {
        String siglas = "DT";

        log.info("Creando departamento ");
        TDepartamentoCompleto auxD = deleg.buscarBySiglas(siglas);
        if (auxD == null) {
            d1 = new TDepartamentoCompleto(deleg.crearDepartamento(new TDepartamento("Departamento Test")));
        } else
            d1 = auxD;


        String nombre = "empleTest";

        log.info("Creando empleado ");
        TEmpleadoCompleto auxE = Delegado_Empleado.getInstance().buscarByEmail(nombre.toLowerCase().concat("@gmail.com"));

        if (auxE == null) {
            e1 = Delegado_Empleado.getInstance().crearEmpleado(new TEmpleadoTCompleto(nombre, "1234", d1.getId()));
        } else
            e1 = auxE;


        d1 = deleg.buscarBySiglas(siglas);


    }


    @AfterEach
    void finalizarContexto() throws DepartamentoException, EmpleadoException {

        Delegado_Empleado.getInstance().eliminarEmpleado(e1.getEmpleado().getId());

        log.info("Eliminado departamento");
        deleg.eliminarDepartamento(d1.getId());

    }


    /******************************************************************
     ******************   TEST CREAR DEPARTAMENTO   *******************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"crear1", "crear2", "crear3"})
    void crearDepartamento(String nombre) throws DepartamentoException {

        TDepartamento d = new TDepartamento(nombre);
        TDepartamento departCreado = deleg.crearDepartamento(d);


        d.setId(departCreado.getId());
        log.debug("departCreado = '" + departCreado + "'");
        log.debug("d         = '" + d + "'");

        assertNotNull(departCreado);
        assertNotNull(departCreado.getId());

        assertEquals(d.toString(), departCreado.toString());


        deleg.eliminarDepartamento(departCreado.getId());
    }


    @Test
    void crearDepartamentoExistente() throws DepartamentoException {


        TDepartamento d = new TDepartamento("Departamento Existente");

        log.info("Creando departamento 1");
        d = deleg.crearDepartamento(d);


        TDepartamento finalD = d;
        Throwable exception = assertThrows(DepartamentoYaExisteExcepcion.class, () -> {

            TDepartamento d2 = finalD;

            log.info("Creando departamento 2");
            d2 = deleg.crearDepartamento(d2);

        });


        log.debug("exception = '" + exception.getMessage() + "'");
        deleg.eliminarDepartamento(d.getId());

    }

    @Test
    void crearDepartamentoNull() {


        Throwable exception = assertThrows(DepartamentoFieldInvalidException.class, () -> {

            TDepartamento departCreado = deleg.crearDepartamento(null);

            assertNull(departCreado);

        });

        log.error("----  EXCEPCION! ----", exception);

    }


    @Test
    void crearDepartamentoVacio() {

        Throwable exception = assertThrows(DepartamentoException.class, () -> {

            TDepartamento departCreado = deleg.crearDepartamento(new TDepartamento());

        });

        log.info("Excepcion capturada:" + exception.getMessage());
    }


    @Test
    void crearDepartamentoSiglasNull() {


        log.info("forzando siglas = null");
        Throwable ex1 = assertThrows(DepartamentoFieldInvalidException.class, () -> {

            d1.setSiglas(null);
            log.debug("d1= " + d1);
            TDepartamento departCreado = deleg.crearDepartamento(d1.getDepartamento());

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void crearDepartamentoSiglaVacio() {


        log.info("forzando siglas = ''");
        Throwable ex2 = assertThrows(DepartamentoException.class, () -> {

            d1.setSiglas("");
            log.debug("d1 = '" + d1 + "'");
            TDepartamento empleCreado = deleg.crearDepartamento(d1.getDepartamento());

        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }


    /******************************************************************
     ****************   TEST BUSCAR DEPARTAMENTO ID  ******************
     ******************************************************************/


    @Test
    void buscarByID() throws DepartamentoException {
        log.info("SA_DepartamentoImplTest.buscarByID");

        TDepartamentoCompleto d = deleg.buscarByID(d1.getId());
        log.info(d.toString());


        assertNotNull(d);
        assertEquals(d.getId(), d1.getId());
        assertEquals(d.getDepartamento().getNombre(), d1.getDepartamento().getNombre());
        assertEquals(d.toString(), d1.toString());

        //deleg.eliminarDepartamento(nuevo);

    }


    @Test
    void buscarByIDNegativo() throws DepartamentoException {
        log.info("SA_DepartamentoImplTest.buscarByIDNegativo");

        Throwable ex2 = assertThrows(DepartamentoFieldInvalidException.class, () -> {

            TDepartamentoCompleto d = deleg.buscarByID(-2L);


        });
        log.info("Excepcion capturada:" + ex2.getMessage());

    }

    @Test
    void buscarByIDCero() throws DepartamentoException {
        log.info("SA_DepartamentoImplTest.buscarByIDCero");

        Throwable ex2 = assertThrows(DepartamentoException.class, () -> {

            TDepartamentoCompleto d = deleg.buscarByID(0L);


        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }


    @Test
    void buscarByIDInexixtente() throws DepartamentoException {
        log.info("SA_DepartamentoImplTest.buscarByIDInexixtente");

        TDepartamentoCompleto buscado = deleg.buscarByID(30000L);

        assertNull(buscado);

    }


    /******************************************************************
     ******************   TEST ELIMINAR DEPARTAMENTO   ********************
     ******************************************************************/


    @Test
    void eliminarDepartamento() throws DepartamentoException, EmpleadoException {
        log.info("SA_DepartamentoImplTest.eliminarDepartamento");

        TDepartamento d = new TDepartamento("Eliminar 5");

        log.info("Creando departamento");
        d = deleg.crearDepartamento(d);

        log.info("Eliminando departamento");
        boolean resutl = deleg.eliminarDepartamento(d.getId());

        log.debug("resutl = '" + resutl + "'");

        assertNull(deleg.buscarByID(d.getId()));

    }


    @Test
    void eliminarDepartamentoConEmpleados() throws DepartamentoException, EmpleadoException {
        log.info("SA_DepartamentoImplTest.eliminarDepartamento");

        TDepartamentoCompleto d = new TDepartamentoCompleto();
        log.info("Creando departamento");

        d.setDepartamento(deleg.crearDepartamento(new TDepartamento("Eliminar 6")));


        String nombre = "EmpleEliminar";

        log.info("Creando empleado ");
        TEmpleadoCompleto auxE = Delegado_Empleado.getInstance().buscarByEmail(nombre.toLowerCase().concat("@gmail.com"));

        if (auxE == null) {
            auxE = Delegado_Empleado.getInstance().crearEmpleado(new TEmpleadoTCompleto(nombre, "1234", d.getId()));
        }

        d = deleg.buscarBySiglas(d.getSiglas());


        TDepartamentoCompleto finalD = d;
        Throwable ex1 = assertThrows(DepartamentoConEmpleadosException.class, () -> {

            log.info("Eliminando departamento");
            boolean result = deleg.eliminarDepartamento(finalD.getId());


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

        Delegado_Empleado.getInstance().eliminarEmpleado(auxE.getEmpleado().getId());
        boolean result = deleg.eliminarDepartamento(finalD.getId());

    }


    @Test
    void eliminarDepartamentoNull() {


        Throwable exception = assertThrows(DepartamentoNoEncontradoException.class, () -> {
            boolean emple = deleg.eliminarDepartamento(null);

            assertNull(emple);

        });

        log.error("----  EXCEPCION! ----", exception);

    }


    @Test
    void eliminarDepartamentoIDNegativo() {

        TDepartamento depart = new TDepartamento("Eiminar");
        depart.setId(-23L);


        Throwable exception = assertThrows(DepartamentoFieldInvalidException.class, () -> {

            boolean result = deleg.eliminarDepartamento(depart.getId());

        });

        log.error("----  EXCEPCION! ----", exception);

    }


    /******************************************************************
     *****************   TEST LISTAR DEPARTAMENTOS   ******************
     ******************************************************************/


    @Test
    void listarDepartamentos() {
        log.info("SA_DepartamentoImplTest.listarDepartamentos");

        TDepartamento[] lista = deleg.listarDepartamentos();

        assertNotNull(lista);

        log.info("************************************************************");
        log.info("************************************************************");
        Arrays.asList(lista).stream().forEach(System.out::println);
        log.info("************************************************************");
        log.info("************************************************************");

    }


    /******************************************************************
     ******************   TEST BUSCAR BY SIGLAS   *********************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"Departamento de Busquedas 1", "Departamento de Busquedas 2"})
    void buscarBySiglas(String nombre) throws DepartamentoException {
        TDepartamentoCompleto d1 = new TDepartamentoCompleto(new TDepartamento(nombre));
        TDepartamento nuevo;

        String siglas = d1.getSiglas();

        log.info("Creando departamento");
        nuevo = deleg.crearDepartamento(d1.getDepartamento());

        log.info("buscando departamento");
        d1 = deleg.buscarBySiglas(siglas);

        assertNotNull(d1);
        assertNotNull(nuevo);

        assertEquals(d1.getDepartamento().toString(), nuevo.toString());

        deleg.eliminarDepartamento(nuevo.getId());

    }


    @Test
    void buscarBySiglasInexistente() throws DepartamentoException {


        TDepartamentoCompleto e2 = deleg.buscarBySiglas("asdfawefafafdwefa");

        assertNull(e2);

    }


    @Test
    void buscarBySiglasVacio() throws DepartamentoException {


        TDepartamentoCompleto d2 = deleg.buscarBySiglas("");


        assertNull(d2);

    }


    @Test
    void buscarBySiglasNull() throws DepartamentoException {

            TDepartamentoCompleto e2 = deleg.buscarBySiglas(null);

            assertNull(e2);



    }


/******************************************************************
 *********************   METODOS AUXILIARES   *********************
 ******************************************************************/


}
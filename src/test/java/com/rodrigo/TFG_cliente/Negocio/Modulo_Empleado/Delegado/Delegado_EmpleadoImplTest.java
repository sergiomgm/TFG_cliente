package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTParcial;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TEmpleadoProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.Presentacion.Utils.EmailValidatorTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
class Delegado_EmpleadoImplTest {

    static Delegado_Empleado deleg;

    static private TEmpleadoCompleto e1;
    static TEmpleadoCompleto empleCompleto;
    //    static Empleado emple2;
    static TDepartamentoCompleto dept;
    static TProyectoCompleto proy1;

    final static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImplTest.class);


    /*******************************************************************
     **********************   METODOS INICIALES   **********************
     *******************************************************************/

    @BeforeAll
    static void initSA() throws DepartamentoException, EmpleadoException, ProyectoException, ProxyException {
        log.info("Creando Delegado...");
        deleg = Delegado_Empleado.getInstance();


//        empleCompleto = new EmpleadoTParcial("empleCompleto", "1234", Rol.ADMIN);
        empleCompleto = deleg.buscarByID(20L);

//        dept1 = FactoriaSA.getInstance().crearSA_Departamento().buscarBySiglas(dept1.getSiglas());

        dept = Delegado_Departamento.getInstance().buscarBySiglas("DdP");

        proy1 = Delegado_Proyecto.getInstance().buscarByID(1L);



        /*//dept = new Departamento("Ingenieria del Software");
        dept = FactoriaSA.getInstance().crearSA_Departamento().buscarByID(3L);

        e1 = new EmpleadoTCompleto("empleTest", "1234", Rol.EMPLEADO, dept);

        //dept = FactoriaSA.getInstance().crearSA_Departamento().buscarByID(1L);
        //dept = FactoriaSA.getInstance().crearSA_Departamento().crearDepartamento(dept);

        dept.getEmpleados().add(e1);
        e1.setDepartamento(dept);

        e1 = FactoriaSA.getInstance().crearSA_Empleado().buscarByEmail(e1.getSiglas());*/
    }

    @BeforeEach
    void iniciarContexto() throws EmpleadoException {

        String nombre = "empleTest";

        log.info("Creando empleado ");
        TEmpleadoCompleto aux = deleg.buscarByEmail(nombre.toLowerCase().concat("@gmail.com"));

        if (aux == null) {
            e1 = deleg.crearEmpleado(new TEmpleadoTCompleto(nombre, "1234",  dept.getId()));

        } else
            e1 = aux;
    }


    @AfterEach
    void finalizarContexto() throws EmpleadoException {

       /* assertFalse(deleg.transactionIsActive(), "Transacción no cerrada");

        assertFalse(deleg.emIsOpen(), "Entity Manager no cerrado");
*/
        log.info("Eliminado empleado");
        deleg.eliminarEmpleado(e1.getEmpleado().getId());
    }


    /******************************************************************
     ********************   TEST CREAR EMPLEADO   *********************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"crear1, 1234", "crear2, 1234", "crear3, 1234"})
    void crearEmpleado(String nombre, String pass) throws EmpleadoException {


        TEmpleadoTParcial e = new TEmpleadoTParcial(nombre, pass, dept.getId());

        TEmpleadoCompleto empleCreado = deleg.crearEmpleado(e);


        e.setId(empleCreado.getEmpleado().getId());
        log.debug("empleCreado = '" + empleCreado + "'");
        log.debug("e1          = '" + e + "'");

        assertNotNull(empleCreado);
        assertNotNull(empleCreado.getEmpleado().getId());

        assertEquals(e.toString(), empleCreado.getEmpleado().toString());


        deleg.eliminarEmpleado(empleCreado.getEmpleado().getId());
    }


    @Test
    void crearEmpleadoExistente() throws EmpleadoException {

        /*Empleado e1 = new EmpleadoTParcial("juan", "1234", dept);

        log.info("Creando empleado 1");
        e1 = deleg.crearEmpleado(e1);*/


        Throwable exception = assertThrows(EmpleadoYaExisteExcepcion.class, () -> {

            TEmpleadoCompleto e2 = e1;

            log.info("Creando empleado 2");
            e2 = deleg.crearEmpleado(e2.getEmpleado());

        });


        /*deleg.eliminarEmpleado(e1);*/
    }

    @Test
    void crearEmpleadoNull() {


        Throwable exception = assertThrows(EmpleadoException.class, () -> {
            TEmpleadoCompleto empleCreado;
            empleCreado = deleg.crearEmpleado(null);

            assertNull(empleCreado);

        });

        log.error("----  EXCEPCION! ----", exception);

    }


    @Test
    void crearEmpleadoVacio() {

        Throwable exception = assertThrows(EmpleadoException.class, () -> {

            TEmpleadoCompleto empleCreado = deleg.crearEmpleado(new TEmpleadoTParcial());

        });

        log.info("Excepcion capturada:" + exception.getMessage());
    }


    @Test
    void crearEmpleadoEmailNull() {


        log.info("forzando email = null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            e1.getEmpleado().setEmail(null);
            log.debug("e1= " + e1);
            TEmpleadoCompleto empleCreado = deleg.crearEmpleado(e1.getEmpleado());

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void crearEmpleadoEmailVacio() {


        log.info("forzando email = ''");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            e1.getEmpleado().setEmail("");
            log.debug("e1 = '" + e1 + "'");
            TEmpleadoCompleto empleCreado = deleg.crearEmpleado(e1.getEmpleado());

        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }


    /******************************************************************
     ******************   TEST BUSCAR EMPLEADO ID  ********************
     ******************************************************************/


    @Test
    void buscarByID() throws EmpleadoException {
        log.info("SA_EmpleadoImplTest.buscarUsuarioByID");

        TEmpleadoCompleto e = deleg.buscarByID(e1.getEmpleado().getId());
        log.info(e.toString());


        assertNotNull(e);
        assertEquals(e.getEmpleado().getId(), e1.getEmpleado().getId());
        assertEquals(e.getEmpleado().getNombre(), e1.getEmpleado().getNombre());
        assertEquals(e.toString(), e1.toString());

        //deleg.eliminarEmpleado(nuevo);

    }


    @Test
    void buscarByIDNegativo() throws EmpleadoException {
        log.info("SA_EmpleadoImplTest.buscarByIDNegativo");

        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            TEmpleadoCompleto e = deleg.buscarByID(-2L);


        });
        log.info("Excepcion capturada:" + ex2.getMessage());

    }

    @Test
    void buscarByIDCero() throws EmpleadoException {
        log.info("SA_EmpleadoImplTest.buscarByIDCero");

        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            TEmpleadoCompleto e = deleg.buscarByID(0L);


        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }


    @Test
    void buscarByIDInexixtente() throws EmpleadoException {
        log.info("SA_EmpleadoImplTest.buscarByIDInexixtente");

        TEmpleadoCompleto buscado = deleg.buscarByID(30000L);

        assertNull(buscado);

    }


    /******************************************************************
     ******************   TEST ELIMINAR EMPLEADO   ********************
     ******************************************************************/


    @Test
    void eliminarEmpleado() throws EmpleadoException, ProyectoException {
        log.info("SA_EmpleadoImplTest.eliminarEmpleado");


        log.info("Creando empleado");
        TEmpleadoCompleto e = deleg.crearEmpleado(new TEmpleadoTParcial("Eliminar4", "pass", dept.getId()));

        log.info("Asignando proyecto a empleado");
        TEmpleadoProyecto ep = Delegado_Proyecto.getInstance().agregarEmpleadoAProyecto(e.getEmpleado(), proy1.getProyecto(), 5);

        proy1.agregarEmpleadoProyecto(ep, e.getEmpleado());
        e.agregarEmpleadoProyecto(ep, proy1.getProyecto());

        log.info("Eliminando empleado");
        boolean resutl = deleg.eliminarEmpleado(e.getEmpleado().getId());

        log.debug("resutl = '" + resutl + "'");


        assertNull(deleg.buscarByID(e.getId()));

    }


    /******************************************************************
     *******************   TEST LISTAR EMPLEADOS   ********************
     ******************************************************************/


    @Test
    void listarUsuarios() {
        log.info("ListarUsersTest");

        List<TEmpleado> lista = deleg.listarEmpleados();

        assertNotNull(lista);

        log.info("************************************************************");
        log.info("************************************************************");
        lista.stream().forEach(System.out::println);
        log.info("************************************************************");
        log.info("************************************************************");

    }

/*
    @Test
    void saludo() {
        log.info("---- SA_EmpleadoImplTest.saludo ---- ");

        String nombre = "Rodrigo";
        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";

        assertNotNull(deleg.saludar(nombre));

        assertTrue(deleg.saludar(nombre).equals(str));
    }
    */




    /******************************************************************
     *******************   TEST BUSCAR BY EMAIL   *********************
     ******************************************************************/

    @ParameterizedTest
    @CsvSource({"buscar1, 1234", "buscar2, 1234"})
    void buscarByEmail(String nombre, String pass) throws EmpleadoException {
        TEmpleadoCompleto nuevo, e1 = new TEmpleadoCompleto(new TEmpleadoTParcial(nombre, pass,  dept.getId()), dept.getDepartamento());
        dept.getEmpleados().put(e1.getId(), e1.getEmpleado());

        String email = e1.getEmail();

        log.info("Creando empleado");
        nuevo = deleg.crearEmpleado(e1.getEmpleado());

        log.info("buscnado empleado");
        e1 = deleg.buscarByEmail(email);

        assertEquals(e1.toString(), nuevo.toString());

        deleg.eliminarEmpleado(nuevo.getEmpleado().getId());

    }

    /*public static void main(String[] args) throws EmpleadoException, DepartamentoException {
        initSA();
        Empleado nuevo, e1 = new EmpleadoTParcial("empleado", "1234", Rol.EMPLEADO, dept);
        String email = e1.getSiglas();

        //log.info("Creando empleado");
        //nuevo = deleg.crearEmpleado(e1);

        log.info("buscnado empleado");
        e1 = deleg.buscarByEmail(email);

    }*/

/*    @Test
    void buscarByEmailSimple() throws EmpleadoException {
        Empleado nuevo, e1 = new EmpleadoTParcial("administrador", "1234", Rol.ADMIN, dept);
        String email = e1.getSiglas();

        log.info("Creando empleado");
        //nuevo = deleg.crearEmpleado(e1);

        log.info("buscnado empleado");
        e1 = deleg.buscarByEmail(email);

        //assertTrue(e1.equalsWithOutVersion(nuevo));

        //deleg.eliminarEmpleado(nuevo);

    }*/

    @Test
    void buscarByEmailInexistente() throws EmpleadoException {


        TEmpleadoCompleto e2 = deleg.buscarByEmail("emailInexistente@gmail.com");

        assertNull(e2);


    }


    @ParameterizedTest
    @MethodSource("InvalidEmailProvider")
    void buscarByEmailIncorrecto(String[] Email) {


        Throwable ex1 = assertThrows(EmpleadoFieldInvalidException.class, () -> {

            for (String temp : Email) {
                deleg.buscarByEmail(temp);
            }


        });

        log.info("Excepcion capturada:" + ex1.getMessage());


    }

    @Test
    void buscarByEmailVacio() {

        Throwable ex1 = assertThrows(EmpleadoFieldInvalidException.class, () -> {

            TEmpleadoCompleto e2 = deleg.buscarByEmail("");


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void buscarByEmailNull() {
        Throwable ex1 = assertThrows(EmpleadoFieldInvalidException.class, () -> {

            TEmpleadoCompleto e2 = deleg.buscarByEmail(null);


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    /******************************************************************
     *********************   METODOS AUXILIARES   *********************
     ******************************************************************/


    public static Object[][] InvalidEmailProvider() {
        return EmailValidatorTest.InvalidEmailProvider();
    }

}
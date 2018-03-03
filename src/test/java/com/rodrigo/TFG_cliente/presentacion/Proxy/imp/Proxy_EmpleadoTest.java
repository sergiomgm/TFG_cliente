package com.rodrigo.TFG_cliente.presentacion.Proxy.imp;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Rol;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoLoginErroneo;
import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.proxy.imp.Proxy_Empleado;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Proxy_EmpleadoTest {


    private final static Logger log = LoggerFactory.getLogger(Proxy_EmpleadoTest.class);

    private static Proxy_Empleado proxy;

    private Empleado e1;



    /******************************************************************
     **********************   METODO INICIALES   **********************
     ******************************************************************/

    @BeforeAll
    static void initSA() {
        try {
            proxy = new Proxy_Empleado();
        } catch (ProxyException e) {
            log.error("Error al crear Proxy_Empleado");
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            fail("Error al crear Proxy_Empleado");
        }
    }

    @BeforeEach
    void iniciarContexto() throws EmpleadoException {
        e1 = new Empleado("empleado", "1234", Rol.valueOf("EMPLEADO"));
        log.info("Creando empleado ");
        if (proxy.buscarByEmail(e1.getEmail()) == null) {
            e1 = proxy.crearEmpleado(e1);
        }
    }


    @AfterEach
    void finalizarContexto() {

        log.info("Eliminado empleado");
        proxy.eliminarEmpleado(e1);
    }
    

    @Test
    void initProxy() {

        try {
            proxy.initProxy();
        } catch (ProxyException e) {

            log.error("Error al iniaciar Proxy_Empleado");
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            fail("Error al iniciar Proxy_Empleado");
        }

    }

    @Test
    void crearEmpleado() {
        log.info("crearUserTest");
        Empleado user = new Empleado("juan", "1234");

        Empleado nuevo = proxy.crearEmpleado(user);

        log.debug(user.toString());
        log.debug(nuevo.toString());

        assertNotNull(nuevo);
        assertNotNull(nuevo.getId());

        assertTrue(nuevo.getId() instanceof Long);
        assertEquals(nuevo.getNombre().toString(), user.getNombre().toString());
        assertEquals(nuevo.getPassword().toString(), user.getPassword().toString());
    }


    @Test
    void buscarEmpleadoByID() {
        log.info("BuscarUserTest");
        Empleado nuevo = proxy.crearEmpleado(new Empleado("test2", "1234"));
        Empleado userB = proxy.buscarEmpleadoByID(nuevo.getId());

        log.info(userB.toString());



        assertNotNull(userB);
        assertEquals(nuevo.getId(), userB.getId());
        assertEquals(nuevo.getNombre(), userB.getNombre());

    }


    @Test
    void eliminarEmpleado() {
        log.info("EliminarUser Test");

        Empleado u = new Empleado("Eliminar", "pass");
        u = proxy.crearEmpleado(u);
        log.debug("Creado: " + u.toString());

        Boolean resutl = proxy.eliminarEmpleado(u);
        log.info("Eliminado: " + resutl);
        assertTrue(resutl);

        assertNull(proxy.buscarEmpleadoByID(u.getId()));


    }



    @Test
    void listarEmpleados() {
        log.info("ListarUsersTest");

        List lista = proxy.listarEmpleados();
        assertNotNull(lista);

    }



    @ParameterizedTest(name = "#{index} con [{arguments}]")
    @ValueSource(strings = { "Rodrigo", "Claudia", "Antonio" })
    void saludar(String nombre) {

        log.info("nombre = [" + nombre + "]");

        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";


        String saludo = proxy.saludar(nombre);

        log.info("str = '" + str + "'");
        log.info("saludo = '" + saludo + "'");

        assertNotNull(saludo);


        assertEquals(saludo, str);

    }

    @Test
    void saludarNull() {

        String nombre = null;
        log.info("nombre = [" + nombre + "]");

        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";

        String saludo = proxy.saludar(nombre);

        log.info("str = '" + str + "'");
        log.info("saludo = '" + saludo + "'");

        assertNotNull(saludo);

        assertEquals(saludo, str);

    }




    /******************************************************************
     ********************   TEST LOGIN EMPLEADO   *********************
     ******************************************************************/



    //@ParameterizedTest(name = "-> {0}, {1}")
    //@CsvSource({"emple, 1234, EMPLEADO", "admin, 1234, ADMIN"})
    @Test
    void loginTest() throws EmpleadoException {
        String email = e1.getEmail();
        String pass = e1.getPassword();


        log.info("Login: {email='" + email + ", pass='" + pass + "'}");
        Boolean result = proxy.loginEmpleado(email, pass);

        log.debug("result = '" + result + "'");
        assertTrue(result);

    }

    @Test
    void loginParamErroneosTest() {

        log.info("Login email erroneo");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = proxy.loginEmpleado("kajsdnflaf", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void loginEmpleadoInexistenteTest() {

        log.info("Login empleado inexistente");
        Throwable ex1 = assertThrows(EmpleadoLoginErroneo.class, () -> {

            boolean login = proxy.loginEmpleado("kajsdnflaf@gmail.com", "1234");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void loginEmailNulloVacioTest() {

        log.info("Login email null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = proxy.loginEmpleado(null, "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());


        log.info("Login email vacio");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            boolean login = proxy.loginEmpleado("", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());


    }

    @Test
    void loginPassNulloVacioTest() {

        log.info("Login pass null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = proxy.loginEmpleado("kajsdnflaf", null);
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

        log.info("Login pass vacia");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            boolean login = proxy.loginEmpleado("kajsdnflaf", "");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());

    }



    /******************************************************************
     *******************   TEST BUSCAR BY EMAIL   *********************
     ******************************************************************/

    @ParameterizedTest
    @CsvSource({"Admin, 1234, ADMIN", "rodri, 1234, EMPLEADO", "emple,1234, EMPLEADO"})
    void buscarByEmail(String nombre, String pass, String rol) throws EmpleadoException {
        Empleado nuevo, e1 = new Empleado(nombre, pass, Rol.valueOf(rol));
        String email = e1.getEmail();

        log.info("Creando empleado");
        nuevo = proxy.crearEmpleado(e1);

        log.info("buscnado empleado");
        e1 = proxy.buscarByEmail(email);

        assertTrue(e1.equalsWithOutVersion(nuevo));

        proxy.eliminarEmpleado(nuevo);

    }

    @Test
    void buscarByEmailInexistente() throws EmpleadoException {


        Empleado e2 = proxy.buscarByEmail("emailInexistente@gmail.com");

        assertNull(e2);

        proxy.eliminarEmpleado(e2);


    }



    @ParameterizedTest
    @MethodSource("InvalidEmailProvider")
    void buscarByEmailIncorrecto(String[] Email) {


        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            for (String temp : Email) {
                proxy.buscarByEmail(temp);
            }


        });

        log.info("Excepcion capturada:" + ex1.getMessage());


    }

    @Test
    void buscarByEmailVacio() {

        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            Empleado e2 = proxy.buscarByEmail("");


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void buscarByEmailNull() {
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            Empleado e2 = proxy.buscarByEmail(null);


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }





    /******************************************************************
     *********************   METODOS AUXILIARES   *********************
     ******************************************************************/



   /* public static Object[][] InvalidEmailProvider() {
        return EmailValidatorTest.InvalidEmailProvider();
    }*/





}
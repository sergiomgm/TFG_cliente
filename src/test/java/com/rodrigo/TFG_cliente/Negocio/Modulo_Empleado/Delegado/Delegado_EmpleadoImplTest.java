package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.EmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Rol;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Presentacion.Utils.EmailValidatorTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class Delegado_EmpleadoImplTest {


    private final static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImplTest.class);

    private Empleado e1;



    /******************************************************************
     **********************   METODO INICIALES   **********************
     ******************************************************************/

    @BeforeAll
    static void initSA() {
    }

    @BeforeEach
    void iniciarContexto() throws EmpleadoException {
        e1 = new EmpleadoTCompleto("empleado", "1234", Rol.valueOf("EMPLEADO"));
        log.info("Creando empleado ");
        if (Delegado_Empleado.getInstance().buscarByEmail(e1.getEmail()) == null) {
            e1 = Delegado_Empleado.getInstance().crearEmpleado(e1);
        }
    }


    @AfterEach
    void finalizarContexto() {

        log.info("Eliminado empleado");
        Delegado_Empleado.getInstance().eliminarEmpleado(e1);
    }




    /******************************************************************
     ********************   TEST CREAR EMPLEADO   *********************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"Admin, 1234, ADMIN", "rodri, 1234, EMPLEADO", "emple,1234, EMPLEADO"})
    void crearEmpleado(String nombre, String pass, String rol) throws EmpleadoException {

        Empleado e1 = new EmpleadoTCompleto(nombre, pass, Rol.valueOf(rol));
        log.debug("e1.getRol() = '" + e1.getRol() + "'");

        Empleado empleCreado = Delegado_Empleado.getInstance().crearEmpleado(e1);


        assertNotNull(empleCreado);

        e1.setId(empleCreado.getId());
        assertTrue(empleCreado.equalsWithOutVersion(e1));


        Delegado_Empleado.getInstance().eliminarEmpleado(empleCreado);
    }


    @Test
    void crearEmpleadoExistente() throws EmpleadoException {

        /*Usuario e1 = new Usuario("juan", "1234", Rol.valueOf("EMPLEADO"));

        log.info("Creando empleado 1");
        e1 = DelegadoDelNegocio.crearEmpleado(e1);*/


        Throwable exception = assertThrows(EmpleadoException.class, () -> {

            Empleado e2 = e1;

            log.info("Creando empleado 2");
            e2 = Delegado_Empleado.getInstance().crearEmpleado(e2);

        });


        /*DelegadoDelNegocio.eliminarEmpleado(e1);*/
    }

    @Test
    void crearEmpleadoNull() {


        Throwable exception = assertThrows(EmpleadoException.class, () -> {
            Empleado empleCreado;
            empleCreado = Delegado_Empleado.getInstance().crearEmpleado(null);

            assertNull(empleCreado);

        });


        log.error("Mensaje excepcion: " + exception.getMessage());
        log.error("EXCEPCION!!", exception);



    }


    @Test
    void crearEmpleadoVacio() {

        Throwable exception = assertThrows(EmpleadoException.class, () -> {

            Empleado empleCreado = Delegado_Empleado.getInstance().crearEmpleado(new EmpleadoTCompleto());

        });

        log.info("Excepcion capturada:" + exception.getMessage());
    }


    @Test
    void crearEmpleadoEmailNull() {


        log.info("forzando email = null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            e1.setEmail(null);
            log.debug("e1= " + e1);
            Empleado empleCreado = Delegado_Empleado.getInstance().crearEmpleado(e1);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void crearEmpleadoEmailVacio() {


        log.info("forzando email = ''");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            e1.setEmail("");
            log.debug("e1= " + e1);
            Empleado empleCreado = Delegado_Empleado.getInstance().crearEmpleado(e1);

        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }


/*
    @Test
    void buscarEmpleadoByID() {
        log.info("BuscarUserTest");
        Usuario nuevo = DelegadoDelNegocio.crearEmpleado(new Usuario("test2", "1234"));
        Usuario userB = DelegadoDelNegocio.buscarEmpleadoByID(nuevo.getId());

        log.info(userB.toString());



        assertNotNull(userB);
        assertEquals(nuevo.getId(), userB.getId());
        assertEquals(nuevo.getNombre(), userB.getNombre());

    }


    @Test
    void eliminarEmpleado() {
        log.info("EliminarUser Test");

        Usuario u = new Usuario("Eliminar", "pass");
        u = DelegadoDelNegocio.crearEmpleado(u);
        log.debug("Creado: " + u.toString());

        Boolean resutl = DelegadoDelNegocio.eliminarEmpleado(u);
        log.info("Eliminado: " + resutl);
        assertTrue(resutl);

        assertNull(DelegadoDelNegocio.buscarEmpleadoByID(u.getId()));


    }



    @Test
    void listarEmpleados() {
        log.info("ListarUsersTest");

        List lista = DelegadoDelNegocio.listarEmpleados();
        assertNotNull(lista);

    }
*/



    @ParameterizedTest(name = "#{index} con [{arguments}]")
    @ValueSource(strings = { "Rodrigo", "Claudia", "Antonio" })
    void saludar(String nombre) {

        log.info("nombre = [" + nombre + "]");

        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";


        String saludo = Delegado_Empleado.getInstance().saludar(nombre);

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

        String saludo = Delegado_Empleado.getInstance().saludar(nombre);

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
        Boolean result = Delegado_Empleado.getInstance().loginEmpleado(email, pass);

        log.debug("result = '" + result + "'");
        assertTrue(result);

    }

    @Test
    void loginParamErroneosTest() {

        log.info("Login email erroneo");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado("kajsdnflaf", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void loginEmpleadoInexistenteTest() {

        log.info("Login empleado inexistente");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado("kajsdnflaf@gmail.com", "1234");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void loginEmailNulloVacioTest() {

        log.info("Login email null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado(null, "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());


        log.info("Login email vacio");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado("", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());


    }

    @Test
    void loginPassNulloVacioTest() {

        log.info("Login pass null");
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado("kajsdnflaf", null);
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

        log.info("Login pass vacia");
        Throwable ex2 = assertThrows(EmpleadoException.class, () -> {

            boolean login = Delegado_Empleado.getInstance().loginEmpleado("kajsdnflaf", "");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());

    }



    /******************************************************************
     *******************   TEST BUSCAR BY EMAIL   *********************
     ******************************************************************/

    @ParameterizedTest
    @CsvSource({"Administrador, 1234, ADMIN", "rodri, 1234, EMPLEADO", "emple,1234, EMPLEADO"})
    void buscarByEmail(String nombre, String pass, String rol) throws EmpleadoException {
        Empleado nuevo, e1 = new EmpleadoTCompleto(nombre, pass, Rol.valueOf(rol));
        String email = e1.getEmail();

        log.info("Creando empleado");
        nuevo = Delegado_Empleado.getInstance().crearEmpleado(e1);

        log.info("buscnado empleado");
        e1 = Delegado_Empleado.getInstance().buscarByEmail(email);

        assertTrue(e1.equalsWithOutVersion(nuevo));

        Delegado_Empleado.getInstance().eliminarEmpleado(nuevo);

    }

    //@Test
    //void pruebaNuevoBuscarByEmail() throws EmpleadoException {
    public static void main(String[] args) throws EmpleadoException {

        Empleado nuevo = null, e1 = new EmpleadoTCompleto("admin", "1234", Rol.ADMIN);
        String email = e1.getEmail();

        log.info("buscnado empleado");
        e1 = Delegado_Empleado.getInstance().buscarByEmail(email);

        log.debug("e1 = '" + e1 + "'");
        //assertTrue(e1.equalsWithOutVersion(nuevo));

        Delegado_Empleado.getInstance().eliminarEmpleado(nuevo);

    }

    @Test
    void buscarByEmailInexistente() throws EmpleadoException {


        Empleado e2 = Delegado_Empleado.getInstance().buscarByEmail("emailInexistente@gmail.com");

        assertNull(e2);

        Delegado_Empleado.getInstance().eliminarEmpleado(e2);


    }



    @ParameterizedTest
    @MethodSource("InvalidEmailProvider")
    void buscarByEmailIncorrecto(String[] Email) {


        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            for (String temp : Email) {
                Delegado_Empleado.getInstance().buscarByEmail(temp);
            }


        });

        log.info("Excepcion capturada:" + ex1.getMessage());


    }

    @Test
    void buscarByEmailVacio() {

        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            Empleado e2 = Delegado_Empleado.getInstance().buscarByEmail("");


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void buscarByEmailNull() {
        Throwable ex1 = assertThrows(EmpleadoException.class, () -> {

            Empleado e2 = Delegado_Empleado.getInstance().buscarByEmail(null);


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
package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Rol;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioFieldNullException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioLoginErroneo;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl.Broker_SA_UsuarioImpl;
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

import static org.junit.jupiter.api.Assertions.*;

class Broker_SA_UsuarioTest {


    private static Broker_SA_UsuarioImpl b;

    final static Logger log = LoggerFactory.getLogger(Broker_SA_UsuarioTest.class);

    private Usuario e1;



    /******************************************************************
     **********************   METODO INICIALES   **********************
     ******************************************************************/

    @BeforeAll
    static void initBroker() {
        log.info("Creando SA...");
        b = new Broker_SA_UsuarioImpl();
    }

    @BeforeEach
    void iniciarContexto() throws UsuarioException {
        e1 = new Usuario("usuario", "1234", Rol.valueOf("USUARIO"));
        log.info("Creando usuario ");
        if (b.buscarByEmail(e1.getEmail()) == null) {
            e1 = b.crearUsuario(e1);
        }
    }


    @AfterEach
    void finalizarContexto() {


        log.info("Eliminado usuario");
        b.eliminarUsuario(e1);
    }


    /******************************************************************
     ********************   TEST CREAR USUARIO   *********************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"Admin, 1234, ADMIN", "rodri, 1234, USUARIO"})
    void crearUsuario(String nombre, String pass, String rol) throws UsuarioException {

        Usuario e1 = new Usuario(nombre, pass, Rol.valueOf(rol));
        Usuario usuarioCreado = b.crearUsuario(e1);

        assertNotNull(usuarioCreado);

        e1.setId(usuarioCreado.getId());
        assertTrue(usuarioCreado.equalsWithOutVersion(e1));


        b.eliminarUsuario(usuarioCreado);
    }


    @Test
    void crearUsuarioExistente() throws UsuarioException {

        /*Usuario e1 = new Usuario("juan", "1234", Rol.valueOf("USUARIO"));

        log.info("Creando usuario 1");
        e1 = b.crearUsuario(e1);*/


        Throwable exception = assertThrows(UsuarioYaExisteExcepcion.class, () -> {

            Usuario e2 = e1;

            log.info("Creando usuario 2");
            e2 = b.crearUsuario(e2);

        });


        /*b.eliminarUsuario(e1);*/
    }

    @Test
    void crearUsuarioNull() {


        Throwable exception = assertThrows(UsuarioException.class, () -> {
            Usuario usuarioCreado;
            usuarioCreado = b.crearUsuario(null);

            assertNull(usuarioCreado);

        });

    }


    @Test
    void crearUsuarioVacio() {

        Throwable exception = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario usuarioCreado = b.crearUsuario(new Usuario());

        });

        log.info("Excepcion capturada:" + exception.getMessage());
    }


    @Test
    void crearUsuarioEmailNull() {


        log.info("forzando email = null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            e1.setEmail(null);
            log.debug("e1= " + e1);
            Usuario usuarioCreado = b.crearUsuario(e1);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void crearUsuarioEmailVacio() {


        log.info("forzando email = ''");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            e1.setEmail("");
            log.debug("e1= " + e1);
            Usuario usuarioCreado = b.crearUsuario(e1);

        });
        log.info("Excepcion capturada:" + ex2.getMessage());


    }




    /******************************************************************
     *******************   TEST BUSCAR USUARIO   *********************
     ******************************************************************/


/*
    @Test
    void buscarUsuarioByID() throws UsuarioException {
        log.info("BuscarUserTest");
        Usuario e = new Usuario("test2", "1234");
        Usuario nuevo = null;

        nuevo = b.buscarByEmail(e.getEmail());
        if (nuevo == null) {
            //nuevo = b.crearUsuario(e);
        }

        Usuario userB = b.buscarByID(nuevo.getId());

        log.info(userB.toString());


        assertNotNull(userB);
        assertEquals(nuevo.getId(), userB.getId());
        assertEquals(nuevo.getNombre(), userB.getNombre());

        b.eliminarUsuario(nuevo);

    }

    @Test
    void eliminarUsuario() throws UsuarioException {
        log.info("EliminarUser Test");

        Usuario u = new Usuario("Eliminar", "pass");


        if (!b.buscarByEmail(u.getEmail()).equalsWithOutVersion(u)) {
            //u = b.crearUsuario(u);
        }


        assertTrue(b.eliminarUsuario(u));

        assertNull(b.buscarByID(u.getId()));

        b.eliminarUsuario(u);
    }


    @Test
    void listarUsuarios() {
        log.info("ListarUsersTest");

        assertNotNull(b.listarUsuarios());

        log.info("************************************************************");
        b.listarUsuarios().stream().forEach((user ->
                log.debug("user = '" + user + "'")
        ));

    }

    @Test
    void saludo() {
        log.info("---- SA_UsuarioTest.saludo ---- ");

        String nombre = "Rodrigo";
        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";

        assertNotNull(b.saludar(nombre));

        assertTrue(b.saludar(nombre).equals(str));
    }
    */



    /******************************************************************
     ********************   TEST LOGIN USUARIO   *********************
     ******************************************************************/



    //@ParameterizedTest(name = "-> {0}, {1}")
    //@CsvSource({"usuario, 1234, USUARIO", "admin, 1234, ADMIN"})
    @Test
    void loginTest() throws UsuarioException {
        String email = e1.getEmail();
        String pass = e1.getPassword();


        log.info("Login: {email='" + email + ", pass='" + pass + "'}");
        Boolean result = b.loginUsuario(email, pass);

        log.debug("result = '" + result + "'");
        assertTrue(result);

    }

    @Test
    void loginParamErroneosTest() {

        log.info("Login email erroneo");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = b.loginUsuario("kajsdnflaf", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void loginUsuarioInexistenteTest() {

        log.info("Login usuario inexistente");
        Throwable ex1 = assertThrows(UsuarioLoginErroneo.class, () -> {

            boolean login = b.loginUsuario("kajsdnflaf@gmail.com", "1234");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void loginEmailNulloVacioTest() {

        log.info("Login email null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = b.loginUsuario(null, "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());


        log.info("Login email vacio");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = b.loginUsuario("", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());


    }

    @Test
    void loginPassNulloVacioTest() {

        log.info("Login pass null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = b.loginUsuario("kajsdnflaf", null);
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

        log.info("Login pass vacia");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = b.loginUsuario("kajsdnflaf", "");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());

    }



    /******************************************************************
     *******************   TEST BUSCAR BY EMAIL   *********************
     ******************************************************************/

    @ParameterizedTest
    @CsvSource({"Admin, 1234, ADMIN", "rodri, 1234, USUARIO"})
    void buscarByEmail(String nombre, String pass, String rol) throws UsuarioException {
        Usuario nuevo, e1 = new Usuario(nombre, pass, Rol.valueOf(rol));
        String email = e1.getEmail();

        log.info("Creando usuario");
        nuevo = b.crearUsuario(e1);

        log.info("buscnado usuario");
        e1 = b.buscarByEmail(email);

        assertTrue(e1.equalsWithOutVersion(nuevo));

        b.eliminarUsuario(nuevo);

    }

    @Test
    void buscarByEmailInexistente() throws UsuarioException {


        Usuario e2 = b.buscarByEmail("emailInexistente@gmail.com");

        assertNull(e2);

        b.eliminarUsuario(e2);


    }



    @ParameterizedTest
    @MethodSource("InvalidEmailProvider")
    void buscarByEmailIncorrecto(String[] Email) {


        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            for (String temp : Email) {
                b.buscarByEmail(temp);
            }


        });

        log.info("Excepcion capturada:" + ex1.getMessage());


    }

    @Test
    void buscarByEmailVacio() {

        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario e2 = b.buscarByEmail("");


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void buscarByEmailNull() {
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario e2 = b.buscarByEmail(null);


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }





    /******************************************************************
     *********************   METODOS AUXILIARES   *********************
     ******************************************************************/



    public static Object[][] InvalidEmailProvider() {
        return EmailValidatorTest.InvalidEmailProvider();
    }


    @Test
    void buscarUnEmple() throws UsuarioException {

        log.info(b.buscarByEmail("administrador@gmail.com").toString());
    }

}
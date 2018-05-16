package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.FactoriaSA;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Rol;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioFieldNullException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioLoginErroneo;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioYaExisteExcepcion;
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

class SA_UsuarioImplTest {

    static SA_Usuario sa;

    private Usuario e1;

    final static Logger log = LoggerFactory.getLogger(SA_UsuarioImplTest.class);


    /*******************************************************************
     **********************   METODOS INICIALES   **********************
     *******************************************************************/

    @BeforeAll
    static void initSA() {
        log.info("Creando SA...");
        sa = FactoriaSA.getInstance().crearSA_Usuario();
    }

    @BeforeEach
    void iniciarContexto() throws UsuarioException {
        e1 = new Usuario("usuario", "1234", Rol.valueOf("USUARIO"));
        log.info("Creando usuario ");
        Usuario aux = sa.buscarByEmail(e1.getEmail());
        if ( aux == null) {
            e1 = sa.crearUsuario(e1);
        }else{
            e1 = aux;
        }
    }


    @AfterEach
    void finalizarContexto() {

       /* assertFalse(sa.transactionIsActive(), "Transacción no cerrada");

        assertFalse(sa.emIsOpen(), "Entity Manager no cerrado");
*/
        log.info("Eliminado usuario");
        sa.eliminarUsuario(e1);
    }


    /******************************************************************
     ********************   TEST CREAR USUARIO   *********************
     ******************************************************************/


    @ParameterizedTest
    @CsvSource({"Admin, 1234, ADMIN", "rodri, 1234, USUARIO"})
    void crearUsuario(String nombre, String pass, String rol) throws UsuarioException {

        Usuario e1 = new Usuario(nombre, pass, Rol.valueOf(rol));
        Usuario usuarioCreado = sa.crearUsuario(e1);

        assertNotNull(usuarioCreado);

        e1.setId(usuarioCreado.getId());
        assertTrue(usuarioCreado.equalsWithOutVersion(e1));


        sa.eliminarUsuario(usuarioCreado);
    }


    @Test
    void crearUsuarioExistente() throws UsuarioException {

        /*Usuario e1 = new Usuario("juan", "1234", Rol.valueOf("USUARIO"));

        log.info("Creando usuario 1");
        e1 = sa.crearUsuario(e1);*/


        Throwable exception = assertThrows(UsuarioYaExisteExcepcion.class, () -> {

            Usuario e2 = e1;

            log.info("Creando usuario 2");
            e2 = sa.crearUsuario(e2);

        });


        /*sa.eliminarUsuario(e1);*/
    }

    @Test
    void crearUsuarioNull() {


        Throwable exception = assertThrows(UsuarioException.class, () -> {
            Usuario usuarioCreado;
            usuarioCreado = sa.crearUsuario(null);

            assertNull(usuarioCreado);

        });

        log.error("----  EXCEPCION! ----", exception);

    }


    @Test
    void crearUsuarioVacio() {

        Throwable exception = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario usuarioCreado = sa.crearUsuario(new Usuario());

        });

        log.info("Excepcion capturada:" + exception.getMessage());
    }


    @Test
    void crearUsuarioEmailNull() {


        log.info("forzando email = null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            e1.setEmail(null);
            log.debug("e1= " + e1);
            Usuario usuarioCreado = sa.crearUsuario(e1);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void crearUsuarioEmailVacio() {


        log.info("forzando email = ''");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            e1.setEmail("");
            log.debug("e1= " + e1);
            Usuario usuarioCreado = sa.crearUsuario(e1);

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

        nuevo = sa.buscarByEmail(e.getSiglas());
        if (nuevo == null) {
            //nuevo = sa.crearUsuario(e);
        }

        Usuario userB = sa.buscarByID(nuevo.getId());

        log.info(userB.toString());


        assertNotNull(userB);
        assertEquals(nuevo.getId(), userB.getId());
        assertEquals(nuevo.getNombre(), userB.getNombre());

        sa.eliminarUsuario(nuevo);

    }

    @Test
    void eliminarUsuario() throws UsuarioException {
        log.info("EliminarUser Test");

        Usuario u = new Usuario("Eliminar", "pass");


        if (!sa.buscarByEmail(u.getSiglas()).equalsWithOutVersion(u)) {
            //u = sa.crearUsuario(u);
        }


        assertTrue(sa.eliminarUsuario(u));

        assertNull(sa.buscarByID(u.getId()));

        sa.eliminarUsuario(u);
    }


    @Test
    void listarUsuarios() {
        log.info("ListarUsersTest");

        assertNotNull(sa.listarUsuarios());

        log.info("************************************************************");
        sa.listarUsuarios().stream().forEach((user ->
                log.debug("user = '" + user + "'")
        ));

    }

    @Test
    void saludo() {
        log.info("---- SA_UsuarioTest.saludo ---- ");

        String nombre = "Rodrigo";
        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";

        assertNotNull(sa.saludar(nombre));

        assertTrue(sa.saludar(nombre).equals(str));
    }
    */



    /******************************************************************
     ********************   TEST LOGIN USUARIO   *********************
     ******************************************************************/



    //@ParameterizedTest(name = "-> {0}, {1}")
    //@CsvSource({"usuario, 1234, USUARIO", "admin, 1234, ADMIN"})
    @Test
    void loginUsuarioTest() throws UsuarioException {
        String email = e1.getEmail();
        String pass = e1.getPassword();


        log.info("Login: {email='" + email + ", pass='" + pass + "'}");
        Boolean result = sa.loginUsuario(email, pass);

        log.debug("result = '" + result + "'");
        assertTrue(result);

    }

    @Test
    void loginParamErroneosTest() {

        log.info("Login email erroneo");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = sa.loginUsuario("kajsdnflaf", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void loginUsuarioInexistenteTest() {

        log.info("Login usuario inexistente");
        Throwable ex1 = assertThrows(UsuarioLoginErroneo.class, () -> {

            boolean login = sa.loginUsuario("kajsdnflaf@gmail.com", "1234");
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }

    @Test
    void loginEmailNulloVacioTest() {

        log.info("Login email null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = sa.loginUsuario(null, "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());


        log.info("Login email vacio");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = sa.loginUsuario("", "1234");

            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex2.getMessage());


    }

    @Test
    void loginPassNulloVacioTest() {

        log.info("Login pass null");
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = sa.loginUsuario("kajsdnflaf", null);
            assertFalse(login);

        });

        log.info("Excepcion capturada:" + ex1.getMessage());

        log.info("Login pass vacia");
        Throwable ex2 = assertThrows(UsuarioFieldNullException.class, () -> {

            boolean login = sa.loginUsuario("kajsdnflaf", "");
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
        nuevo = sa.crearUsuario(e1);

        log.info("buscnado usuario");
        e1 = sa.buscarByEmail(email);

        assertTrue(e1.equalsWithOutVersion(nuevo));

        sa.eliminarUsuario(nuevo);

    }

    @Test
    void buscarByEmailInexistente() throws UsuarioException {


        Usuario e2 = sa.buscarByEmail("emailInexistente@gmail.com");

        assertNull(e2);

        sa.eliminarUsuario(e2);


    }



    @ParameterizedTest
    @MethodSource("InvalidEmailProvider")
    void buscarByEmailIncorrecto(String[] Email) {


        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            for (String temp : Email) {
                sa.buscarByEmail(temp);
            }


        });

        log.info("Excepcion capturada:" + ex1.getMessage());


    }

    @Test
    void buscarByEmailVacio() {

        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario e2 = sa.buscarByEmail("");


        });

        log.info("Excepcion capturada:" + ex1.getMessage());

    }


    @Test
    void buscarByEmailNull() {
        Throwable ex1 = assertThrows(UsuarioFieldNullException.class, () -> {

            Usuario e2 = sa.buscarByEmail(null);


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
    void listarUsuarios() {
        log.info("ListarUsersTest");

        assertNotNull(sa.listarUsuarios());

        log.info("************************************************************");
        sa.listarUsuarios().stream().forEach((user ->
                log.debug("user = '" + user + "'")
        ));

    }



  /*  @Test
    void buscarUnEmple() throws UsuarioException {

        log.info(sa.buscarByEmail("administrador@gmail.com").toString());
    }*/
}
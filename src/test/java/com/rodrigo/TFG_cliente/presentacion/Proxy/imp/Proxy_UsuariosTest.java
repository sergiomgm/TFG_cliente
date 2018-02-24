package com.rodrigo.TFG_cliente.presentacion.Proxy.imp;

import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.proxy.imp.Proxy_Usuarios;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Proxy_UsuariosTest {


    private final static Logger log = LoggerFactory.getLogger(Proxy_UsuariosTest.class);

    private static Proxy_Usuarios proxy;

    @BeforeAll
    static void setUp() {

        try {
            proxy = new Proxy_Usuarios();
        } catch (ProxyException e) {
            log.error("Error al crear Proxy_Usuarios");
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            fail("Error al crear Proxy_Usuarios");
        }

    }

    @AfterAll
    static void tearDown() {

    }

    @Test
    void initProxy() {

        try {
            proxy.initProxy();
        } catch (ProxyException e) {

            log.error("Error al iniaciar Proxy_Usuarios");
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            fail("Error al iniciar Proxy_Usuarios");
        }

    }

    @Test
    void crearUsuario() {
        log.info("crearUserTest");
        Usuario user = new Usuario("juan", "1234");

        Usuario nuevo = proxy.crearUsuario(user);

        log.debug(user.toString());
        log.debug(nuevo.toString());

        assertNotNull(nuevo);
        assertNotNull(nuevo.getId());

        assertTrue(nuevo.getId() instanceof Long);
        assertEquals(nuevo.getNombre().toString(), user.getNombre().toString());
        assertEquals(nuevo.getPassword().toString(), user.getPassword().toString());
    }


    @Test
    void buscarUsuarioByID() {
        log.info("BuscarUserTest");
        Usuario nuevo = proxy.crearUsuario(new Usuario("test2", "1234"));
        Usuario userB = proxy.buscarUsuarioByID(nuevo.getId());

        log.info(userB.toString());



        assertNotNull(userB);
        assertEquals(nuevo.getId(), userB.getId());
        assertEquals(nuevo.getNombre(), userB.getNombre());

    }


    @Test
    void eliminarUsuario() {
        log.info("EliminarUser Test");

        Usuario u = new Usuario("Eliminar", "pass");
        u = proxy.crearUsuario(u);
        log.debug("Creado: " + u.toString());

        Boolean resutl = proxy.eliminarUsuario(u);
        log.info("Eliminado: " + resutl);
        assertTrue(resutl);

        assertNull(proxy.buscarUsuarioByID(u.getId()));


    }



    @Test
    void listarUsuarios() {
        log.info("ListarUsersTest");

        List lista = proxy.listarUsuarios();
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
}
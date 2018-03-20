package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl.SA_UsuarioImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioTest {

    private final static Logger log = LoggerFactory.getLogger(UsuarioTest.class);

    private static Usuario usuario;
    private static Usuario admin;



    @BeforeAll
    static void beforeAll(){

        usuario = new Usuario("usuario", "1234", Rol.USUARIO);
        admin = new Usuario("admin", "1234", Rol.ADMIN);

    }

    @Test
    void equalsWithOutVersion() {

        Usuario usuario2;
        usuario2 = usuario;



        log.debug("usuario = '" + usuario + "'");
        log.debug("usuario2 = '" + usuario2.toString() + "'");
        log.debug("admin = '" + admin + "'");

        log.debug("usuario.equalsWithOutVersion(usuario2)");
        assertTrue(usuario.equalsWithOutVersion(usuario2));

        log.debug("usuario2.equalsWithOutVersion(usuario)");
        assertTrue(usuario2.equalsWithOutVersion(usuario));

        log.debug("usuario.equalsWithOutVersion(usuario)");
        assertTrue(usuario.equalsWithOutVersion(usuario));

        log.debug("usuario.equalsWithOutVersion(admin)");
        assertFalse(usuario.equalsWithOutVersion(admin));

        log.debug("admin.equalsWithOutVersion(usuario)");
        assertFalse(admin.equalsWithOutVersion(usuario));


        Usuario usuario3 = new Usuario("usuario3", "1234", Rol.USUARIO);
        log.debug("usuario3 = '" + usuario3 + "'");

        log.debug("usuario.equalsWithOutVersion(usuario3)");
        assertFalse(usuario.equalsWithOutVersion(usuario3));

        log.debug("usuario3.equalsWithOutVersion(usuario)");
        assertFalse(usuario3.equalsWithOutVersion(usuario));

        log.debug("usuario3.equalsWithOutVersion(usuario3)");
        assertTrue(usuario3.equalsWithOutVersion(usuario3));


    }

    @Test
    void equalsWithOutVersionConBBDD() throws UsuarioException {
        log.info("creando SA_Usuario");

        Usuario admin2 = new SA_UsuarioImpl().crearUsuario(admin);
        admin.setId(admin2.getId());

        log.debug("admin2 = '" + admin2 + "'");

        log.debug("admin.equalsWithOutVersion(admin2)");
        assertTrue(admin.equalsWithOutVersion(admin2));

        log.debug("admin2.equalsWithOutVersion(admin)");
        assertTrue(admin2.equalsWithOutVersion(admin));

        Boolean resutl = new SA_UsuarioImpl().eliminarUsuario(admin);
        log.debug("resutl = '" + resutl + "'");
    }


    @Test
    void constructoraUsuarioTest() {
        Usuario user = new Usuario("rodri", "1234", Rol.valueOf("ADMIN"));

        log.debug("user = '" + user + "'");;

    }
}
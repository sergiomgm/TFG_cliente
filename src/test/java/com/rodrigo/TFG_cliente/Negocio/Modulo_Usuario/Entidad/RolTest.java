package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
class RolTest {

    private static Rol rolUser;
    private static Rol rolAdmin;

    private final static Logger log = LoggerFactory.getLogger(RolTest.class);


    @BeforeAll
    static void beforeAll(){
        rolUser = Rol.USUARIO;
        rolAdmin = Rol.ADMIN;

    }

    @Test
    void toStringTest() {

        log.info("rolUser.toString() = '" + rolUser.toString() + "'");
        assertEquals(rolUser.toString(), "USUARIO");


        log.info("rolAdmin.toString() = '" + rolAdmin.toString() + "'");
        assertEquals(rolAdmin.toString(), "ADMIN");

    }
}
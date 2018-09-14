package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class UsuarioLoginErroneo extends UsuarioException {
    public UsuarioLoginErroneo(String message) {
        super(message);
    }
}

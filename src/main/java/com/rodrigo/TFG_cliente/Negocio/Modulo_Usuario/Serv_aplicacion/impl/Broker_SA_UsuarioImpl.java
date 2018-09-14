package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl;


import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.IBroker_SA_Usuario;

import javax.jws.WebParam;
import java.util.List;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class Broker_SA_UsuarioImpl implements IBroker_SA_Usuario {

    public Broker_SA_UsuarioImpl() {}

    @Override
    public Usuario crearUsuario(@WebParam(name="Usuario") Usuario empleadoNuevo) throws UsuarioException {

        return new SA_UsuarioImpl().crearUsuario(empleadoNuevo);
        //return null;
    }


    public Usuario buscarUsuarioByID(@WebParam(name="id") Long id) {
        return new SA_UsuarioImpl().buscarByID(id);
    }

    public boolean eliminarUsuario(@WebParam(name="Usuario") Usuario empleadoEliminar) {

        return new SA_UsuarioImpl().eliminarUsuario(empleadoEliminar);
    }

    public List<Usuario> listarUsuarios() {

        return new SA_UsuarioImpl().listarUsuarios();
    }

    @Override
    public String saludar(@WebParam(name="nombre") String nombre) {
        return new SA_UsuarioImpl().saludar(nombre);
    }



    public boolean loginUsuario(String email, String pass) throws UsuarioException {
        return new SA_UsuarioImpl().loginUsuario(email, pass);
    }

    @Override
    public Usuario buscarByEmail(String email) throws UsuarioException {
        return new SA_UsuarioImpl().buscarByEmail(email);
    }

}

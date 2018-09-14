package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;

import java.util.List;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public interface SA_Usuario {

    Usuario crearUsuario(Usuario empleadoNuevo) throws UsuarioException;

    Usuario buscarByID(Long id);

    boolean eliminarUsuario(Usuario empleadoEliminar);

    List<Usuario> listarUsuarios();

    String saludar(String nombre);

    Boolean loginUsuario(String email, String pass) throws UsuarioException;

    Usuario buscarByEmail(String email) throws UsuarioException;

}

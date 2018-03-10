package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioFieldNullException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;

import java.util.List;

/**
 * Created by Rodrigo de Miguel on 06/05/2017.
 */
public interface SA_Usuario {

    Usuario crearUsuario(Usuario empleadoNuevo) throws UsuarioException;

    Usuario buscarByID(Long id);

    boolean eliminarUsuario(Usuario empleadoEliminar);

    List<Usuario> listarUsuarios();

    String saludar(String nombre);

    Boolean loginUsuario(String email, String pass) throws UsuarioException;

    Usuario buscarByEmail(String email) throws UsuarioException, UsuarioFieldNullException;

}

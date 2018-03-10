package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

/*
@WebService
*/
public interface IBroker_SA_Usuario {

    @WebMethod(operationName="crearUsuario")
    public Usuario crearUsuario(@WebParam(name = "Usuario") Usuario empleadoNuevo) throws UsuarioException;

    @WebMethod(operationName="buscarByID")
    public Usuario buscarUsuarioByID(@WebParam(name = "id") Long id);


    @WebMethod(operationName="eliminarUsuario")
    public boolean eliminarUsuario(@WebParam(name = "Usuario") Usuario empleadoEliminar) ;


    @WebMethod(operationName="listarUsuarios")
    public List<Usuario> listarUsuarios();

    @WebMethod(operationName="saludar")
    public String saludar(@WebParam(name = "nombre") String nombre);

    @WebMethod(operationName="loginUsuario")
    public boolean loginUsuario(String email, String pass) throws UsuarioException;

    @WebMethod(operationName="buscarByEmail")
    public Usuario buscarByEmail(String email) throws UsuarioException;



}


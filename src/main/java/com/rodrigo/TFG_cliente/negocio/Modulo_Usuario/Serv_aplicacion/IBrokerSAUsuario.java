package com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Serv_aplicacion;


import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Entidad.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(
        targetNamespace = "http://imp.Serv_aplicacion.Modulo_Usuario.Negocio.TFG_server.rodrigo.com/",
        name = "BrokerSAUsuario")
public interface IBrokerSAUsuario {

    @WebMethod(operationName="crearUsuario")
    public Usuario crearUsuario(@WebParam(name = "Usuario") Usuario usuarioNuevo);

    @WebMethod(operationName="buscarUsuarioByID")
    public Usuario buscarUsuarioByID(@WebParam(name = "id") Long id);


    @WebMethod(operationName="eliminarUsuario")
    public boolean eliminarUsuario(@WebParam(name = "Usuario") Usuario usuarioEliminar) ;


    @WebMethod(operationName="listarUsuarios")
    public List<Usuario> listarUsuarios();

    @WebMethod(operationName="saludar")
    public String saludar(@WebParam(name="nombre") String nombre);

}


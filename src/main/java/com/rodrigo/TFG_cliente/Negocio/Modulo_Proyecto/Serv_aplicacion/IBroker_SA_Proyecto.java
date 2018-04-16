package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Serv_aplicacion;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(
        targetNamespace = "http://impl.Serv_aplicacion.Modulo_Proyecto.Negocio.TFG_server.rodrigo.com/",
        name = "Broker_SA_ProyectoImpl")
public interface IBroker_SA_Proyecto {

    @WebMethod(operationName="crearProyecto")
    public Proyecto crearProyecto(@WebParam(name = "proyectoNuevo") Proyecto proyectoNuevo) throws ProyectoException;

    @WebMethod(operationName="buscarByID")
    public Proyecto buscarByID(@WebParam(name = "id") Long id);


    @WebMethod(operationName="eliminarProyecto")
    public boolean eliminarProyecto(@WebParam(name = "proyectoEliminar") Proyecto proyectoEliminar) ;


    @WebMethod(operationName="listarProyectos")
    public List<Proyecto> listarProyectos();


}


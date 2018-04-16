package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado;

import com.rodrigo.TFG_cliente.Negocio.DelegadoDelNegocio;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.impl.Delegado_ProyectoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Delegado_Proyecto implements DelegadoDelNegocio {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Proyecto.class);

    protected final String URL_WSDL = HOST + ":" + PORT + APP_URI +  "/SA_Proyecto?wsdl";

    protected final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Proyecto.Negocio.TFG_server.rodrigo.com/";

    protected final String SERVICE_NAME = "Broker_SA_ProyectoImpl";



    private static Delegado_Proyecto ourInstance;

    static {
        try {
            ourInstance = new Delegado_ProyectoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Proyecto getInstance() {
        return ourInstance;
    }


    public abstract Proyecto crearProyecto( Proyecto proyectoNuevo) throws ProyectoException;

    public abstract Proyecto buscarByID(Long id);


    public abstract boolean eliminarProyecto(Proyecto proyectoEliminar) ;


    public abstract List<Proyecto> listarProyectos();

}

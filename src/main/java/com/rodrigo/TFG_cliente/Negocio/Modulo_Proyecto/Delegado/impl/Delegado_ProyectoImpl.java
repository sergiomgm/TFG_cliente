package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TEmpleadoProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoYaExistenteException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Serv_aplicacion.IBroker_SA_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Delegado_ProyectoImpl extends Delegado_Proyecto {

    private final static Logger log = LoggerFactory.getLogger(Delegado_ProyectoImpl.class);

    private IBroker_SA_Proyecto portProyecto;


    public Delegado_ProyectoImpl() throws ProxyException {
        log.info("Creando DelegadoDelNegocio");


        log.debug("Creando Qname del servicio");
        QName SERVICE_EMPLEADO = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.debug("Creando URL_WSDL de enlace");
        log.debug("URL_WSDL: " + URL_WSDL);
        URL wsdlURLProyectos;
        try {
            wsdlURLProyectos = new URL(URL_WSDL);
        } catch (MalformedURLException e) {
            log.error("Error al crear el WSDL", e);

            throw new ProxyException("Error al conectar con servicio Proyecto");
        }


        log.debug("Creando servicio Proyecto");
        Service ssProyectos = Service.create(wsdlURLProyectos, SERVICE_EMPLEADO);


        log.debug("Creando puerto de enlace para el servicio");
        portProyecto = ssProyectos.getPort(IBroker_SA_Proyecto.class);


        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TProyecto crearProyecto(TProyecto proyectoNuevo) throws ProyectoYaExistenteException, ProyectoFieldInvalidException, ProyectoException {
        return portProyecto.crearProyecto(proyectoNuevo);
    }

    @Override
    public TProyectoCompleto buscarByID(Long id) throws ProyectoFieldInvalidException, ProyectoException {
        return portProyecto.buscarByID(id);
    }

    @Override
    public TProyectoCompleto buscarByNombre( String nombre) throws ProyectoFieldInvalidException, ProyectoException{
        return portProyecto.buscarByNombre(nombre);

    }


    @Override
    public boolean eliminarProyecto(TProyecto proyectoEliminar) throws ProyectoFieldInvalidException, ProyectoException {
        return portProyecto.eliminarProyecto(proyectoEliminar);
    }

    @Override
    public List<TProyecto> listarProyectos() {
        return portProyecto.listarProyectos();
    }

    public TEmpleadoProyecto añadirEmpleadoAProyecto(TEmpleado e, TProyecto p, int horas) {
        return portProyecto.añadirEmpleadoAProyecto(e, p, horas);
    }

}

package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TEmpleadoProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoYaExistenteException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Serv_aplicacion.IBroker_SA_Proyecto;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class Delegado_ProyectoImpl extends Delegado_Proyecto {

    private final static Logger log = LoggerFactory.getLogger(Delegado_ProyectoImpl.class);



    private String HOST = "https://localhost" ;
//    String HOST = "https://127.0.0.1" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";


    private final String URL_WSDL = "http://localhost:8080/TFG_server/wsdl/SA_Proyecto.wsdl";

    private final String URL_SERVICE = HOST + ":"+ PORT + APP_URI + "/SA_Proyecto";

    private final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Proyecto.Negocio.TFG_server.rodrigo.com/";

    private final String SERVICE_NAME = "Broker_SA_ProyectoImpl";


    private IBroker_SA_Proyecto portProyecto;



    public Delegado_ProyectoImpl() throws ProxyException {
        log.info("Creando DelegadoDelNegocio");
        /*
        log.debug("Creando Qname del servicio");
        QName SERVICE_PROYECTO = new QName(NAMESPACE_URI, SERVICE_NAME);

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
        Service ssProyectos = Service.create(wsdlURLProyectos, SERVICE_PROYECTO);


        log.debug("Creando puerto de enlace para el servicio");
        portProyecto = ssProyectos.getPort(IBroker_SA_Proyecto.class);



        log.info("Asignando usuario y contraseña");
        //el servicio 2 requiere usuario y contraseña
        Map<String, Object> req_ctx2= ((BindingProvider) portProyecto).getRequestContext();
        req_ctx2.put(BindingProvider.USERNAME_PROPERTY, "usuario");
        req_ctx2.put(BindingProvider.PASSWORD_PROPERTY, "contra");

        req_ctx2.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_SERVICE);
		*/
        
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cxf.xml");
		
		portProyecto = (IBroker_SA_Proyecto) context.getBean("SA_Proyecto");
		
		((BindingProvider) portProyecto).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "usuario");
		((BindingProvider) portProyecto).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "contra");

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
    public boolean eliminarProyecto(Long id) throws ProyectoFieldInvalidException, ProyectoException {
        return portProyecto.eliminarProyecto(id);
    }

    @Override
    public List<TProyecto> listarProyectos() {
        return portProyecto.listarProyectos();
    }

    @Override
    public TEmpleadoProyecto agregarEmpleadoAProyecto(TEmpleado e, TProyecto p, int horas) throws EmpleadoException, ProyectoException {
        return portProyecto.agregarEmpleadoAProyecto(e, p, horas);
    }

    @Override
    public boolean eliminarEmpleadoAProyecto(Long idEmple, Long idProy) throws ProyectoException, EmpleadoException{
        return portProyecto.eliminarEmpleadoAProyecto(idEmple, idProy);

    }

}

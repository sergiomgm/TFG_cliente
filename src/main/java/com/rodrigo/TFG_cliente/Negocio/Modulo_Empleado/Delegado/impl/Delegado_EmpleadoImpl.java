package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Delegado_EmpleadoImpl extends Delegado_Empleado {

    private final static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImpl.class);

    private IBroker_SA_Empleado portEmpleados;


    public Delegado_EmpleadoImpl() throws ProxyException {
        log.info("Creando DelegadoDelNegocio");


        log.debug("Creando Qname del servicio");
        QName SERVICE_EMPLEADO = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.debug("Creando URL_WSDL de enlace");
        log.debug("URL_WSDL: " + URL_WSDL);
        URL wsdlURLEmpleados;
        try {
            wsdlURLEmpleados = new URL(URL_WSDL);
        } catch (MalformedURLException e) {
            log.error("Error al crear el WSDL", e);

            throw new ProxyException("Error al conectar con servicio Empleado");
        }


        log.debug("Creando servicio Empleado");
        Service ssEmpleados = Service.create(wsdlURLEmpleados, SERVICE_EMPLEADO);


        log.debug("Creando puerto de enlace para el servicio");
        portEmpleados = ssEmpleados.getPort(IBroker_SA_Empleado.class);


        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public Empleado crearEmpleado(Empleado empleadoNuevo) throws EmpleadoException {
        return portEmpleados.crearEmpleado(empleadoNuevo);
    }

    @Override
    public Empleado buscarByID(Long id) {
        return portEmpleados.buscarByID(id);
    }

    @Override
    public boolean eliminarEmpleado(Empleado empleadoEliminar) {
        return portEmpleados.eliminarEmpleado(empleadoEliminar);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return portEmpleados.listarEmpleados();
    }

    @Override
    public String saludar(String nombre) {
        return this.portEmpleados.saludar(nombre);
    }

    @Override
    public boolean loginEmpleado(String email, String pass) throws EmpleadoException {
        return portEmpleados.loginEmpleado(email, pass);
    }

    @Override
    public Empleado buscarByEmail(String email) throws EmpleadoException {
        return portEmpleados.buscarByEmail(email);
    }

}

package com.rodrigo.TFG_cliente.presentacion.Proxy.imp;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.Proxy.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Proxy_Empleado implements Proxy, IBroker_SA_Empleado {

    private final static Logger log = LoggerFactory.getLogger(Proxy_Empleado.class);

    private final String URL_WSDL = HOST + ":" + PORT + APP_URI +  "/SA_Empleado?wsdl";

    private final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Empleado.Negocio.TFG_server.rodrigo.com/";

    private final String SERVICE_NAME = "Broker_SA_EmpleadoImpl";


    private IBroker_SA_Empleado portEmpleados;


    public Proxy_Empleado() throws ProxyException {
        initProxy();
    }

    public void initProxy() throws ProxyException {


        QName SERVICE_USUARIOS = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.debug("preURL");

        URL wsdlURLEmpleados = null;

        try {
            wsdlURLEmpleados = new URL(URL_WSDL);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            throw new ProxyException("Error al conectar con servicio Empleado");
        }


        log.debug("Pre Service ss");
        Service ssEmpleados = Service.create(wsdlURLEmpleados, SERVICE_USUARIOS);
        log.debug("pre port");


        portEmpleados = ssEmpleados.getPort(IBroker_SA_Empleado.class);
        log.debug("Post port");

    }


    @Override
    public Empleado crearEmpleado(Empleado empleadoNuevo) throws EmpleadoException {
        return portEmpleados.crearEmpleado(empleadoNuevo);
    }

    @Override
    public Empleado buscarEmpleadoByID(Long id) {
        return portEmpleados.buscarEmpleadoByID(id);
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

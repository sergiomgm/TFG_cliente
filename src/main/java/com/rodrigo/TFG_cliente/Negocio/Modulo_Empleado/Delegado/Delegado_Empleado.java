package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl.Delegado_EmpleadoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.DelegadoDelNegocio;
import com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Delegado_Empleado implements DelegadoDelNegocio {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Empleado.class);

    protected final String URL_WSDL = HOST + ":" + PORT + APP_URI +  "/SA_Empleado?wsdl";

    protected final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Empleado.Negocio.TFG_server.rodrigo.com/";

    protected final String SERVICE_NAME = "Broker_SA_EmpleadoImpl";



    private static Delegado_Empleado ourInstance;

    static {
        try {
            ourInstance = new Delegado_EmpleadoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Empleado getInstance() {
        return ourInstance;
    }

    public abstract Empleado crearEmpleado(Empleado empleadoNuevo) throws EmpleadoException;

    public abstract Empleado buscarEmpleadoByID(Long id);

    public abstract boolean eliminarEmpleado(Empleado empleadoEliminar);

    public abstract List<Empleado> listarEmpleados();

    public abstract String saludar(String nombre);

    public abstract boolean loginEmpleado(String email, String pass) throws EmpleadoException;

    public abstract Empleado buscarByEmail(String email) throws EmpleadoException;
}

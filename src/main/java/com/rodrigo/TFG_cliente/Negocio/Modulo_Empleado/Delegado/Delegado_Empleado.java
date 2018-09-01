package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl.Delegado_EmpleadoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoLoginErroneo;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Delegado_Empleado  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Empleado.class);
    String HOST = "http://localhost" ;
//    String HOST = "https://127.0.0.1" ;

    String PORT = "8080";
//    String PORT = "8443";

    String APP_URI = "/TFG_server/services";

//    protected final String URL_WSDL = HOST + ":" + PORT + APP_URI + "/SA_Empleado?wsdl";
    protected final String URL_WSDL = "https://localhost" + ":" + 8443 + APP_URI + "/SA_Empleado?wsdl";

    protected final String NAMESPACE_URI = "http://impl.Serv_aplicacion.Modulo_Empleado.Negocio.TFG_server.rodrigo.com/";

    protected final String SERVICE_NAME = "Broker_SA_EmpleadoImpl";


    private static Delegado_Empleado ourInstance;

    static {

//        //for localhost testing only
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//                new javax.net.ssl.HostnameVerifier() {
//
//                    public boolean verify(String hostname,
//                                          javax.net.ssl.SSLSession sslSession) {
//                        log.info("en metodo de control LOCALHOST");
//                        if (hostname.equals("localhost")) {
//                            return true;
//                        }
//                        return false;
//                    }
//                });


        try {
            log.info("Delegado_Empleado.static initializer");
            ourInstance = new Delegado_EmpleadoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Empleado getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract TEmpleadoCompleto crearEmpleado(TEmpleado empleadoNuevo) throws EmpleadoYaExisteExcepcion, EmpleadoFieldInvalidException, EmpleadoException;


    public abstract TEmpleadoCompleto buscarByID(Long id) throws EmpleadoFieldInvalidException, EmpleadoException;


    public abstract boolean eliminarEmpleado(Long id) throws EmpleadoFieldInvalidException, EmpleadoException;


    public abstract List<TEmpleado> listarEmpleados();


    public abstract String saludar(String nombre);


    public abstract boolean loginEmpleado(String email, String pass) throws EmpleadoLoginErroneo, EmpleadoFieldInvalidException, EmpleadoException;


    public abstract TEmpleadoCompleto buscarByEmail(String email) throws EmpleadoFieldInvalidException, EmpleadoException;


    public abstract TEmpleadoCompleto buscarByIDTransfer(Long id) throws EmpleadoFieldInvalidException, EmpleadoException;
}

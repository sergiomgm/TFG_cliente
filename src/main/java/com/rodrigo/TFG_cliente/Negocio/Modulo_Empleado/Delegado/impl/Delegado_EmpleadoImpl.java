package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoLoginErroneo;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Serv_aplicacion.IBroker_SA_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Delegado_EmpleadoImpl extends Delegado_Empleado {

    private static Logger log = LoggerFactory.getLogger(Delegado_EmpleadoImpl.class);

    private IBroker_SA_Empleado portEmpleados;




    public Delegado_EmpleadoImpl() throws ProxyException {
        log.info("Creando Delegado_EmpleadoImpl");

        log.info("Creando Qname del servicio");
        QName SERVICE_EMPLEADO = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.info("Creando URL_WSDL de enlace");
        log.info("URL_WSDL: " + URL_WSDL);
        URL wsdlURLEmpleados;
        try {
//            wsdlURLEmpleados = new URL(URL_WSDL);

            wsdlURLEmpleados = new URL("http://localhost:8080/TFG_server/wsdl/SA_Empleado.wsdl");
            log.info("wsdlURLEmpleados = '" + wsdlURLEmpleados + "'");
        } catch (MalformedURLException e) {
            log.error("Error al crear el WSDL", e);

            throw new ProxyException("Error al conectar con servicio Empleado");
        }


        log.info("Creando servicio Empleado");
        Service ssEmpleados = Service.create(wsdlURLEmpleados, SERVICE_EMPLEADO);


        log.info("Creando puerto de enlace para el servicio");
        portEmpleados =  ssEmpleados.getPort(IBroker_SA_Empleado.class);



        log.info("Asignando usuario y contraseña");
        //el servicio 2 requiere usuario y contraseña
        Map<String, Object> req_ctx2= ((BindingProvider) portEmpleados).getRequestContext();
        req_ctx2.put(BindingProvider.USERNAME_PROPERTY, "usuario");
        req_ctx2.put(BindingProvider.PASSWORD_PROPERTY, "contra");

        String endPoint2= "https://localhost:8443/TFG_server/services/SA_Empleado";
        req_ctx2.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint2);



        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TEmpleadoCompleto crearEmpleado(TEmpleado empleadoNuevo) throws EmpleadoYaExisteExcepcion, EmpleadoFieldInvalidException, EmpleadoException{
        return portEmpleados.crearEmpleado(empleadoNuevo);
    }

    @Override
    public TEmpleadoCompleto buscarByID(Long id) throws EmpleadoException {
        return portEmpleados.buscarByID(id);
    }

    @Override
    public boolean eliminarEmpleado(Long id) throws EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.eliminarEmpleado(id);
    }

    @Override
    public List<TEmpleado> listarEmpleados() {
        return portEmpleados.listarEmpleados();
    }

    @Override
    public String saludar(String nombre) {
        return this.portEmpleados.saludar(nombre);
    }

    @Override
    public boolean loginEmpleado(String email, String pass) throws EmpleadoLoginErroneo, EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.loginEmpleado(email, pass);
    }

    @Override
    public TEmpleadoCompleto buscarByEmail(String email) throws EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.buscarByEmail(email);
    }


    @Override
    public TEmpleadoCompleto buscarByIDTransfer(Long id) throws EmpleadoFieldInvalidException, EmpleadoException {
        return portEmpleados.buscarByIDTransfer(id);
    }
}

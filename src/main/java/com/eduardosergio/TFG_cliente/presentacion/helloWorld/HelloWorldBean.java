package com.eduardosergio.TFG_cliente.presentacion.helloWorld;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.Delegado_HelloWorld;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLogger;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTParcial;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Utils.EmailValidator;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "HelloWorldBean")
@SessionScoped
public class HelloWorldBean implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(HelloWorldBean.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    
    /****************************
     ********** METODOS *********
     ****************************/


    public void salute() {
    	System.out.println(Delegado_HelloWorld.getInstance().salute());
    }



    /****************************
     **** GETTERS AND SETTERS ***
     ****************************/


    public String getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(String viewRequest) {
        this.viewRequest = viewRequest;
    }

}

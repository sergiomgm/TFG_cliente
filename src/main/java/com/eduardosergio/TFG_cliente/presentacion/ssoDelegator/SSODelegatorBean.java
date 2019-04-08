package com.eduardosergio.TFG_cliente.presentacion.ssoDelegator;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.Delegado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "SSODelegatorBean")
@SessionScoped
public class SSODelegatorBean implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(SSODelegatorBean.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;
    
    private AccionVista accionVista = new AccionVista();
    
    private List<TDepartamento> listaDepartamento;

    
    /****************************
     ********** METODOS *********
     ****************************/


    public void salute() {
    	System.out.println(Delegado.getInstance().salute());
    }
    
    public void saludar() {
    	System.out.println(Delegado.getInstance().saludar());
    }
    
    public void salutare() {
    	System.out.println(Delegado.getInstance().salutare());
    }
    
    public String listarDepartamentos() {    	
    	accionVista.setAccion(AccionVista.AccionEnum.ACCION_LISTAR_DEPARTAMENTOS);
    	
    	listaDepartamento = Arrays.asList(Delegado.getInstance().listarDepartamentos());
    	
    	return viewRequest;
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

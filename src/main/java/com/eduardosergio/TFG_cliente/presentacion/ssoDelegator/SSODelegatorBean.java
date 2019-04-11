package com.eduardosergio.TFG_cliente.presentacion.ssoDelegator;



import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

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
    

    
    /****************************
     ********** METODOS *********
     ****************************/


    public void invokeServices() {
    	SMR.getInstance().invokeServices();
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

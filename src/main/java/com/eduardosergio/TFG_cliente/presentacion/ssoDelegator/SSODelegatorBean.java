package com.eduardosergio.TFG_cliente.presentacion.ssoDelegator;



import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@ManagedBean(name = "SSODelegatorBean")
@SessionScoped
public class SSODelegatorBean implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -9064926599359384662L;


	/****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(SSODelegatorBean.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;
    
    

    
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

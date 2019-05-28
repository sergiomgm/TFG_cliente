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

	
	private String user;
	private String pass;
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
    	System.out.println(user + " " + pass);
    	SMR.getInstance().synchronize(this.user, this.pass);
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
    
    public String getUser() {
    	return this.user;
    }
    
    public void setUser(String user) {
    	this.user = user;
    }
    
    public String getPass() {
    	return this.pass;
    }
    
    public void setPass(String pass) {
    	this.pass = pass;
    }


}

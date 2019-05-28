package com.eduardosergio.TFG_cliente.presentacion.holaMundo;

import com.eduardosergio.TFG_cliente.negocio.holaMundo.Delegado.Delegado_HolaMundo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "HolaMundoBean")
@SessionScoped
public class HolaMundoBean implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(HolaMundoBean.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    
    /****************************
     ********** METODOS *********
     ****************************/


    public void saludar() {
    	System.out.println(Delegado_HolaMundo.getInstance().saludar());
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

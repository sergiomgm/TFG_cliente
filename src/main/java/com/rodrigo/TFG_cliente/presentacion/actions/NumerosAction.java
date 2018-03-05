package com.rodrigo.TFG_cliente.presentacion.actions;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.Proxy.imp.Proxy_Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@ManagedBean(name = "numerosAction")
@SessionScoped
public class NumerosAction implements Serializable {


    final static Logger log = LoggerFactory.getLogger(NumerosAction.class);

    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    private Empleado empleado;

    private Proxy_Empleado proxyEmpleado;

    private String saludo;

    List<Empleado> lista;

    public NumerosAction() throws ProxyException, EmpleadoException {
        log.info("En constructor de Bean [NumerosAction]");
        proxyEmpleado = new Proxy_Empleado();


        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            String email = principal.getName();
            empleado = proxyEmpleado.buscarByEmail(email);
            log.debug("empleado = '" + empleado + "'");
        } catch (NullPointerException e) {
            log.error("No se pudo cargar el empleado de la sesión.");
        }

    }


    public String saludar() {


        log.info("Nombre: " + nombre);
        saludo = proxyEmpleado.saludar(this.nombre);

        log.info("Saludo: " + saludo);
        log.info("Redirigiendo a vista...");


        return viewRequest;
    }

    public String listarEmpleados() {

        log.info("listar");

        lista = proxyEmpleado.listarEmpleados();

        if (lista.size() >= 1) {
            log.info("--- Lista con Users ---");
        }

        log.info("Redirigiendo a vista...");
        return "admin";
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public List<Empleado> getLista() {
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public String getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(String viewRequest) {
        this.viewRequest = viewRequest;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
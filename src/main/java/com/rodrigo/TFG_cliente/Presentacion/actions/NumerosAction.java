package com.rodrigo.TFG_cliente.Presentacion.actions;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.FactoriaSA;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
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

    private TEmpleado empleado;


    private Usuario usuario;


    private String saludo;

    List<TEmpleado> lista;

    public NumerosAction() throws ProxyException, UsuarioException {
        log.info("En constructor de Bean [NumerosAction]");


        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            String email = principal.getName();
            usuario = FactoriaSA.getInstance().crearSA_Usuario().buscarByEmail(email);
            log.debug("usuario = '" + usuario + "'");
        } catch (NullPointerException e) {
            log.error("No se pudo cargar el empleado de la sesión.");
        }

    }


    public String saludar() {


        log.info("Nombre: " + nombre);
        saludo = Delegado_Empleado.getInstance().saludar(this.nombre);

        log.info("Saludo: " + saludo);
        log.info("Redirigiendo a vista...");


        return viewRequest;
    }

    public String listarEmpleados() {

        log.info("listar");

        lista = Delegado_Empleado.getInstance().listarEmpleados();

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

    public List<TEmpleado> getLista() {
        return lista;
    }

    public void setLista(List<TEmpleado> lista) {
        this.lista = lista;
    }

    public String getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(String viewRequest) {
        this.viewRequest = viewRequest;
    }

    public TEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(TEmpleado empleado) {
        this.empleado = empleado;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
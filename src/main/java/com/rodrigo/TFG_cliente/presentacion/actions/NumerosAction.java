package com.rodrigo.TFG_cliente.presentacion.actions;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Serv_aplicacion.IBrokerSAUsuario;
import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.proxy.Proxy;
import com.rodrigo.TFG_cliente.presentacion.proxy.imp.Proxy_Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@ManagedBean(name = "numerosAction")
@SessionScoped
public class NumerosAction implements Serializable {


    final static Logger log = LoggerFactory.getLogger(NumerosAction.class);


    private Usuario user;

    private Proxy_Usuarios proxyUsuarios;

    private String saludo;

    public NumerosAction() throws ProxyException {


        log.info("En constructor de Bean [NumerosAction]");
        proxyUsuarios = new Proxy_Usuarios();


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

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    List<Usuario> lista;


    //public int evento;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }




    public String saludar() {


        log.info("Nombre: " + nombre);
        saludo = proxyUsuarios.saludar(this.nombre);

        log.info("Saludo: " + saludo);
        log.info("Redirigiendo a vista...");
        return "admin";
    }

    public String listarUsuarios() {

        log.info("listar");

        lista = proxyUsuarios.listarUsuarios();

        if (lista.size() >= 1) {
            log.info("--- Lista con Users ---");
        }

        log.info("Redirigiendo a vista...");
        return "admin";
    }

}
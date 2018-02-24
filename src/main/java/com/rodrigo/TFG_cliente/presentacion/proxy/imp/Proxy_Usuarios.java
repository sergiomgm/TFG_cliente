package com.rodrigo.TFG_cliente.presentacion.proxy.imp;

import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Serv_aplicacion.IBrokerSAUsuario;
import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.proxy.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Proxy_Usuarios implements Proxy, IBrokerSAUsuario {

    private final static Logger log = LoggerFactory.getLogger(Proxy_Usuarios.class);

    private final String URL_WSDL = HOST + ":" + PORT + APP_URI +  "/SA_Usuarios?wsdl";

    private final String NAMESPACE_URI = "http://imp.Serv_aplicacion.Modulo_Usuario.Negocio.TFG_server.rodrigo.com/";

    private final String SERVICE_NAME = "BrokerSAUsuario";


    private IBrokerSAUsuario portUsuarios;


    public Proxy_Usuarios() throws ProxyException {
        initProxy();
    }

    public void initProxy() throws ProxyException {


        QName SERVICE_USUARIOS = new QName(NAMESPACE_URI, SERVICE_NAME);

        log.debug("preURL");

        URL wsdlURLUsuarios = null;

        try {
            wsdlURLUsuarios = new URL(URL_WSDL);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());

            throw new ProxyException("Error al conectar con servicio Usuario");
        }


        log.debug("Pre Service ss");
        Service ssUsuarios = Service.create(wsdlURLUsuarios, SERVICE_USUARIOS);
        log.debug("pre port");


        portUsuarios = ssUsuarios.getPort(IBrokerSAUsuario.class);
        log.debug("Post port");

    }


    @Override
    public Usuario crearUsuario(Usuario usuarioNuevo) {
        return portUsuarios.crearUsuario(usuarioNuevo);
    }

    @Override
    public Usuario buscarUsuarioByID(Long id) {
        return portUsuarios.buscarUsuarioByID(id);
    }

    @Override
    public boolean eliminarUsuario(Usuario usuarioEliminar) {
        return portUsuarios.eliminarUsuario(usuarioEliminar);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return portUsuarios.listarUsuarios();
    }

    @Override
    public String saludar(String nombre) {
        return this.portUsuarios.saludar(nombre);
    }

}

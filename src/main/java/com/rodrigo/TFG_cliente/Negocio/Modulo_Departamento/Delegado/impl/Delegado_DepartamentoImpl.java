package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


public class Delegado_DepartamentoImpl extends Delegado_Departamento {

    private Logger log = LoggerFactory.getLogger(Delegado_DepartamentoImpl.class);


    private Client cliente;


    public Delegado_DepartamentoImpl(){
        log.info("Creando DelegadoDelNegocio");

        log.info("Creando cliente");
        cliente = ClientBuilder.newBuilder().newClient();

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TDepartamento crearDepartamento(TDepartamento departamentoNuevo) throws DepartamentoException {

        log.info("Delegado_DepartamentoImpl.crearDepartamento");
        log.info("departamentoNuevo = [" + departamentoNuevo + "]");

        String urlFinal = URL;
        System.out.println("urlFinal = [" + urlFinal + "]");

        Response res = cliente
                .target(urlFinal)
                .request()
                .put(Entity.xml(departamentoNuevo));

        TDepartamento dept = (TDepartamento) res.getEntity();

        System.out.println("res = [" + res + "]");



        return dept;
    }

    @Override
    public TDepartamentoCompleto buscarDepartamentoByID(Long id) {
        log.info("Delegado_DepartamentoImpl.buscarDepartamentoByID");
        log.info("id = [" + id + "]");

        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + id.toString() +"]");

        TDepartamentoCompleto res = cliente
                .target(urlFinal)
                .path(id.toString())
                .request()
                .get(TDepartamentoCompleto.class);

        System.out.println("res = [" + res + "]");

        return res;

    }

    @Override
    public boolean eliminarDepartamento(TDepartamento departEliminar) {
        log.info("Delegado_DepartamentoImpl.eliminarDepartamento");
        log.info("id = [" + departEliminar + "]");
        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + departEliminar.toString() +"]");

        Boolean res = cliente
                .target(urlFinal)
                .path(departEliminar.toString())
                .request()
                .get(Boolean.class);

        System.out.println("res = [" + res + "]");

        return res;
    }

    @Override
    public TDepartamento[] listarDepartamentos() {
        log.info("Delegado_DepartamentoImpl.listarDepartamentos");

        String urlFinal = URL + "/listar";

        System.out.println("urlFinal = [" + urlFinal +"]");

        TDepartamento[] res = cliente
                .target(urlFinal)
                .path("")
                .request()
                .get(TDepartamento[].class);

        System.out.println("res = [" + res + "]");

        return res;
    }

    @Override
    public String saludar(String nombre) {

        //Client client = ClientBuilder.newBuilder().newClient();
       /* WebTarget target = client.target("http://localhost:8080/rs");
        target = target.path("service").queryParam("a", "avalue");

        Invocation.Builder builder = target.request();
        Response response = builder.get();
        Book book = builder.get(Book.class);*/

        String urlFinal = URL+"/saludo/"+nombre;
        System.out.println("urlFinal = [" + urlFinal + "]");

        String res = cliente
                .target(urlFinal)
                .request()
                .get(String.class);

        System.out.println("res = [" + res + "]");



        return res;
    }



    @Override
    public TDepartamentoCompleto buscarBySiglas(String siglas) throws DepartamentoException {
        log.info("Buscando por siglas: [" + siglas + "]");
        String urlFinal = URL + "/bySiglas";

        System.out.println("urlFinal = [" + urlFinal +"/"+ siglas +"]");

        TDepartamentoCompleto res = cliente
                .target(urlFinal)
                .path(siglas)
                .request()
                .get(TDepartamentoCompleto.class);

        System.out.println("res = [" + res + "]");

        return res;
    }





}



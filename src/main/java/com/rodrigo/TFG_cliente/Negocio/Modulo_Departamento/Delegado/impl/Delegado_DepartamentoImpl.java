package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Departamento;
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
    public Departamento crearDepartamento(Departamento departamentoNuevo) throws DepartamentoException {

        log.info("Delegado_DepartamentoImpl.crearDepartamento");
        log.info("departamentoNuevo = [" + departamentoNuevo + "]");

        String urlFinal = URL;
        System.out.println("urlFinal = [" + urlFinal + "]");

        Response res = cliente
                .target(urlFinal)
                .request()
                .put(Entity.xml(departamentoNuevo));

        Departamento dept = (Departamento) res.getEntity();

        System.out.println("res = [" + res + "]");



        return dept;
    }

    @Override
    public Departamento buscarDepartamentoByID(Long id) {
        log.info("Delegado_DepartamentoImpl.buscarDepartamentoByID");
        log.info("id = [" + id + "]");

        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + id.toString() +"]");

        Departamento res = cliente
                .target(urlFinal)
                .path(id.toString())
                .request()
                .get(Departamento.class);

        System.out.println("res = [" + res + "]");

        return res;

    }

    @Override
    public boolean eliminarDepartamento(Long id) {
        log.info("Delegado_DepartamentoImpl.eliminarDepartamento");
        log.info("id = [" + id + "]");
        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + id.toString() +"]");

        Boolean res = cliente
                .target(urlFinal)
                .path(id.toString())
                .request()
                .get(Boolean.class);

        System.out.println("res = [" + res + "]");

        return res;
    }

    @Override
    public Departamento[] listarDepartamentos() {
        log.info("Delegado_DepartamentoImpl.listarDepartamentos");

        String urlFinal = URL + "/listar";

        System.out.println("urlFinal = [" + urlFinal +"]");

        Departamento[] res = cliente
                .target(urlFinal)
                .path("")
                .request()
                .get(Departamento[].class);

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
    public Departamento getDepartamentoCompleto(Long id){

        String urlFinal = URL + "/deptCompleto/";

        System.out.println("urlFinal = [" + urlFinal + id.toString() +"]");

        Departamento res = cliente
                .target(urlFinal)
                .path(id.toString())
                .request()
                .get(Departamento.class);


        System.out.println("res = [" + res + "]");
        return res;
    }


    @Override
    public Departamento buscarByXXXXTest(Long id){
        System.out.println("Delegado_DepartamentoImpl.buscarByXXXXTest");

        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + id.toString() +"]");

        Departamento res = cliente
                .target(urlFinal)
                .path(id.toString())
                .request()
                .get(Departamento.class);

        System.out.println("res = [" + res + "]");



         urlFinal = URL + "/bySiglas";

        System.out.println("urlFinal = [" + urlFinal + "DdP" +"]");

        Departamento res2 = cliente
                .target(urlFinal)
                .path("DdP")
                .request()
                .get(Departamento.class);


        System.out.println("res2 = [" + res2 + "]");
        return res;
    }


    @Override
    public Departamento buscarBySiglas(String siglas) throws DepartamentoException {
        log.info("Buscando por siglas: [" + siglas + "]");
        String urlFinal = URL + "/bySiglas";

        System.out.println("urlFinal = [" + urlFinal +"/"+ siglas +"]");

        Departamento res = cliente
                .target(urlFinal)
                .path(siglas)
                .request()
                .get(Departamento.class);

        System.out.println("res = [" + res + "]");

        return res;
    }






    public static class Main {


        public static void main(String[] args) throws ProxyException {


            Delegado_Departamento.getInstance().saludar("Claudia");

            Delegado_Departamento.getInstance().getDepartamentoCompleto(3L);

            Delegado_Departamento.getInstance().buscarByXXXXTest(3L);

        }

    }
}



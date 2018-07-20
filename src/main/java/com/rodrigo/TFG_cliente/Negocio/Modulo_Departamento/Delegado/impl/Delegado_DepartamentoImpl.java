package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Authenticator;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


public class Delegado_DepartamentoImpl extends Delegado_Departamento {

    private Logger log = LoggerFactory.getLogger(Delegado_DepartamentoImpl.class);


    private Client cliente;


    public Delegado_DepartamentoImpl() {
        log.info("Creando DelegadoDelNegocio");

        log.info("Creando cliente");
        cliente = ClientBuilder
                .newBuilder()
                .newClient()
                .register(new Authenticator("user", "pass"));

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TDepartamento crearDepartamento(TDepartamento departamentoNuevo) throws DepartamentoYaExisteExcepcion, DepartamentoException {

        log.info("Delegado_DepartamentoImpl.crearDepartamento");
        log.info("departamentoNuevo = [" + departamentoNuevo + "]");

        if(departamentoNuevo == null){
            throw new DepartamentoFieldInvalidException();
        }

        String urlFinal = URL;
        System.out.println("urlFinal = [" + urlFinal + "]");

        Response res = cliente
                .target(urlFinal)
                .request()
                .put(Entity.xml(departamentoNuevo));

        System.out.println("res.getStatus() = [" + res.getStatus() + "]");

        if (res.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("res.readEntity(TDepartamento.class) = [" + res.readEntity(TDepartamento.class) + "]");
            System.out.println("res = [" + res + "]");
            return res.readEntity(TDepartamento.class);

        } else if (res.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {

            throw new DepartamentoFieldInvalidException();
        } else if (res.getStatus() == Response.Status.BAD_GATEWAY.getStatusCode()) {

            throw new DepartamentoYaExisteExcepcion();
        } else {

            throw new DepartamentoException("Error al crear el departamento.");
        }

    }

    @Override
    public TDepartamentoCompleto buscarByID(Long id) throws DepartamentoException {
        log.info("Delegado_DepartamentoImpl.buscarDepartamentoByID");
        log.info("id = [" + id + "]");

        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + "/" +  id.toString() + "]");


        try {
            TDepartamentoCompleto dept = cliente
                    .target(urlFinal)
                    .path(id.toString())
                    .request()
                    .get(TDepartamentoCompleto.class);

            System.out.println("dept = [" + dept + "]");
            return dept;

        } catch (BadRequestException e) {

            System.out.println("Bad request");
            throw new DepartamentoFieldInvalidException("ERROR EN LA PETICION!");

        } catch (NotFoundException e) {

            System.out.println("Not found");
            return null;
        }


    }

    @Override
    public boolean eliminarDepartamento(Long id) throws DepartamentoFieldInvalidException, DepartamentoNoEncontradoException, DepartamentoConEmpleadosException {
        log.info("Delegado_DepartamentoImpl.eliminarDepartamento");
        log.info("id = [" + id + "]");
        log.info("id = [" + String.valueOf(id) + "]");
        boolean result = false;
        String urlFinal = URL + "";

        System.out.println("urlFinal = [" + urlFinal + "/" + id + "]");

        Response res = cliente
                .target(urlFinal)
                .path(String.valueOf(id))
                .request()
                .delete();


        System.out.println("res = [" + res + "]");
        System.out.println("res.getStatus() = [" + res.getStatus() + "]");

        if (res.getStatus() == Response.Status.OK.getStatusCode()) {
            result = true;
        } else if (res.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            result = false;
        } else if (res.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            throw new DepartamentoFieldInvalidException();
        } else if (res.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new DepartamentoNoEncontradoException();
        } else if(res.getStatus() == Response.Status.BAD_GATEWAY.getStatusCode()) {
            throw new DepartamentoConEmpleadosException();
        }
        return result;
    }

    @Override
    public TDepartamento[] listarDepartamentos() {
        log.info("Delegado_DepartamentoImpl.listarDepartamentos");

        String urlFinal = URL + "/listar";

        System.out.println("urlFinal = [" + urlFinal + "]");

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

        String urlFinal = URL + "/saludo/" + nombre;
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
        log.info("Buscando por siglas: [" + String.valueOf(siglas) + "]");
        String urlFinal = URL + "/bySiglas";

        System.out.println("urlFinal = [" + urlFinal + "/" + String.valueOf(siglas) + "]");

        try {
            TDepartamentoCompleto dept = cliente
                    .target(urlFinal)
                    .path(String.valueOf(siglas))
                    .request()
                    .get(TDepartamentoCompleto.class);

            System.out.println("dept = [" + dept + "]");
            return dept;

        } catch (BadRequestException e) {

            System.out.println("Bad request");
            throw new DepartamentoException("ERROR EN LA PETICION!");

        } catch (NotFoundException e) {

            System.out.println("Departamento no encontrado");
            return null;
        }
    }


}
package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl;

import com.eduardosergio.TFG_cliente.negocio.seguridad.departamento.ObfuscatedTransferObjectDepartamento;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLoggerBusiness;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Authenticator;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import javax.xml.ws.BindingProvider;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class Delegado_DepartamentoImpl extends Delegado_Departamento {

    private Logger log = LoggerFactory.getLogger(Delegado_DepartamentoImpl.class);

    private String HOST = "https://localhost" ;

    private String PORT = "8443";

    private String APP_URI = "/TFG_server/services";

    private final String SERVICE_NAME = "/SA_Departamento/departamento";

    private final String URL_SERVICIO = HOST + ":" + PORT + APP_URI + SERVICE_NAME;



    private Client cliente;


    public Delegado_DepartamentoImpl() {
        log.info("Creando DelegadoDelNegocio");

        log.info("Creando cliente");
        
        String user = "user";
        
        cliente = ClientBuilder
                .newBuilder()
                .newClient()
                .register(new Authenticator(user, "pass")).property("user", user);

        log.info("DelegadoDelNegocio creado");
    }


    @Override
    public TDepartamento crearDepartamento(TDepartamento departamentoNuevo) throws DepartamentoYaExisteExcepcion, DepartamentoException {

        log.info("Delegado_DepartamentoImpl.crearDepartamento");
        log.info("departamentoNuevo = [" + departamentoNuevo + "]");

        if (departamentoNuevo == null) {
            throw new DepartamentoFieldInvalidException();
        }

        String urlFinal = URL_SERVICIO;
        System.out.println("urlFinal = [" + urlFinal + "]");
        
        ObfuscatedTransferObjectDepartamento departamentoOfuscado = new ObfuscatedTransferObjectDepartamento();
        departamentoOfuscado.setProtectedData("id", departamentoNuevo.getId());
        departamentoOfuscado.setProtectedData("nominaMes", departamentoNuevo.getNominaMes());
        departamentoOfuscado.setPublicData("siglas", departamentoNuevo.getSiglas());
        departamentoOfuscado.setPublicData("nombre", departamentoNuevo.getNombre());

        Response res = cliente
                .target(urlFinal)
                .request()
                .put(Entity.xml(departamentoOfuscado));

        System.out.println("res.getStatus() = [" + res.getStatus() + "]");

        if (res.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("res.readEntity(TDepartamento.class) = [" + res.readEntity(TDepartamento.class) + "]");
            System.out.println("res = [" + res + "]");

            SecureLoggerBusiness secureLogger = SecureLoggerBusiness.getInstance();
        	secureLogger.log((String) cliente.target(urlFinal).getConfiguration().getProperty("user"), "crearDepartamento");
        	
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

        String urlFinal = URL_SERVICIO + "";

        System.out.println("urlFinal = [" + urlFinal + "/" + id.toString() + "]");


        try {
            WebTarget wt = cliente.target(urlFinal);
            
            wt = wt.path(id.toString());
            Invocation.Builder b = wt.request();
            TDepartamentoCompleto dept = b.get(TDepartamentoCompleto.class);
            
            SecureLoggerBusiness secureLogger = SecureLoggerBusiness.getInstance();
        	secureLogger.log((String) wt.getConfiguration().getProperty("user"), "buscarById");
        	
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
        String urlFinal = URL_SERVICIO + "";

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
            
            SecureLoggerBusiness secureLogger = SecureLoggerBusiness.getInstance();
        	secureLogger.log((String) cliente.target(urlFinal).getConfiguration().getProperty("user"), "eliminarDepartamento");
        } else if (res.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            result = false;
        } else if (res.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            throw new DepartamentoFieldInvalidException();
        } else if (res.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new DepartamentoNoEncontradoException();
        } else if (res.getStatus() == Response.Status.BAD_GATEWAY.getStatusCode()) {
            throw new DepartamentoConEmpleadosException();
        }
        return result;
    }

    @Override
    public TDepartamento[] listarDepartamentos() {
        log.info("Delegado_DepartamentoImpl.listarDepartamentos");

        String urlFinal = URL_SERVICIO + "/listar";

        System.out.println("urlFinal = [" + urlFinal + "]");

        TDepartamento[] res = cliente
                .target(urlFinal)
                .path("")
                .request()
                .get(TDepartamento[].class);
        
        SecureLoggerBusiness secureLogger = SecureLoggerBusiness.getInstance();
    	secureLogger.log((String) cliente.target(urlFinal).getConfiguration().getProperty("user"), "listarDepartamentos");
    	
        System.out.println("res = [" + res + "]");

        return res;
    }



    @Override
    public TDepartamentoCompleto buscarBySiglas(String siglas) throws DepartamentoException {
        log.info("Buscando por siglas: [" + String.valueOf(siglas) + "]");
        String urlFinal = URL_SERVICIO + "/bySiglas";

        System.out.println("urlFinal = [" + urlFinal + "/" + String.valueOf(siglas) + "]");

        try {
            TDepartamentoCompleto dept = cliente
                    .target(urlFinal)
                    .path(String.valueOf(siglas))
                    .request()
                    .get(TDepartamentoCompleto.class);
            
            System.out.println("dept = [" + dept + "]");
            
            SecureLoggerBusiness secureLogger = SecureLoggerBusiness.getInstance();
        	secureLogger.log((String) cliente.target(urlFinal).getConfiguration().getProperty("user"), "buscarBySiglas");
        	
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
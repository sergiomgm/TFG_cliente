package com.rodrigo.TFG_cliente.Presentacion.Modulo_Departamento.Bean;

import com.eduardosergio.TFG_cliente.negocio.modulo_Departamento.delegado.Delegado_DepartamentoSOAP;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLogger;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
@ManagedBean(name = "DepartamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    private final static Logger log = LoggerFactory.getLogger(DepartamentoBean.class);

    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    private AccionVista accionVista = new AccionVista();


    private Long id;

    private String nombre;

    private String siglas;

    private TDepartamentoCompleto departamentoCompleto = new TDepartamentoCompleto();

    private List<TDepartamento> listaDepartamento;



    /****************************
     ********** METODOS *********
     ****************************/


    public String crearDepartamento() {
        System.out.println(accionVista);

        TDepartamento departNuevo;

        if(nombre != null && nombre.trim() != ""){

            if(siglas != null && siglas.trim() != ""){
                departNuevo = new TDepartamento(nombre, siglas);
            }else{
                departNuevo = new TDepartamento(nombre);
            }


            try {
            	
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Crear nuevo departamento");
                
                departamentoCompleto.setDepartamento(Delegado_Departamento.getInstance().crearDepartamento(departNuevo));
                accionVista.setHayError(false);

            } catch (DepartamentoYaExisteExcepcion e1) {

                log.error("EXCEPCION!!", e1);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e1.getMessage());

            } catch (DepartamentoFieldInvalidException e2){

                log.error("EXCEPCION!!", e2);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e2.getMessage());

            } catch (DepartamentoException e3){

                log.error("EXCEPCION!!", e3);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e3.getMessage());

            }


        }else{
            accionVista.setMensajeWarning("Debe escribirse un nombre de departamento");
        }



        if (accionVista.getHayError()) {
                accionVista.setAccion(AccionVista.AccionEnum.ACCION_CREAR_DEPARTAMENTO);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_DEPARTAMENTO);
            accionVista.setMensajeSuccess("Departamento creado correctamente");
        }


        System.out.println(viewRequest);
        System.out.println("accionVista = [" + accionVista + "]");

        return viewRequest;
    }


    public String buscarById() {
        System.out.println(accionVista);
        log.info("this.id = '" + this.id + "'");
        log.info("id = '" + id + "'");



        if (id != null && id > 0) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Buscar departamento con id " + id);
                
                departamentoCompleto = Delegado_Departamento.getInstance().buscarByID(id);
                if (departamentoCompleto == null) {
                    accionVista.setMensajeWarning("Departamento no encontrado en la BBDD");
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (DepartamentoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());
            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("El ID debe ser un número positivo");
        }

        System.out.println("*********************");
        System.out.println("hayError = [" + accionVista.getHayError() + "]");
        System.out.println("mensajeError = [" + accionVista.getMensajeError() + "]");
        System.out.println("*********************");

        System.out.println(viewRequest);
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_DEPARTAMENTO);
        return viewRequest;
    }
    
    public String buscarByIdSOAP() {
        System.out.println(accionVista);
        log.info("this.id = '" + this.id + "'");
        log.info("id = '" + id + "'");

        if (id != null && id > 0) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Buscar departamento con id " + id + " mediante SOAP");
                
                departamentoCompleto = Delegado_DepartamentoSOAP.getInstance().buscarByID(id);
                if (departamentoCompleto == null) {
                    accionVista.setMensajeWarning("Departamento no encontrado en la BBDD");
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (DepartamentoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());
            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("El ID debe ser un número positivo");
        }

        System.out.println("*********************");
        System.out.println("hayError = [" + accionVista.getHayError() + "]");
        System.out.println("mensajeError = [" + accionVista.getMensajeError() + "]");
        System.out.println("*********************");

        System.out.println(viewRequest);
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_DEPARTAMENTO);
        return viewRequest;
    }


    public String eliminarDepartamento() {
        System.out.println(accionVista);
        log.info("id = '" + id + "'");

        if (id != null && id > 0) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Eliminar departamento con id " + id);
                
                boolean result  = Delegado_Departamento.getInstance().eliminarDepartamento(id);
                if(result){
                    accionVista.setMensajeSuccess("Departamento borrado correctamente");
                }else{
                    accionVista.setMensajeWarning("Departamento no se pudo borrar correctamente");
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (DepartamentoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("El ID debe ser un número positivo");
        }

        System.out.println(viewRequest);
        return viewRequest;
    }


    public String listarDepartamentos() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_LISTAR_DEPARTAMENTOS);
        System.out.println(accionVista);

        System.out.println(viewRequest);
        
        SecureLogger secureLogger = SecureLogger.getInstance();
        secureLogger.log("Listar departamentos");
        
        listaDepartamento = Arrays.asList(Delegado_Departamento.getInstance().listarDepartamentos());


        return viewRequest;
    }


    public String buscarBySiglas() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_BUSCAR_DEPARTAMENTO_SIGLAS);
        System.out.println(accionVista);
        log.info("siglas = '" + siglas + "'");

        if (siglas != null && !siglas.trim().equals("")) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Buscar departamento con siglas " + siglas);
                
                departamentoCompleto = Delegado_Departamento.getInstance().buscarBySiglas(siglas.trim());
                if (departamentoCompleto == null) {
                    accionVista.setMensajeWarning("Departamento no encontrado en la BBDD");
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (DepartamentoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (Exception e) {
                log.error("EXCEPCION!!", e);
                accionVista.setHayError(true);
                accionVista.setMensajeError("Ocurrió un error al buscar en el sistema.");
            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("Las siglas son incorrectas.");
        }

        System.out.println(viewRequest);
        return viewRequest;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public TDepartamentoCompleto getDepartamentoCompleto() {
        return departamentoCompleto;
    }

    public void setDepartamentoCompleto(TDepartamentoCompleto departamentoCompleto) {
        this.departamentoCompleto = departamentoCompleto;
    }

    public List<TDepartamento> getListaDepartamentos() {
        return listaDepartamento;
    }

    public void setListaDepartamentos(List<TDepartamento> listaDepartamentos) {
        this.listaDepartamento = listaDepartamentos;
    }

    public AccionVista getAccionVista() {
        return this.accionVista;
    }

    public String setAccion(String accion) {
        log.info(accion);

        this.accionVista.setAccion(AccionVista.AccionEnum.valueOf(accion));

        log.info(viewRequest);
        return viewRequest;
    }


    private void iniciarAtributos() {

        this.nombre = null;
        this.siglas = null;
        this.id = null;
        this.departamentoCompleto = null;
        this.listaDepartamento = null;
        this.accionVista.inicializarAtributos();
    }




}

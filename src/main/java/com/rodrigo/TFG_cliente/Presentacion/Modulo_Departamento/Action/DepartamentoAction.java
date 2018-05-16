package com.rodrigo.TFG_cliente.Presentacion.Modulo_Departamento.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoFieldInvalidException;
import com.rodrigo.TFG_cliente.Presentacion.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "DepartamentoAction")
@SessionScoped
public class DepartamentoAction extends Action implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    private final static Logger log = LoggerFactory.getLogger(DepartamentoAction.class);

    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    private Long id;

    private String siglas;

    private TDepartamentoCompleto departamentoCompleto;

    private List<TDepartamento> listaDepartamento;


    /****************************
     ********** METODOS *********
     ****************************/


    public String crearDepartamento() {
        //super.accion = Accion.BUSCAR_DEPARTAMENTO_ID;
        System.out.println(accion);
        iniciarAtributos();

        System.out.println(viewRequest);


        return viewRequest;
    }


    public String buscarById() {
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                departamentoCompleto = Delegado_Departamento.getInstance().buscarByID(id);
                if (departamentoCompleto == null) {
                    mensajeWarning = "Departamento no encontrado en la BBDD";
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (DepartamentoException e) {
                e.printStackTrace();
                hayError = true;
                mensajeError = e.getMessage();
            }

        } else {
            hayError = true;
            mensajeError = "El ID debe ser un número positivo";
        }

        System.out.println("*********************");
        System.out.println("hayError = [" + hayError + "]");
        System.out.println("mensajeError = [" + mensajeError + "]");
        System.out.println("*********************");

        System.out.println(viewRequest);
        return viewRequest;
    }


    public String eliminarDepartamento() {
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                boolean result  = Delegado_Departamento.getInstance().eliminarDepartamento(id);
                if(result){
                    mensajeSuccess = "Departamento borrado correctamente";
                }else{
                    mensajeWarning = "Departamento no se pudo borrar correctamente";
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (DepartamentoException e) {
                e.printStackTrace();
                hayError = true;
                mensajeError = e.getMessage();

            }

        } else {
            hayError = true;
            mensajeError = "El ID debe ser un número positivo";
        }

        System.out.println(viewRequest);
        return viewRequest;
    }


    public String listarDepartamentos() {
        super.accion = Accion.ACCION_LISTAR_DEPARTAMENTOS;
        System.out.println(accion);
        iniciarAtributos();

        System.out.println(viewRequest);

        listaDepartamento = Arrays.asList(Delegado_Departamento.getInstance().listarDepartamentos());


        return viewRequest;
    }


    public String buscarBySiglas() {
//        super.accion = Accion.BUSCAR_DEPARTAMENTO_ID;
        System.out.println(accion);
        String siglas = this.siglas;
        log.info("siglas = '" + siglas + "'");
        iniciarAtributos();

        if (siglas != null && !siglas.trim().equals("")) {


            try {
                departamentoCompleto = Delegado_Departamento.getInstance().buscarBySiglas(siglas.trim());
                if (departamentoCompleto == null) {
                    mensajeWarning = "Departamento no encontrado en la BBDD";
                }
            } catch (DepartamentoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (DepartamentoException e) {
                e.printStackTrace();
                hayError = true;
                mensajeError = e.getMessage();

            } catch (Exception e) {
                log.error("EXCEPCION!!", e);
                hayError = true;
                mensajeError = "Ocurrió un error al buscar en el sistema.";
            }

        } else {
            hayError = true;
            mensajeError = "Las siglas son incorrectas.";
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

    public String getAccion() {
        return String.valueOf(super.accion);
    }

    public String setAccion(String accion) {
        log.info(accion);

        super.accion = Accion.valueOf(accion);

        iniciarAtributos();
        log.info(viewRequest);
        return viewRequest;
    }


    private void iniciarAtributos() {

        this.siglas = null;
        this.id = null;
        this.departamentoCompleto = null;
        this.listaDepartamento = null;
        hayError = false;
        mensajeError = null;
        mensajeWarning = null;
        mensajeSuccess = null;
    }


}

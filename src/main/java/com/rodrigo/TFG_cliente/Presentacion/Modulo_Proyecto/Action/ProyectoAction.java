package com.rodrigo.TFG_cliente.Presentacion.Modulo_Proyecto.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoFieldInvalidException;
import com.rodrigo.TFG_cliente.Presentacion.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "ProyectoAction")
@SessionScoped
public class ProyectoAction extends Action implements Serializable {

    /****************************
     ********  ATRIBUTOS  *******
     ****************************/

    private final static Logger log = LoggerFactory.getLogger(ProyectoAction.class);

    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;


    private Long id;

    private String nombre;


    private TProyectoCompleto proyectoCompleto;

    private List<TProyecto> listaProyectos;


    /****************************
     ********** METODOS *********
     ****************************/


    public String crearProyecto(TProyecto proyectoNuevo) {
        super.accion = Accion.ACCION_CREAR_PROYECTO;
        log.info(accion.toString());
        iniciarAtributos();

        log.info(viewRequest);


        return viewRequest;
    }


    public String buscarById() {
        super.accion = Accion.ACCION_BUSCAR_PROYECTO_ID;
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                proyectoCompleto = Delegado_Proyecto.getInstance().buscarByID(id);
                if (proyectoCompleto == null) {
                    mensajeSuccess = "Proyecto no encontrado en la BBDD";
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (ProyectoException e) {
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


    public String buscarByNombre() {
        super.accion = Accion.ACCION_BUSCAR_PROYECTO_NOMBRE;
        System.out.println(accion);
        String nombre = this.nombre;
        log.info("nombre = '" + nombre + "'");
        iniciarAtributos();

        if (nombre != null && !nombre.trim().equals("")) {

            try {
                proyectoCompleto = Delegado_Proyecto.getInstance().buscarByNombre(nombre.trim());
                if (proyectoCompleto == null) {
                    mensajeSuccess = "Proyecto no encontrado en la BBDD";
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (ProyectoException e) {
                e.printStackTrace();
                hayError = true;
                mensajeError = e.getMessage();

            }

        } else {
            hayError = true;
            mensajeError = "Debe escribir un nombre.";
        }

        System.out.println(viewRequest);
        return viewRequest;
    }


    public String eliminarProyecto() {
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                boolean result  = Delegado_Proyecto.getInstance().eliminarProyecto(id);
                if(result){
                    mensajeSuccess = "Proyecto borrado correctamente";
                }else{
                    mensajeWarning = "Proyecto no se pudo borrar correctamente";
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (ProyectoException e) {
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


    public String listarProyectos() {
        super.accion = Accion.ACCION_LISTAR_PROYECTOS;
        log.info(accion.toString());
        iniciarAtributos();

        log.info(viewRequest);

        listaProyectos = Delegado_Proyecto.getInstance().listarProyectos();


        return viewRequest;
    }


    public String añadirEmpleadoAProyecto(TEmpleado e, TProyecto p, int horas) {
        super.accion = Accion.ACCION_AGREGAR_EMPLEADO_A_PROYECTO;
        log.info(accion.toString());
        iniciarAtributos();

        log.info(viewRequest);

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

    public TProyectoCompleto getProyectoCompleto() {
        return proyectoCompleto;
    }

    public void setProyectoCompleto(TProyectoCompleto proyectoCompleto) {
        this.proyectoCompleto = proyectoCompleto;
    }

    public List<TProyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<TProyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
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

        this.nombre = null;
        this.id = null;
        this.proyectoCompleto = null;
        this.listaProyectos = null;

        hayError = false;
        mensajeSuccess = null;
        mensajeWarning = null;
        mensajeError = null;
    }

}

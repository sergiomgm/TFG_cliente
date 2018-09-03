package com.rodrigo.TFG_cliente.Presentacion.Modulo_Proyecto.Bean;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Delegado.Delegado_Proyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TEmpleadoProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyecto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.Transfers.TProyectoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Excepciones.ProyectoYaExistenteException;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "ProyectoBean")
@SessionScoped
public class ProyectoBean implements Serializable {

    /****************************
     ********  ATRIBUTOS  *******
     ****************************/

    private final static Logger log = LoggerFactory.getLogger(ProyectoBean.class);

    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;

    private AccionVista accionVista = new AccionVista();


    private Long id;

    private String nombre;

    private Date fechaInicio;

    private Date fechafin;

    private String descripcion;


    private String idProyecto;

    private String idEmpleado;

    private String horas;

    private TEmpleadoProyecto tep;


    private TProyectoCompleto proyectoCompleto = new TProyectoCompleto();

    private List<TProyecto> listaProyectos;


    private List<TEmpleado> listaEmpleados;


    /****************************
     ********** METODOS *********
     ****************************/


    public String crearProyecto() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_CREAR_PROYECTO);
        //super.accionVista = AccionEnum.BUSCAR_DEPARTAMENTO_ID;
        System.out.println(accionVista);
        //iniciarAtributos();

        TProyecto proyNuevo;

        log.info("getFechaInicio() = '" + getFechaInicio() + "'");
        log.info("getFechafin() = '" + getFechafin() + "'");

        if (nombre != null && nombre.trim() != "" &&
                descripcion != null && descripcion.trim() != "" &&
                fechaInicio != null && fechafin != null) {

            if (fechaInicio.before(fechafin)) {


                proyNuevo = new TProyecto(nombre, descripcion, fechaInicio, fechafin);

                try {

                    proyectoCompleto.setProyecto(Delegado_Proyecto.getInstance().crearProyecto(proyNuevo));

                } catch (ProyectoYaExistenteException e1) {

                    log.error("EXCEPCION!!", e1);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e1.getMessage());

                } catch (ProyectoFieldInvalidException e2) {

                    log.error("EXCEPCION!!", e2);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e2.getMessage());

                } catch (ProyectoException e3) {

                    log.error("EXCEPCION!!", e3);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e3.getMessage());

                }

            } else {
                accionVista.setMensajeWarning("La fecha de fin debe ser posterior a la de inicio.");
            }

        } else {
            accionVista.setMensajeWarning("Deben completarse todos los campos");
        }


        if (accionVista.getHayError()) {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_CREAR_PROYECTO);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_PROYECTO);
            accionVista.setMensajeSuccess("Proyecto creado correctamente");
        }


        System.out.println(viewRequest);
        System.out.println("accionVista = [" + accionVista + "]");

        return viewRequest;
    }


    public String buscarById() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_BUSCAR_PROYECTO_ID);
        System.out.println(accionVista);
//        Long id = this.id;
        log.info("id = '" + id + "'");
//        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                proyectoCompleto = Delegado_Proyecto.getInstance().buscarByID(id);
                if (proyectoCompleto == null) {
                    accionVista.setMensajeWarning("Proyecto no encontrado en la BBDD");
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (ProyectoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("El ID debe ser un número positivo");
        }

        System.out.println(viewRequest);
        if (accionVista.getHayError()) {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_BUSCAR_PROYECTO_ID);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_PROYECTO);
        }
        return viewRequest;
    }


    public String buscarByNombre() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_BUSCAR_PROYECTO_NOMBRE);
        System.out.println(accionVista);
//        String nombre = this.nombre;
        log.info("nombre = '" + nombre + "'");
//        iniciarAtributos();

        if (nombre != null && !nombre.trim().equals("")) {

            try {
                proyectoCompleto = Delegado_Proyecto.getInstance().buscarByNombre(nombre.trim());
                if (proyectoCompleto == null) {
                    accionVista.setMensajeWarning("Proyecto no encontrado en la BBDD");
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (ProyectoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("Debe escribir un nombre.");
        }

        System.out.println(viewRequest);
        if (accionVista.getHayError()) {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_BUSCAR_PROYECTO_NOMBRE);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_PROYECTO);
        }
        return viewRequest;
    }


    public String eliminarProyecto() {
        System.out.println(accionVista);
//        Long id = this.id;
        log.info("id = '" + id + "'");
//        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                boolean result = Delegado_Proyecto.getInstance().eliminarProyecto(id);
                if (result) {
                    accionVista.setMensajeSuccess("Proyecto borrado correctamente");
                } else {
                    accionVista.setMensajeWarning("Proyecto no se pudo borrar correctamente");
                }
            } catch (ProyectoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (ProyectoException e) {
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


    public String listarProyectos() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_LISTAR_PROYECTOS);
        log.info(accionVista.toString());
//        iniciarAtributos();

        log.info(viewRequest);

        listaProyectos = Delegado_Proyecto.getInstance().listarProyectos();


        return viewRequest;
    }


    public String añadirEmpleadoAProyecto() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_ASIGNAR_EMPELADO_A_PROYECTO);
        log.info(accionVista.toString());

        TEmpleado emple;
        TProyecto proy;

        if (idEmpleado != null && Long.valueOf(idEmpleado) > 0L &&
                idProyecto != null && Long.valueOf(idProyecto) > 0L &&
                horas != null && Integer.valueOf(horas) > 0) {

            proy = new TProyecto(Long.valueOf(idProyecto));
            emple = new TEmpleado(Long.valueOf(idEmpleado));

            try {

                tep = Delegado_Proyecto.getInstance().añadirEmpleadoAProyecto(emple, proy, Integer.valueOf(horas));


            } catch (ProyectoException e1) {

                log.error("EXCEPCION!!", e1);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e1.getMessage());

            } catch (EmpleadoException e2) {

                log.error("EXCEPCION!!", e2);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e2.getMessage());

            }


        } else {
            accionVista.setMensajeWarning("Deben completarse todos los campos");
        }


        log.info(viewRequest);
        if (accionVista.getHayError()) {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_ASIGNAR_EMPELADO_A_PROYECTO);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_PROYECTO);
            try {
                this.proyectoCompleto = Delegado_Proyecto.getInstance().buscarByID(tep.getProyecto());
            } catch (ProyectoException e) {
                log.error("EXCEPCION!!", e);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());
            }
            accionVista.setMensajeSuccess("Asignación creada correctamente");
        }

        return viewRequest;
    }


    public String eliminarEmpleadoAProyecto() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_ELIMINAR_EMPLEADO_DE_PROYECTO);
        log.info(accionVista.toString());

        TEmpleado emple;
        TProyecto proy;

        if (idEmpleado != null && Long.valueOf(idEmpleado) > 0L &&
                idProyecto != null && Long.valueOf(idProyecto) > 0L) {

            proy = new TProyecto(Long.valueOf(idProyecto));
            emple = new TEmpleado(Long.valueOf(idEmpleado));

            try {

                boolean resutl = Delegado_Proyecto.getInstance().eliminarEmpleadoAProyecto(Long.valueOf(idEmpleado), Long.valueOf(idProyecto));


            } catch (ProyectoException e1) {

                log.error("EXCEPCION!!", e1);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e1.getMessage());

            } catch (EmpleadoException e2) {

                log.error("EXCEPCION!!", e2);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e2.getMessage());

            }


        } else {
            accionVista.setMensajeWarning("Deben completarse todos los campos");
        }


        log.info(viewRequest);
        if (accionVista.getHayError()) {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_ELIMINAR_EMPLEADO_DE_PROYECTO);

        } else {

            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_PROYECTO);
            try {
                this.proyectoCompleto = Delegado_Proyecto.getInstance().buscarByID(Long.valueOf(idProyecto));
            } catch (ProyectoException e) {
                log.error("EXCEPCION!!", e);
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());
            }
            accionVista.setMensajeSuccess("Asignación eliminada correctamente");
        }

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public TEmpleadoProyecto getTep() {
        return tep;
    }

    public void setTep(TEmpleadoProyecto tep) {
        this.tep = tep;
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

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public AccionVista getAccionVista() {
        return this.accionVista;
    }

    public String setAccion(String accion) {
        log.info(accion);

        this.accionVista.inicializarAtributos();
        this.accionVista.setAccion(AccionVista.AccionEnum.valueOf(accion));

        if (AccionVista.AccionEnum.valueOf(accion) == AccionVista.AccionEnum.ACCION_ASIGNAR_EMPELADO_A_PROYECTO) {
            listaProyectos = Delegado_Proyecto.getInstance().listarProyectos();
            listaEmpleados = Delegado_Empleado.getInstance().listarEmpleados();
        }

        if (AccionVista.AccionEnum.valueOf(accion) == AccionVista.AccionEnum.ACCION_ELIMINAR_EMPLEADO_DE_PROYECTO) {
            listaProyectos = Delegado_Proyecto.getInstance().listarProyectos();
            listaEmpleados = Delegado_Empleado.getInstance().listarEmpleados();
        }


        log.info(viewRequest);
        return viewRequest;
    }


}

package com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Bean;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLogger;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTParcial;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Utils.EmailValidator;
import com.rodrigo.TFG_cliente.Presentacion.AccionVista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
@ManagedBean(name = "EmpleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(EmpleadoBean.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;


    private AccionVista accionVista = new AccionVista();

    private Long id;

    private String email;

    private String nombre;

    private String password;


    private String antiguedad;

    private String sueldoBase;

    private String horasJornada;

    private String precioHora;

    private String idDepart;



    private TEmpleadoCompleto empleadoCompleto;

    private List<? extends TEmpleado> listaEmpleados;


    private TDepartamento[] listaDepartamentos;


    /****************************
     ********** METODOS *********
     ****************************/


    public String crearEmpleado() {
        System.out.println(accionVista);
        TEmpleado emple = null;

        if (nombre != null && nombre.trim() != "" &&
                email != null && new EmailValidator().validate(email.trim())) {


            if (Long.valueOf(idDepart) > 0L) {


                if (antiguedad != null && Long.valueOf(antiguedad) > 0 &&
                        sueldoBase != null && Long.valueOf(sueldoBase) > 0) {

                    if (email != null && new EmailValidator().validate(email.trim())) {
                        emple = new TEmpleadoTCompleto(nombre, email, password, Long.valueOf(idDepart), Long.valueOf(antiguedad).intValue(), Long.valueOf(sueldoBase).intValue());
                    } else {
                        emple = new TEmpleadoTCompleto(nombre, password, Long.valueOf(idDepart), Long.valueOf(antiguedad).intValue(), Long.valueOf(sueldoBase).intValue());
                    }
                } else if (horasJornada != null && Long.valueOf(horasJornada) > 0 &&
                        precioHora != null && Long.valueOf(precioHora) > 0) {

                    if (email != null && new EmailValidator().validate(email.trim())) {
                        emple = new TEmpleadoTParcial(nombre, email, password, Long.valueOf(idDepart), Long.valueOf(horasJornada).intValue(), Long.valueOf(precioHora).intValue());
                    } else {
                        emple = new TEmpleadoTParcial(nombre, password, Long.valueOf(idDepart), Long.valueOf(horasJornada).intValue(), Long.valueOf(precioHora).intValue());
                    }

                }

                try {
                    log.info("Creando Empleado en el sistema");
               
                    SecureLogger secureLogger = SecureLogger.getInstance();
                    secureLogger.log("Crear nuevo empleado a tiempo completo");
                    
                    
                    empleadoCompleto = Delegado_Empleado.getInstance().crearEmpleado(emple);


                } catch (EmpleadoYaExisteExcepcion e) {
                    log.error("EXCEPCION!!", e);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e.getMessage());

                } catch (EmpleadoFieldInvalidException e) {
                    log.error("EXCEPCION!!", e);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e.getMessage());

                } catch (EmpleadoException e) {
                    log.error("EXCEPCION!!", e);
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e.getMessage());

                }
            } else {
                accionVista.setMensajeWarning("El id de departamento debe ser positivo");
            }


        } else {
            accionVista.setHayError(true);
        }
        if (accionVista.getHayError()) {
            if (emple instanceof TEmpleadoTCompleto)
                accionVista.setAccion(AccionVista.AccionEnum.ACCION_CREAR_EMPLEADO_TIEMPO_COMPLETO);
            else
                accionVista.setAccion(AccionVista.AccionEnum.ACCION_CREAR_EMPLEADO_TIEMPO_PARCIAL);
        } else {
            accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_EMPLEADO);
            accionVista.setMensajeSuccess("Empleado creado correctamente");

        }


        System.out.println(viewRequest);
        System.out.println("accionVista = [" + accionVista + "]");
        return viewRequest;
    }


    public String buscarById() {
        System.out.println(accionVista);
        log.info("id = '" + id + "'");

        if (id != null && id > 0) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Buscar empleado con id " + id);
                
                empleadoCompleto = Delegado_Empleado.getInstance().buscarByID(id);
                if (empleadoCompleto == null) {
                    accionVista.setMensajeWarning("Empleado no encontrado en la BBDD");
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (EmpleadoException e) {
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

        accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_EMPLEADO);
        System.out.println(viewRequest);
        System.out.println("accionVista = [" + accionVista + "]");
        return viewRequest;
    }


    public String buscarByEmail() {
        System.out.println(accionVista);
        log.info("email = '" + email + "'");

        if (email != null && (new EmailValidator().validate(email.trim()))) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Buscar empleado con email " + email);
                
                empleadoCompleto = Delegado_Empleado.getInstance().buscarByEmail(email.trim());
                if (empleadoCompleto == null) {
                    accionVista.setMensajeWarning("Empleado no encontrado en la BBDD");
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (EmpleadoException e) {
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
            accionVista.setMensajeError("El email en incorrecto.");
        }

        accionVista.setAccion(AccionVista.AccionEnum.ACCION_MOSTRAR_EMPLEADO);
        System.out.println(viewRequest);
        System.out.println("accionVista = [" + accionVista + "]");

        return viewRequest;
    }


    public String eliminarEmpleado() {
        System.out.println(accionVista);
        log.info("id = '" + id + "'");

        if (id != null && id > 0) {


            try {
                SecureLogger secureLogger = SecureLogger.getInstance();
                secureLogger.log("Eliminar empleado con id " + id);
                
                boolean result = Delegado_Empleado.getInstance().eliminarEmpleado(id);
                if (result) {
                    accionVista.setMensajeSuccess("Empleado borrado correctamente");
                } else {
                    accionVista.setMensajeWarning("Empleado no se pudo borrar correctamente");
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());

            } catch (EmpleadoException e) {
                e.printStackTrace();
                accionVista.setHayError(true);
                accionVista.setMensajeError(e.getMessage());
                accionVista.setMensajeError(e.getMessage());

            }

        } else {
            accionVista.setHayError(true);
            accionVista.setMensajeError("El ID debe ser un número positivo");
        }

        System.out.println(viewRequest);
        return viewRequest;
    }


    public String listarEmpleados() {
        accionVista.setAccion(AccionVista.AccionEnum.ACCION_LISTAR_EMPLEADOS);
        log.info(accionVista.toString());

        log.info(viewRequest);
        
        SecureLogger secureLogger = SecureLogger.getInstance();
        secureLogger.log("Listar empleados");
        
     
        listaEmpleados = Delegado_Empleado.getInstance().listarEmpleados();

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

    public TEmpleadoCompleto getEmpleadoCompleto() {
        return empleadoCompleto;
    }

    public void setEmpleadoCompleto(TEmpleadoCompleto empleadoCompleto) {
        this.empleadoCompleto = empleadoCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<? extends TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(String sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public String getHorasJornada() {
        return horasJornada;
    }

    public void setHorasJornada(String horasJornada) {
        this.horasJornada = horasJornada;
    }

    public String getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(String precioHora) {
        this.precioHora = precioHora;
    }

    public String getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(String idDepart) {
        this.idDepart = idDepart;
    }

    public TDepartamento[] getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(TDepartamento[] listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }


    public AccionVista getAccionVista() {
        return this.accionVista;
    }

    public String setAccion(String accion) {
        log.info(accion);

        accionVista.inicializarAtributos();
        this.accionVista.setAccion(AccionVista.AccionEnum.valueOf(accion));

        if (AccionVista.AccionEnum.valueOf(accion) == AccionVista.AccionEnum.ACCION_CREAR_EMPLEADO_TIEMPO_COMPLETO) {
            listaDepartamentos = Delegado_Departamento.getInstance().listarDepartamentos();
        }

        if (AccionVista.AccionEnum.valueOf(accion) == AccionVista.AccionEnum.ACCION_CREAR_EMPLEADO_TIEMPO_PARCIAL) {
            listaDepartamentos = Delegado_Departamento.getInstance().listarDepartamentos();
        }



        log.info(viewRequest);
        return viewRequest;
    }


    private void iniciarAtributos() {
        this.email = null;
        this.id = null;


        this.nombre = null;
        this.password = null;
        this.antiguedad = null;
        this.sueldoBase = null;
        this.horasJornada = null;
        this.precioHora = null;
        this.idDepart = null;


        this.empleadoCompleto = null;
        this.listaEmpleados = null;
        accionVista.inicializarAtributos();

    }


}

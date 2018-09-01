package com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Bean;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Rol;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoTParcial;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;
import com.rodrigo.TFG_cliente.Negocio.Utils.EmailValidator;
import com.rodrigo.TFG_cliente.Presentacion.actions.AccionVista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

    private String rol;


    private int antiguedad;

    private int sueldoBase;

    private int horasJornada;

    private int precioHora;

    private Long idDepart;


    private static Map<String, Object> roles;

    static {
        roles = new LinkedHashMap<String, Object>();
        roles.put("EMPLEADO", "EMPLEADO"); //label, value
        roles.put("ADMIN", "ADMIN");
    }


    private TEmpleadoCompleto empleadoCompleto;

    private List<TEmpleado> listaEmpleados;


    /****************************
     ********** METODOS *********
     ****************************/


    public String crearEmpleado() {
        System.out.println(accionVista);
        TEmpleado emple = null;

        if (nombre != null && nombre.trim() != "" &&
                email != null && new EmailValidator().validate(email.trim()) &&
                password != null && password.trim() != "" &&
                rol != null && rol.trim() != "") {


            if (idDepart > 0L) {


                if (antiguedad > 0 && sueldoBase > 0) {

                    emple = new TEmpleadoTCompleto(nombre, email, password, Rol.valueOf(rol), idDepart, antiguedad, sueldoBase);
                } else if (horasJornada > 0 && precioHora > 0) {
                    emple = new TEmpleadoTParcial(nombre, email, password, Rol.valueOf(rol), idDepart, horasJornada, precioHora);
                }

                try {
                    log.info("Creando Empleado en el sistema");
                    empleadoCompleto = Delegado_Empleado.getInstance().crearEmpleado(emple);


                } catch (EmpleadoYaExisteExcepcion e) {
                    e.printStackTrace();
                    accionVista.setHayError(true);
                    accionVista.setMensajeError(e.getMessage());

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
                accionVista.setMensajeWarning("El departamento no existe en la BBDD");
            }


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
        //super.accionVista = AccionEnum.BUSCAR_EMPLEADO_ID;
        System.out.println(accionVista);
        log.info("id = '" + id + "'");
//        iniciarAtributos();

        if (id != null && id > 0) {


            try {
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
        //super.accionVista = AccionEnum.BUSCAR_EMPLEADO_ID;
        System.out.println(accionVista);
        log.info("email = '" + email + "'");
//        iniciarAtributos();

        if (email != null && (new EmailValidator().validate(email.trim()))) {


            try {
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
        //super.accionVista = AccionEnum.ELIMINAR_EMPLEADO;
        System.out.println(accionVista);
        log.info("id = '" + id + "'");
//        iniciarAtributos();

        if (id != null && id > 0) {


            try {
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
//        iniciarAtributos();

        log.info(viewRequest);


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

    public List<TEmpleado> getListaEmpleados() {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Map<String, Object> getRoles() {
        return roles;
    }


    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(int sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public int getHorasJornada() {
        return horasJornada;
    }

    public void setHorasJornada(int horasJornada) {
        this.horasJornada = horasJornada;
    }

    public int getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(int precioHora) {
        this.precioHora = precioHora;
    }

    public Long getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Long idDepart) {
        this.idDepart = idDepart;
    }

//    public String getAccionVista() {
//        return String.valueOf(accionVista.getAccionVista());
//    }

    public AccionVista getAccionVista() {
        return this.accionVista;
    }

    public String setAccion(String accion) {
        log.info(accion);

        this.accionVista.setAccion(AccionVista.AccionEnum.valueOf(accion));
        iniciarAtributos();

        log.info(viewRequest);
        return viewRequest;
    }


    private void iniciarAtributos() {
        this.email = null;
        this.id = null;


        this.nombre = null;
        this.password = null;
        this.rol = null;
        this.antiguedad = 0;
        this.sueldoBase = 0;
        this.horasJornada = 0;
        this.precioHora = 0;
        this.idDepart = 0L;


        this.empleadoCompleto = null;
        this.listaEmpleados = null;
        accionVista.inicializarAtributos();

    }


}

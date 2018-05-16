package com.rodrigo.TFG_cliente.Presentacion.Modulo_Empleado.Action;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.Delegado_Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
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
import com.rodrigo.TFG_cliente.Presentacion.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "EmpleadoAction")
@SessionScoped
public class EmpleadoAction extends Action implements Serializable {


    /****************************
     ********  ATRIBUTOS  *******
     ****************************/


    final static Logger log = LoggerFactory.getLogger(EmpleadoAction.class);


    @ManagedProperty(value = "#{viewBean}")
    private String viewRequest;


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
        System.out.println(accion);

        String email = this.email;
        String nombre = this.nombre;
        String password = this.password;
        String rol = this.rol;
        int antiguedad = this.antiguedad;
        int sueldoBase = this.sueldoBase;
        int horasJornada = this.horasJornada;
        int precioHora = this.precioHora;
        Long idDepart = this.idDepart;
        TEmpleado emple = null;

        if (nombre != null && nombre.trim() != "" &&
                email != null && new EmailValidator().validate(email.trim()) &&
                password != null && password.trim() != "" &&
                rol != null && rol.trim() != "") {

            try {
                if (idDepart > 0L &&
                        Delegado_Departamento.getInstance().buscarByID(idDepart).getDepartamento() != null) {


                    if (antiguedad > 0 && sueldoBase > 0) {

                        emple = new TEmpleadoTCompleto(nombre, email, password, Rol.valueOf(rol), idDepart, antiguedad, sueldoBase);
                    }else if (horasJornada > 0 && precioHora > 0) {
                        emple = new TEmpleadoTParcial(nombre, email, password, Rol.valueOf(rol), idDepart, horasJornada, precioHora);
                    }

                    try {
                        log.info("Creando Empleado en el sistema");
                        empleadoCompleto = Delegado_Empleado.getInstance().crearEmpleado(emple);


                    } catch (EmpleadoYaExisteExcepcion e) {
                        e.printStackTrace();
                        hayError = true;
                        mensajeError = e.getMessage();

                    } catch (EmpleadoFieldInvalidException e) {
                        e.printStackTrace();
                        hayError = true;
                        mensajeError = e.getMessage();

                    } catch (EmpleadoException e) {
                        e.printStackTrace();
                        hayError = true;
                        mensajeError = e.getMessage();

                    }
                }else {
                    mensajeWarning = "El departamento no existe en la BBDD";
                }


            } catch (DepartamentoException e) {
                e.printStackTrace();
                hayError = true;
                mensajeError = e.getMessage();
            }

        }
        if (hayError) {
            if (emple instanceof TEmpleadoTCompleto)
            accion = Accion.ACCION_CREAR_EMPLEADO_TIEMPO_COMPLETO;
            else
            accion = Accion.ACCION_CREAR_EMPLEADO_TIEMPO_PARCIAL;
        }else{
            accion = Accion.ACCION_MOSTRAR_EMPLEADO;
            mensajeSuccess = "Empleado creado correctamente";
        }


        System.out.println(viewRequest);
        System.out.println("accion = [" + accion + "]");
        return viewRequest;
    }


    public String buscarById() {
        //super.accion = Accion.BUSCAR_EMPLEADO_ID;
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                empleadoCompleto = Delegado_Empleado.getInstance().buscarByID(id);
                if (empleadoCompleto == null) {
                    mensajeWarning = "Empleado no encontrado en la BBDD";
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (EmpleadoException e) {
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

        accion = Accion.ACCION_MOSTRAR_EMPLEADO;
        System.out.println(viewRequest);
        System.out.println("accion = [" + accion + "]");
        return viewRequest;
    }


    public String buscarByEmail() {
        //super.accion = Accion.BUSCAR_EMPLEADO_ID;
        System.out.println(accion);
        String email = this.email;
        log.info("email = '" + email + "'");
        iniciarAtributos();

        if (email != null && (new EmailValidator().validate(email.trim()))) {


            try {
                empleadoCompleto = Delegado_Empleado.getInstance().buscarByEmail(email.trim());
                if (empleadoCompleto == null) {
                    mensajeWarning = "Empleado no encontrado en la BBDD";
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (EmpleadoException e) {
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
            mensajeError = "El email en incorrecto.";
        }

        accion = Accion.ACCION_MOSTRAR_EMPLEADO;
        System.out.println(viewRequest);
        System.out.println("accion = [" + accion + "]");

        return viewRequest;
    }


    public String eliminarEmpleado() {
        //super.accion = Accion.ELIMINAR_EMPLEADO;
        System.out.println(accion);
        Long id = this.id;
        log.info("id = '" + id + "'");
        iniciarAtributos();

        if (id != null && id > 0) {


            try {
                boolean result = Delegado_Empleado.getInstance().eliminarEmpleado(id);
                if (result) {
                    mensajeSuccess = "Empleado borrado correctamente";
                } else {
                    mensajeWarning = "Empleado no se pudo borrar correctamente";
                }
            } catch (EmpleadoFieldInvalidException e) {
                e.printStackTrace();

                hayError = true;
                mensajeError = e.getMessage();

            } catch (EmpleadoException e) {
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


    public String listarEmpleados() {
        super.accion = Accion.ACCION_LISTAR_EMPLEADOS;
        log.info(accion.toString());
        iniciarAtributos();

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
        hayError = false;
        mensajeError = null;
        mensajeWarning = null;
        mensajeSuccess = null;

    }


}

package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad.EmpleadoProyecto;
import com.sun.xml.bind.CycleRecoverable;
import org.eclipse.persistence.oxm.annotations.XmlClassExtractor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlClassExtractor(EmpleadoClassExtractor.class)
@XmlSeeAlso({
        EmpleadoTParcial.class,
        EmpleadoTCompleto.class
})
public abstract class Empleado implements Serializable, CycleRecoverable {

    /****************************
     ********* ATRIBUTOS ********
     ****************************/


    protected Long id;

    @NotBlank
    protected String nombre;


    @NotBlank
    @Email
    protected String email;


    protected String password;

    @NotNull
    protected Rol rol;


    protected Departamento departamento;

//    protected List<EmpleadoProyecto> proyectos = null;
    protected List<EmpleadoProyecto> proyectos  = new ArrayList<>();

    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/


    public Empleado() {
    }


    public Empleado(String nombre, String password, Rol rol) {
        this.nombre = nombre;
        this.password = password;
        this.email = nombre.toLowerCase().concat("@gmail.com");
        this.rol = rol;
    }


    /**
     * Constructor
     * Rol por defecto = Rol.EMPLEADO
     */
    public Empleado(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.rol = Rol.EMPLEADO;
        this.email = nombre.toLowerCase().concat("@gmail.com");
    }

    public Empleado(Long id, String nombre, String password, String email, Rol rol, long version) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.email = email;
        this.version = version;
    }

    public Empleado(String nombre, String password, String email, Rol rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.email = email;

    }

    public Empleado(Empleado e) {
        this.id = e.id;
        this.nombre = e.nombre;
        this.password = e.password;
        this.rol = e.rol;
        this.email = e.email;
        this.version = e.version;
        this.departamento = new Departamento(e.departamento);
    }


    /****************************
     ********** METODOS *********
     ****************************/

    public abstract double calcularNominaMes();


    /****************************
     **** GETTERS AND SETTERS ***
     ****************************/

    //@XmlAttribute(name = "id", required = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //@XmlElement(name = "nombre", required = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //@XmlElement(name = "password", required = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


    //@XmlElement(name = "version", required = true)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


    //@XmlElement(name = "email", required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    public List<EmpleadoProyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<EmpleadoProyecto> proyectos) {
        this.proyectos = proyectos;
    }


    /****************************
     ****** OTHER METHODS *******
     ****************************/

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", dept='" + departamento.getSiglas() + '\'' +
                ", proySize=" + proyectos.size() +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return getVersion() == empleado.getVersion() &&
                Objects.equals(getId(), empleado.getId()) &&
                Objects.equals(getNombre(), empleado.getNombre()) &&
                Objects.equals(getEmail(), empleado.getEmail()) &&
                Objects.equals(getPassword(), empleado.getPassword()) &&
                getRol() == empleado.getRol();
    }

    public boolean equalsWithOutVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(getId(), empleado.getId()) &&
                Objects.equals(getNombre(), empleado.getNombre()) &&
                Objects.equals(getEmail(), empleado.getEmail()) &&
                Objects.equals(getPassword(), empleado.getPassword()) &&
                Objects.equals(getRol(), empleado.getRol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getPassword(), getRol(), getVersion());
    }
}

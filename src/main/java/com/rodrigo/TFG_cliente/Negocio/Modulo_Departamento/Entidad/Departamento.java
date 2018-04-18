package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.EmpleadoTCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.EmpleadoTParcial;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;


@XmlRootElement/*(name = "Departamento")*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Departamento implements Serializable/*, CycleRecoverable*/ {

    protected long id;

    private String nombre;


    private String siglas;

    /*@XmlElements({ //Con esto hace que se guarde la lista como -->
            @XmlElement(name = "EmpleadoTParcial", type = EmpleadoTParcial.class), //<empleados xsi:type="empleadoTParcial">
            @XmlElement(name = "EmpleadoTCompleto", type = EmpleadoTCompleto.class) //<empleados xsi:type="empleadoTCompleto">
    })*/
//    @XmlElementRef
    private Collection<Empleado> empleados;

    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/

    public Departamento() {
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.siglas = String.valueOf(
                Arrays.stream(nombre.split(" "))
                        .reduce("", (acum, pal) -> acum + String.valueOf(pal.charAt(0))));
    }


    public Departamento(Long id, String nombre, String siglas, long version) {
        this.id = id;
        this.nombre = nombre;
        this.siglas = siglas;
        this.version = version;

    }

    public Departamento(String nombre, String siglas) {
        this.nombre = nombre;
        this.siglas = siglas;
    }

    public Departamento(Departamento d) {
        this.id = d.id;
        this.nombre = d.nombre;
        this.siglas = d.siglas;
        this.version = d.version;
    }




    /****************************
     ********** METODOS *********
     ****************************/

    public double calcularNominaMes(){

        return empleados.stream()
                .map(Empleado::calcularNominaMes)
                .reduce(0.0,(acum, val)->acum + val);
    }



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


    //@XmlElement(name = "version", required = true)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    //@XmlElement(name = "siglas", required = true)
    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }


    //@XmlElement

    public Collection<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    /****************************
     ****** OTHER METHODS *******
     ****************************/

    @Override
    public String toString() {
        return "Departamento{" +
                "  id=" + id +
                ", nombre='" + nombre + '\'' +
                ", siglas='" + siglas + '\'' +
                ", EmpleadosSize='" +((empleados==null)?"null":empleados.size()) + '\'' +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento depto = (Departamento) o;
        return getVersion() == depto.getVersion() &&
                Objects.equals(getId(), depto.getId()) &&
                Objects.equals(getNombre(), depto.getNombre()) &&
                Objects.equals(getSiglas(), depto.getSiglas());
    }

    public boolean equalsWithOutVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento dpto = (Departamento) o;
        return Objects.equals(getId(), dpto.getId()) &&
                Objects.equals(getNombre(), dpto.getNombre()) &&
                Objects.equals(getSiglas(), dpto.getSiglas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getVersion());
    }


   /* @Override
    public Object onCycleDetected(Context cycleRecoveryContext) {
        // Context provides access to the Marshaller being used:
        //System.out.println("JAXB Marshaller is: " + cycleRecoveryContext.getMarshaller());

        //DepartmentPointer p = new DepartmentPointer();
        //p.id = this.id;
        Departamento p = new Departamento(this);
        return p;
    }*/

}



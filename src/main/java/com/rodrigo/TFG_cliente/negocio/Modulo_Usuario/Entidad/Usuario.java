package com.rodrigo.TFG_cliente.negocio.Modulo_Usuario.Entidad;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name = "Usuario")
public class Usuario implements Serializable {

   protected Long id;

    private String nombre;

    protected String password;

    protected long version;


    public Usuario(long l) {
        this.id = l;
    }


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/


    public Usuario() { }

    public Usuario(Long id) { this.id = id; }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;

    }

    public Usuario(Long id, String nombre, String password, long version) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.version = version;
    }

    /****************************
     **** GETTERS AND SETTERS ***
     ****************************/

    @XmlElement(name = "id", required = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "nombre", required = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "password", required = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "  id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", version=" + version +
                '}';
    }
}

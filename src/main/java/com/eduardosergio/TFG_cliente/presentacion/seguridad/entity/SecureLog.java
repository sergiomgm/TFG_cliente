package com.eduardosergio.TFG_cliente.presentacion.seguridad.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
       //@NamedQuery(name = "SecureLogger.insertar", query = "insert obj into logs obj"),

})
public class SecureLog implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY
    @Id
    protected Long id;

    @NotBlank
    @Column(nullable = false)
    @Email
    protected String email;
    
    @NotBlank
    @Column(nullable = false)
    protected String rol;


    @NotNull
    @Column(nullable = false)
    private Date fecha;
    
    @NotNull
    @Column(nullable = false)
    private String operacion;

    @Version
    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/

    
    public SecureLog(String email, String rol, String operacion) {
        this.email = email;
        this.fecha = new Date();
        this.rol = rol;
        this.operacion = operacion;
    }
    
    public SecureLog() {}


    /****************************
     **** GETTERS AND SETTERS ***
     ****************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getOperacion() {
        return this.operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    public void setFecha(String fecha) {
        this.fecha = new Date(fecha);
    }

    public Date getFecha() {
        return fecha;
    }


    /****************************
     ****** OTHER METHODS *******
     ****************************/

    @Override
    public String toString() {
        return "SecureLog{" +
                "  id=" + id +
                ", mail='" + email + '\'' +
                ", rol ='" + rol + '\'' +
                ", operacion ='" + operacion + '\'' +
                ", fecha='" + fecha.toString() + '\'' +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecureLog)) return false;
        SecureLog secureLogger = (SecureLog) o;
        return getVersion() == secureLogger.getVersion() &&
                Objects.equals(getId(), secureLogger.getId()) &&
                Objects.equals(getEmail(), secureLogger.getEmail()) &&
                Objects.equals(getRol(), secureLogger.getRol()) &&
                Objects.equals(getOperacion(), secureLogger.getOperacion()) &&
                Objects.equals(getFecha(), secureLogger.getFecha());
    }

    public boolean equalsWithOutVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecureLog)) return false;
        SecureLog secureLogger = (SecureLog) o;
        return Objects.equals(getId(), secureLogger.getId()) &&
                Objects.equals(getEmail(), secureLogger.getEmail()) &&
                Objects.equals(getRol(), secureLogger.getRol()) &&
                Objects.equals(getOperacion(), secureLogger.getOperacion()) &&
                Objects.equals(getFecha(), secureLogger.getFecha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFecha(), getVersion());
    }
}


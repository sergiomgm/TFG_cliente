package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.entidad;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

@Entity
@NamedQueries({
       //@NamedQuery(name = "SecureLogger.insertar", query = "insert obj into logs obj"),

})
public class SecureLog implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY
    @Column()
    @Id
    protected Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    protected String email;

    @Column(nullable = false)
    private String log;

    @NotNull
    @Column(nullable = false)
    private String fecha;

    @Version
    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/


    public SecureLog(String email) {
        this.email = email;
    }
    
    public SecureLog(String email, String log, String fecha) {
        this.email = email;
        this.log = log;
        this.fecha = fecha;
    }

    public SecureLog() {
    }

    /****************************
     **** GETTERS AND SETTERS ***
     ****************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
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
                ", log='" + log + '\'' +
                ", fecha='" + fecha + '\'' +
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
                Objects.equals(getLog(), secureLogger.getLog()) &&
                Objects.equals(getEmail(), secureLogger.getEmail()) &&
                Objects.equals(getFecha(), secureLogger.getFecha());
    }

    public boolean equalsWithOutVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecureLog)) return false;
        SecureLog secureLogger = (SecureLog) o;
        return Objects.equals(getId(), secureLogger.getId()) &&
                Objects.equals(getLog(), secureLogger.getLog()) &&
                Objects.equals(getEmail(), secureLogger.getEmail()) &&
                Objects.equals(getFecha(), secureLogger.getFecha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLog(), getFecha(), getVersion());
    }
}


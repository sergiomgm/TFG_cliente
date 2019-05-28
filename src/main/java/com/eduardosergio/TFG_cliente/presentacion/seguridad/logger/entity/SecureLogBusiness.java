package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class SecureLogBusiness implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY
    @Id
    protected Long id;

    @NotBlank
    @Column(nullable = false)
    protected String user;

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

    
    public SecureLogBusiness(String user, String operacion) {
        this.user = user;
        this.fecha = new Date();
        this.operacion = operacion;
    }
    
    public SecureLogBusiness() {}


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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
                ", user='" + user + '\'' +
                ", operacion ='" + operacion + '\'' +
                ", fecha='" + fecha.toString() + '\'' +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecureLogBusiness)) return false;
        SecureLogBusiness secureLogger = (SecureLogBusiness) o;
        return getVersion() == secureLogger.getVersion() &&
                Objects.equals(getId(), secureLogger.getId()) &&
                Objects.equals(getUser(), secureLogger.getUser()) &&
                Objects.equals(getOperacion(), secureLogger.getOperacion()) &&
                Objects.equals(getFecha(), secureLogger.getFecha());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFecha(), getVersion());
    }
}


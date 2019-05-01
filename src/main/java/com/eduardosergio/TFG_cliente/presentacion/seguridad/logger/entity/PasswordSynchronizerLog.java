package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PasswordSynchronizerLog implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY
    @Id
    protected Long id;

    @NotBlank
    protected String user;

    @NotNull
    private Date fecha;
    
    @NotNull
    private String servicio;
    
    @NotNull
    private String error;

    @Version
    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/

    
    public PasswordSynchronizerLog(String user, String operacion, String error) {
        this.user = user;
        this.fecha = new Date();
        this.servicio = operacion;
        this.error = error;
    }
    
    public PasswordSynchronizerLog() {}


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
        return this.servicio;
    }

    public void setOperacion(String operacion) {
        this.servicio = operacion;
    }
    
    @SuppressWarnings("deprecation")
	public void setFecha(String fecha) {
        this.fecha = new Date(fecha);
    }

    public Date getFecha() {
        return fecha;
    }
    
   
    public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	/****************************
     ****** OTHER METHODS *******
     ****************************/

    @Override
    public String toString() {
        return "PasswordSynchronizerLog{" +
                "  id=" + id +
                ", user='" + user + '\'' +
                ", operacion ='" + servicio + '\'' +
                ", fecha='" + fecha.toString() + '\'' +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordSynchronizerLog)) return false;
        PasswordSynchronizerLog passwordSynchronizerLog = (PasswordSynchronizerLog) o;
        return getVersion() == passwordSynchronizerLog.getVersion() &&
                Objects.equals(getId(), passwordSynchronizerLog.getId()) &&
                Objects.equals(getUser(), passwordSynchronizerLog.getUser()) &&
                Objects.equals(getOperacion(), passwordSynchronizerLog.getOperacion()) &&
                Objects.equals(getFecha(), passwordSynchronizerLog.getFecha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFecha(), getVersion());
    }
}

package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = "Usuario.listar", query = "FROM Usuario"),
        @NamedQuery(name = "Usuario.buscarPorEmail", query = "from Usuario e where e.email = :email")

})
public class Usuario implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY
    @Column()
    @Id
    protected Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;


    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    protected String email;

//    @ColumnTransformer(read = "pgp_sym_decrypt(password, ‘mySecretKey’)",
//            write = "pgp_sym_encrypt(?, ‘mySecretKey’)")
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private Rol rol;


    @Version
    protected long version;


    /****************************
     ******* CONSTRUCTORES ******
     ****************************/


    public Usuario(String nombre, String password, Rol rol) {
        this.nombre = nombre;

        this.email = nombre.toLowerCase().concat("@gmail.com");
        this.rol = rol;
        setPassword(password);
    }


    /**
     * Constructor
     * Rol por defecto = Rol.USUARIO
     */
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.rol = Rol.USUARIO;
        this.email = nombre.toLowerCase().concat("@gmail.com");
    }

    public Usuario(Long id, String nombre, String password, String email, Rol rol, long version) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.email = email;
        this.version = version;
    }

    public Usuario(String nombre, String password, String email, Rol rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.email = email;

    }

    public Usuario() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Retorna la password desencriptada
     *
     * @return password
     */
    public String getPassword() {
        // Decode data on other side, by processing encoded data
        byte[] valueDecoded = Base64.getDecoder().decode(this.password);
        System.out.println("this.password = [" + this.password + "]");
        System.out.println("Decoded pass is [" + new String(valueDecoded) + "]");
        return new String(valueDecoded);
    }

    /** Acutaliza la password y la mantiene encripotada
     *
     * @param password
     */
    public void setPassword(String password) {
        System.out.println("password = [" + password + "]");
        // Encode data on your side using BASE64
        byte[] bytesEncoded = Base64.getEncoder().encode(password.getBytes());
        System.out.println("encoded pass is [" + new String(bytesEncoded) + "]");

        this.password = new String(bytesEncoded);
        //this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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


    /****************************
     ****** OTHER METHODS *******
     ****************************/

    @Override
    public String toString() {
        return "Usuario{" +
                "  id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", version=" + version +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getVersion() == usuario.getVersion() &&
                Objects.equals(getId(), usuario.getId()) &&
                Objects.equals(getNombre(), usuario.getNombre()) &&
                Objects.equals(getEmail(), usuario.getEmail()) &&
                Objects.equals(getPassword(), usuario.getPassword()) &&
                getRol() == usuario.getRol();
    }

    public boolean equalsWithOutVersion(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getId(), usuario.getId()) &&
                Objects.equals(getNombre(), usuario.getNombre()) &&
                Objects.equals(getEmail(), usuario.getEmail()) &&
                Objects.equals(getPassword(), usuario.getPassword()) &&
                Objects.equals(getRol(), usuario.getRol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getPassword(), getRol(), getVersion());
    }
}

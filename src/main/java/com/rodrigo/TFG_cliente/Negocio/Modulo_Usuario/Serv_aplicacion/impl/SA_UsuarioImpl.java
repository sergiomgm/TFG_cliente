package com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl;


import com.rodrigo.TFG_cliente.Integracion.EMFSingleton;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.*;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.SA_Usuario;
import com.rodrigo.TFG_cliente.Negocio.Utils.EmailValidator;
import org.hibernate.PropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * The type Sa usuario.
 */
public class SA_UsuarioImpl implements SA_Usuario {

    private final static Logger log = LoggerFactory.getLogger(SA_UsuarioImpl.class);

    /**
     * Inserta un usuario en la BBDD
     *
     * @param usuarioNuevo
     * @return Usuario insertado en BBDD o null si la entidad ya existe
     * @throws UsuarioNullException      Si el param es null
     * @throws UsuarioFieldNullException Si el parametro es
     */
    public Usuario crearUsuario(Usuario usuarioNuevo) throws UsuarioException {
        log.info("creando usuario...");

        Usuario emple = null;
        log.debug("usuarioNuevo = '" + usuarioNuevo + "'");

        if (usuarioNuevo == null) {
            log.error("Usuario es null");
            throw new UsuarioException("El usuario para persistir en null", new UsuarioNullException("El usuario para persistir en null"));
        }

        if (usuarioNuevo.getEmail() == null || usuarioNuevo.getEmail() == "") {
            log.error("Email de usuario es null");

            try {
                throw new UsuarioFieldNullException(
                        new PropertyValueException("Usuario.email es erroneo.",
                                Usuario.class.toString(),
                                usuarioNuevo.getClass().getDeclaredField("email").toString()));
            } catch (NoSuchFieldException e) {
                log.error("Ocurrio un error inesperado.");
                throw new UsuarioException("Ocurrio un erro con el email.");
            }
        }

        EntityManager em = EMFSingleton.getInstance().createEntityManager();
        {
            log.debug("Iniciando transacción...");
            em.getTransaction().begin();
            {

                log.info("Buscando por email...");
                try {
                    emple = (Usuario) em
                            .createNamedQuery("Usuario.buscarPorEmail")
                            .setParameter("email", usuarioNuevo.getEmail())
                            .getSingleResult();


                } catch (NoResultException e) {
                    log.info("Usuario con email '" + usuarioNuevo.getEmail() + "' no encontrado");
                }


                if (emple == null) {

                    try {

                        log.info("Persistiendo usuario en BBDD...");
                        emple = em.merge(usuarioNuevo);
                        log.debug("result = '" + emple + "'");


                        log.debug("Terminando transacción...");
                        em.getTransaction().commit();


                    } catch (PersistenceException e2) {
                        log.error("Ocurrio una excepcion al persisitir: " + e2.getMessage());
                        log.error(e2.getStackTrace().toString());
                        em.getTransaction().rollback();

                        throw new UsuarioFieldNullException((PropertyValueException) e2.getCause());

                    } catch (Exception e) {
                        log.error("Ocurrió una error al persisitir en BBDD: " + e.getMessage());
                        log.error("EXCEPCION!", e);
                        em.getTransaction().rollback();

                        throw new UsuarioException("Ocurrió una error al persisitir en BBDD.");
                    } finally {

                        if (em.isOpen())
                            em.close();
                    }

                } else {
                    throw new UsuarioYaExisteExcepcion("Usuario ya existente");
                }
            }
        }

        return emple;
    }


    @Override
    public Usuario buscarByID(Long id) {
        Usuario user;

        log.info("Creando Entity Manager");
        EntityManager em = EMFSingleton.getInstance().createEntityManager();

        {
            em.getTransaction().begin();
            log.info("Buscando usuario en BBDD");
            user = em.find(Usuario.class, id);

            em.getTransaction().commit();
        }
        em.close();

        return user;
    }

    @Override
    public boolean eliminarUsuario(Usuario usuarioEliminar) {


        boolean result;

        EntityManager em = EMFSingleton.getInstance().createEntityManager();

        {
            em.getTransaction().begin();

            try {
                em.remove(em.find(Usuario.class, usuarioEliminar.getId()));
                result = true;
                em.getTransaction().commit();
            } catch (Exception e) {
                //em.getTransaction().rollback();
                result = false;
            }


        }
        if (em.isOpen())
            em.close();


        return result;
    }

    @Override
    public List<Usuario> listarUsuarios() {

        List<Usuario> lista;


        EntityManager em = EMFSingleton.getInstance().createEntityManager();
        {
            em.getTransaction().begin();

            lista = em.createNamedQuery("Usuario.listar").getResultList();

            em.getTransaction().commit();
        }
        em.close();

        return lista;
    }

    public String saludar(String nombre) {
        return "Hola " + nombre + ", un saludo desde el servidor CXF :)";
    }

    public Boolean loginUsuario(String email, String pass) throws UsuarioException {
        Usuario emple = null;
        log.debug("email = '" + email + "'");


        //Validacion del email
        if (email == null || email == "" || !new EmailValidator().validate(email)) {
            log.error("El email es invalido");

            try {
                throw new UsuarioFieldNullException(
                        new PropertyValueException("Usuario.email es erroneo.",
                                Usuario.class.toString(),
                                Usuario.class.getDeclaredField("email").toString()));
            } catch (NoSuchFieldException e) {
                log.error("Ocurrio un error inesperado.");
                throw new UsuarioException("Ocurrio un error con el email.");
            }
        }


        //Validacion del pass
        log.debug("pass = '" + pass + "'");
        if (pass == null || pass == "") {
            log.error("La password es invalido");

            try {
                throw new UsuarioFieldNullException(
                        new PropertyValueException("Usuario.password es erroneo.",
                                Usuario.class.toString(),
                                Usuario.class.getDeclaredField("password").toString()));
            } catch (NoSuchFieldException e) {
                log.error("Ocurrio un error inesperado.");
                throw new UsuarioException("Ocurrio un error con la password.");
            }
        }

        EntityManager em = EMFSingleton.getInstance().createEntityManager();
        try {


            {
                log.debug("Iniciando transacción...");
                em.getTransaction().begin();
                {
                    log.info("Buscando usuario en BBDD...");

                    log.info("Buscando usuario...");
                    try {

                        emple = (Usuario) em
                                .createNamedQuery("Usuario.buscarPorEmail")
                                .setParameter("email", email)
                                .getSingleResult();



                    } catch (NoResultException e) {
                        log.info("Usuario con email '" + email + "' no encontrado");
                        emple = null;
                    }
                    /*if (result.size() > 0) {
                        emple = (Usuario) result.get(0);
                    }*/
                    log.debug("emple = '" + emple + "'");

                }
                log.debug("Terminando transacción...");
                em.getTransaction().commit();
            }

        /*} catch (PersistenceException e2) {
            log.error("Ocurrio una excepcion al persisitir: " + e2.getMessage());
            log.error(e2.getStackTrace().toString());
            em.getTransaction().rollback();

            throw new UsuarioFieldNullException((PropertyValueException) e2.getCause());*/

        } catch (Exception e) {
            log.error("Ocurrió una error al persisitir en BBDD.");
            log.error("Mensaje: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            //em.getTransaction().rollback();

            throw new UsuarioException("Ocurrió una error al persisitir en BBDD.");
        } finally {

            if (em.isOpen())
                em.close();
        }


        if (emple == null) {
            throw new UsuarioLoginErroneo("Datos loginUsuario incorrectos");
        }

        log.info("Comporbando credenciales");

        if (email != null && emple.getEmail().equals(email) &&
                pass != null && emple.getPassword().equals(pass)) {

            log.info("LOGIN CORRECTO");
            return true;
        } else {
            log.info("LOGIN ERRONEO");
            return false;
        }

    }


    /**
     * Busca un Usuario por email
     *
     * @param email
     * @return retrona el usuario de la BBDD
     * Null si no existe
     */
    public Usuario buscarByEmail(String email) throws UsuarioException {
        Usuario emple = null;
        log.debug("email = '" + email + "'");


        //Validacion del email
        if (email == null || email == "" || !new EmailValidator().validate(email)) {
            log.error("El email es invalido");

            try {
                throw new UsuarioFieldNullException(
                        new PropertyValueException("Usuario.email es erroneo.",
                                Usuario.class.toString(),
                                Usuario.class.getDeclaredField("email").toString()));
            } catch (NoSuchFieldException e) {
                log.error("Ocurrio un error inesperado.");
                throw new UsuarioException("Ocurrio un error con el email.");
            }
        }


        log.info("Email correcto");
        EntityManager em = EMFSingleton.getInstance().createEntityManager();
        {
            em.getTransaction().begin();

            log.info("Buscando usuario...");
            try {
                emple = (Usuario) em
                        .createNamedQuery("Usuario.buscarPorEmail")
                        .setParameter("email", email)
                        .getSingleResult();


            } catch (NoResultException e) {
                log.info("Usuario con email '" + email + "' no encontrado");
                emple = null;
            }
            /*if (result.size() > 0) {
                emple = (Usuario) result.get(0);
            }*/

            em.getTransaction().commit();
        }
        if (em.isOpen())
            em.close();

        return emple;
    }


  /*  public boolean emIsOpen() {
        return em.isOpen();
    }

    public boolean transactionIsActive() {
        return em.getTransaction().isActive();
    }*/

  /*  @Override
    protected void finalize() throws Throwable {
        log.info("CERRANDO EMF");
        EMFSingleton.getInstance().close();
        super.finalize();
    }*/
}

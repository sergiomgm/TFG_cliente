package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLogger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.entidad.SecureLog;
import com.rodrigo.TFG_cliente.Integracion.EMFSingleton;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioFieldNullException;

public class SecureLoggerImpl extends SecureLogger {

	public void log(SecureLog secureLog) {
		EntityManager em = javax.persistence.Persistence.createEntityManagerFactory("SecurePipe").createEntityManager();
		
		em.getTransaction().begin();
		
		try {
			em.merge(secureLog);
			
			em.getTransaction().commit();
		} catch (PersistenceException e2) {
            em.getTransaction().rollback();
            System.out.println(e2.getCause());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ocurrió una error al persisitir en BBDD.");
        } finally {

            if (em.isOpen())
                em.close();
        }
	}
}

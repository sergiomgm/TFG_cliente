package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.Logger;

public class LoggerImp implements Logger {

	@Override
	public void write(SecureLog log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TFG_cliente");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			
			em.getTransaction().begin();
			
			
			
			em.persist(log);
				
				
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
		} catch (Exception e) {
			System.out.println("EXCEPCION AL PERSISTIR EL LOG : " + e.getMessage());
		}
		
		
	}
	
	@Override
	public void write(SecureLogBusiness log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TFG_cliente");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			
			em.getTransaction().begin();
			
			
			
			em.persist(log);
				
				
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
		} catch (Exception e) {
			System.out.println("EXCEPCION AL PERSISTIR EL LOG : " + e.getMessage());
		}
		
		
	}
}

package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.Logger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;

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

	@Override
	public void write(PasswordSynchronizerLog log) {
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
	public void delete(PasswordSynchronizerLog log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TFG_cliente");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			
			if (!em.contains(log)) {
				log = em.merge(log);
				
			}
			
			em.remove(log);
			em.getTransaction().commit();
			
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println("EXCEPCION AL PERSISTIR EL LOG : " + e.getMessage());
		}
		
	}
	
	
}

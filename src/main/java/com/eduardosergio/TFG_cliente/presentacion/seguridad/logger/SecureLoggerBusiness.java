package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.SecureLoggerBusinessImp;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.SecureLoggerImp;

public abstract class SecureLoggerBusiness {
	private static SecureLoggerBusiness instance;
	
	public static SecureLoggerBusiness getInstance() {
		if (instance == null) {
			instance = new SecureLoggerBusinessImp();
		}
		
		return instance;
	}
	
	public abstract void log(String user, String operation);

	
}



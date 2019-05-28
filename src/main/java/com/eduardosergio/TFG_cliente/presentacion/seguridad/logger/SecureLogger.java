package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.SecureLoggerImp;

public abstract class SecureLogger {
	private static SecureLogger instance;
	
	public static SecureLogger getInstance() {
		if (instance == null) {
			instance = new SecureLoggerImp();
		}
		
		return instance;
	}
	
	public abstract void log(String operation);

	
}



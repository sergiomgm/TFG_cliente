package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp.SecureLoggerBusinessImp;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp.SecureLoggerImp;

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



package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp.LogManagerImp;

public abstract class LogManager {
	private static LogManager instance;
	
	public static LogManager getInstance() {
		if (instance == null) {
			instance = new LogManagerImp();
		}
		
		return instance;
	}
	
	public abstract void log(SecureLog log);
	
	public abstract void log(SecureLogBusiness log);
}

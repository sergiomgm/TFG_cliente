package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLogger;

public class SecureLoggerImp extends SecureLogger {

	@Override
	public void log(String message) {
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(message);
	}

}

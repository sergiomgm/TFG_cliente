package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLoggerBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;


public class SecureLoggerBusinessImp extends SecureLoggerBusiness {

	@Override
	public void log(String user, String operation) {
		 
		SecureLogBusiness logMessage = new SecureLogBusiness(user, operation);
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(logMessage);
	}

}

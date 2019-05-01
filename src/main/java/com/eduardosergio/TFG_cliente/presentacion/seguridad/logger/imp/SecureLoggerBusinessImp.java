package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLoggerBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;


public class SecureLoggerBusinessImp extends SecureLoggerBusiness {

	@Override
	public void log(String user, String operation) {
		 
		SecureLogBusiness logMessage = new SecureLogBusiness(user, operation);
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(logMessage);
	}

}

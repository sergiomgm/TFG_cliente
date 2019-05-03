package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLoggerBusiness;


public class SecureLoggerBusinessImp extends SecureLoggerBusiness {

	@Override
	public void log(String user, String operation) {
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(user, operation);
	}

}

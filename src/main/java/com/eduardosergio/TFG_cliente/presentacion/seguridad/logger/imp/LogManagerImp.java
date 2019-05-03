package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.Logger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;

public class LogManagerImp extends LogManager {

	@Override
	public void log(String user, String rolDelUsuario, String operation) {
		Logger logger = LogFactory.getInstance().makeLogger();
		SecureLog logMessage = new SecureLog(user, rolDelUsuario, operation);
		
		logger.write(logMessage);
	}
	
	@Override
	public void log(String user, String operation) {
		SecureLogBusiness logMessage = new SecureLogBusiness(user, operation);
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(logMessage);
	}

	@Override
	public void log(PasswordSynchronizerLog log) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(log);
		
	}

	@Override
	public void deleteLog(PasswordSynchronizerLog log) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.delete(log);
	}
}

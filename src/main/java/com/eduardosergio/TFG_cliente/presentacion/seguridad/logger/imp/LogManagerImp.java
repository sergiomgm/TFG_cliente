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
	public Long logPasswordSynchronizer(String user, String password, String service, String error) {
		PasswordSynchronizerLog log = new PasswordSynchronizerLog(user, password, service, error);
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(log);
		return log.getId();
	}

	@Override
	public void deleteLog(Long passwordSynchronizerLogId) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.delete(passwordSynchronizerLogId);
	}

	@Override
	public void logError(Long passwordSynchronizerLogId, String error) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.logError(passwordSynchronizerLogId, error);
	}
}

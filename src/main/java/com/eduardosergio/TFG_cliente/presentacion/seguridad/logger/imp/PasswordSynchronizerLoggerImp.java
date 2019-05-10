package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.PasswordSynchronizerLogger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;

public class PasswordSynchronizerLoggerImp extends PasswordSynchronizerLogger {

	@Override
	public Long log(String user, String service, String error) {
		LogManager logManager = LogManager.getInstance();
		return logManager.logPasswordSynchronizer(user, service, error);
	}

	@Override
	public void deleteLog(Long passwordSynchronizerLogId) {
		LogManager logManager = LogManager.getInstance();
		logManager.deleteLog(passwordSynchronizerLogId);
	}

	@Override
	public void logError(Long passwordSynchronizerLogId, String error) {
		LogManager logManager = LogManager.getInstance();
		logManager.logError(passwordSynchronizerLogId, error);
	}

}

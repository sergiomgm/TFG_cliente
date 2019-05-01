package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.PasswordSynchronizerLogger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;

public class PasswordSynchronizerLoggerImp extends PasswordSynchronizerLogger {

	@Override
	public void log(PasswordSynchronizerLog log) {
		LogManager logManager = LogManager.getInstance();
		logManager.log(log);
	}

	@Override
	public void deleteLog(PasswordSynchronizerLog log) {
		LogManager logManager = LogManager.getInstance();
		logManager.deleteLog(log);
	}

}

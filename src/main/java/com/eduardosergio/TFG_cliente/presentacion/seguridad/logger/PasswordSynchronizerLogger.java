package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.PasswordSynchronizerLoggerImp;

public abstract class PasswordSynchronizerLogger {
	private static PasswordSynchronizerLogger instance;
	
	public static PasswordSynchronizerLogger getInstance() {
		if (instance == null) {
			instance = new PasswordSynchronizerLoggerImp();
		}
	
		return instance;
	}
	
	public abstract void log(PasswordSynchronizerLog log);
	public abstract void deleteLog(PasswordSynchronizerLog log);
}

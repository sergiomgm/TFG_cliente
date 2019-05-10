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
	
	public abstract Long log(String user, String service, String error);
	public abstract void logError(Long passwordSynchronizerLogId, String error);
	public abstract void deleteLog(Long passwordSynchronizerLogId);
}

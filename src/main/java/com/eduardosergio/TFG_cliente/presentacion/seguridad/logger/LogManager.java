package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.LogManagerImp;

public abstract class LogManager {
	private static LogManager instance;
	
	public static LogManager getInstance() {
		if (instance == null) {
			instance = new LogManagerImp();
		}
		
		return instance;
	}
	
	public abstract void log(String user, String rolDelUsuario, String operation);
	public abstract void log(String user, String operation);
	public abstract Long logPasswordSynchronizer(String user, String password, String service, String error);
	public abstract void deleteLog(Long passwordSynchronizerLogId);
	public abstract void logError(Long passwordSynchronizerLogId, String serviciosQueHanFallado, String error);
}

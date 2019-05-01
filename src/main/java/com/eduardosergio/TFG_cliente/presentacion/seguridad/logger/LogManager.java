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
	
	public abstract void log(SecureLog log);
	public abstract void log(SecureLogBusiness log);
	public abstract void log(PasswordSynchronizerLog log);
	public abstract void deleteLog(PasswordSynchronizerLog log);
}

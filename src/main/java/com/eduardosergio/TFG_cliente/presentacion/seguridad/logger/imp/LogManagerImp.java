package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.Logger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;

public class LogManagerImp extends LogManager {

	@Override
	public void log(SecureLog log) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(log);
	}
	
	@Override
	public void log(SecureLogBusiness log) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(log);
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

package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.Logger;

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
}

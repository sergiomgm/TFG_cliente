package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.Logger;

public class LogManagerImp extends LogManager {

	@Override
	public void log(String message) {
		Logger logger = LogFactory.getInstance().makeLogger();
		logger.write(message);
	}
}

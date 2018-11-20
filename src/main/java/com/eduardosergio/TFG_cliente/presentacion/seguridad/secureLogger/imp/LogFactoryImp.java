package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.Logger;

public class LogFactoryImp extends LogFactory {

	@Override
	public Logger makeLogger() {
		return new LoggerImp();
	}
}

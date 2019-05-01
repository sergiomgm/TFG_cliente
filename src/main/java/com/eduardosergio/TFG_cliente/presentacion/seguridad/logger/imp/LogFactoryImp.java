package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogFactory;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.Logger;

public class LogFactoryImp extends LogFactory {

	@Override
	public Logger makeLogger() {
		return new LoggerImp();
	}
}

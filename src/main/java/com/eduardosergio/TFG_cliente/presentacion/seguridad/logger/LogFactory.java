package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp.LogFactoryImp;

public abstract class LogFactory {
	private static  LogFactory instance;
	
	public static LogFactory getInstance() {
		if (instance == null) {
			instance = new LogFactoryImp();
		}
		
		return instance;
	}
	
	public abstract Logger makeLogger();
}

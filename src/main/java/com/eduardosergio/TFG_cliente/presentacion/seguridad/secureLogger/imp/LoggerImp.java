package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.Logger;

public class LoggerImp implements Logger {

	@Override
	public void write(String message) {
		System.out.println(message);
	}
}

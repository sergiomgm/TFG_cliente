package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;

public interface Logger {
	public void write(SecureLog log);
	
	public void write(SecureLogBusiness log);
}

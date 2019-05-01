package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.SecureLogBusiness;

public interface Logger {
	public void write(SecureLog log);
	
	public void write(SecureLogBusiness log);
	
	public void write(PasswordSynchronizerLog log);
	public void delete(PasswordSynchronizerLog log);
}

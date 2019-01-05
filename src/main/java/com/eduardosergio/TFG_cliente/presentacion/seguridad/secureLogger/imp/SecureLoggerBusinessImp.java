package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLogBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLoggerBusiness;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLogger;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Rol;

public class SecureLoggerBusinessImp extends SecureLoggerBusiness {

	@Override
	public void log(String user, String operation) {
		 
		SecureLogBusiness logMessage = new SecureLogBusiness(user, operation);
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(logMessage);
	}

}

package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.imp;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.entity.SecureLog;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLogger;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Rol;

public class SecureLoggerImp extends SecureLogger {

	@Override
	public void log(String operation) {
		
		HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String user = origRequest.getRemoteUser();
		String rolDelUsuario = "";
		
		for (Rol rol : Rol.values()) {
			if (origRequest.isUserInRole(rol.toString())) {
				rolDelUsuario = rol.toString();
			}
		}
		 
		SecureLog logMessage = new SecureLog(user, rolDelUsuario, operation);
		
		LogManager logManager = LogManager.getInstance();
		logManager.log(logMessage);
	}

}

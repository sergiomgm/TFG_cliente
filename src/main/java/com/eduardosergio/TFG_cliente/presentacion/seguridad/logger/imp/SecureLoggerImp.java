package com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.imp;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.LogManager;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.SecureLogger;
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
		 
		LogManager logManager = LogManager.getInstance();
		logManager.log(user, rolDelUsuario, operation);
	}

}

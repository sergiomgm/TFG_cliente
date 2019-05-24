package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.PasswordSynchronizerLogger;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.entity.PasswordSynchronizerLog;

public class SMRImpl extends SMR {

	@Override
	public void synchronize(String user, String pass) {
		HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String userOfTheRequest = origRequest.getRemoteUser();
		Integer response;
		
		Long passwordSynchronizerLogId = PasswordSynchronizerLogger.getInstance().log(user, pass, "sts1", "");
		try {
			response = SSODelegator.getInstance().syncSts1(user, pass);
			if (response < 0) {
				PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, "Error al modificar las credenciales del STS1");
			} else {
				PasswordSynchronizerLogger.getInstance().deleteLog(passwordSynchronizerLogId);
			}
		} catch (Exception e) {
			PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, e.getMessage());
		}
		
		
		passwordSynchronizerLogId = PasswordSynchronizerLogger.getInstance().log(user, pass, "sts2", "");
		try {
			response = SSODelegator.getInstance().syncSts2(user, pass);
			if (response < 0) {
				PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, "Error al modificar las credenciales del STS2");
			} else {
				PasswordSynchronizerLogger.getInstance().deleteLog(passwordSynchronizerLogId);
			}
		} catch (Exception e) {
			PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, e.getMessage());
		}
		
		
		passwordSynchronizerLogId = PasswordSynchronizerLogger.getInstance().log(user, pass, "rest", "");
		try {
			response = SSODelegator.getInstance().syncRest(user, pass);
			if (response < 0) {
				PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, "Error al modificar las credenciales del servicio REST");
			} else {
				PasswordSynchronizerLogger.getInstance().deleteLog(passwordSynchronizerLogId);
			}
		} catch (Exception e) {
			PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, e.getMessage());
		}
	}
	
}

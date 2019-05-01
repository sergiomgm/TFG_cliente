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
		
		PasswordSynchronizerLog log = new PasswordSynchronizerLog(userOfTheRequest, "sts1", "");
		PasswordSynchronizerLogger.getInstance().log(log);
		
		SSODelegator.getInstance().syncSts1(user, pass);
		
		// Si no hay error
		PasswordSynchronizerLogger.getInstance().deleteLog(log);
		
		
		log = new PasswordSynchronizerLog(userOfTheRequest, "sts2", "");
		PasswordSynchronizerLogger.getInstance().log(log);
		SSODelegator.getInstance().syncSts2(user, pass);
		// Si no hay error
		PasswordSynchronizerLogger.getInstance().deleteLog(log);
		
		
		log = new PasswordSynchronizerLog(userOfTheRequest, "rest", "");
		PasswordSynchronizerLogger.getInstance().log(log);
		SSODelegator.getInstance().syncRest(user, pass);
		// Si no hay error
		PasswordSynchronizerLogger.getInstance().deleteLog(log);
	}
	
}

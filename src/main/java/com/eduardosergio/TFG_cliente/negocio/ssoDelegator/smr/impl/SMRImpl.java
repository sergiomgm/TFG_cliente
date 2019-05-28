package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl;



import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.logger.PasswordSynchronizerLogger;

public class SMRImpl extends SMR {

	@Override
	public void synchronize(String user, String pass) {
		Integer response;
		boolean hayError = false;
		String serviciosQueHanFallado = "";
		String error = "";
		
		Long passwordSynchronizerLogId = PasswordSynchronizerLogger.getInstance().log(user, pass, "", "");
		
		try {
			response = SSODelegator.getInstance().syncSts1(user, pass);
			if (response < 0) {
				hayError = true;
				serviciosQueHanFallado += "sts1";
			} 
		} catch (Exception e) {
			hayError = true;
			serviciosQueHanFallado += "sts1";
			error += "STS1 : " + e.getMessage();
		} 
		
		try {
			response = SSODelegator.getInstance().syncSts2(user, pass);
			if (response < 0) {
				hayError = true;
				serviciosQueHanFallado += " - sts2";
			} 
		} catch (Exception e) {
			hayError = true;
			serviciosQueHanFallado += " - sts2";
			error += "- STS2 : " + e.getMessage();
		}
		
		try {
			response = SSODelegator.getInstance().syncRest(user, pass);
			if (response < 0) {
				hayError = true;
				serviciosQueHanFallado += " - rest";
			} 
		} catch (Exception e) {
			hayError = true;
			serviciosQueHanFallado += " - rest";
			error += "- REST : " + e.getMessage();
		}
		
		if (hayError) {
			PasswordSynchronizerLogger.getInstance().logError(passwordSynchronizerLogId, serviciosQueHanFallado, error);
		} else {
			PasswordSynchronizerLogger.getInstance().deleteLog(passwordSynchronizerLogId);
		}
	}
	
}

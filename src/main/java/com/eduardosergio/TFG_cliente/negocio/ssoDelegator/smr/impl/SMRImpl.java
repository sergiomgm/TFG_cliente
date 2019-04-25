package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;

public class SMRImpl extends SMR {

	@Override
	public void synchronize(String user, String pass) {
		SSODelegator.getInstance().syncSts1(user, pass);
		SSODelegator.getInstance().syncSts2(user, pass);
		SSODelegator.getInstance().syncRest(user, pass);
		
	}
	
}

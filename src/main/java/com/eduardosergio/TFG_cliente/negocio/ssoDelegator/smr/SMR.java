package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl.SMRImpl;

public abstract class SMR {
	private static SMR instance;
	
	public static SMR getInstance() {
		if (instance == null) {
			instance = new SMRImpl();
		}
		return instance;
	}
	
	public abstract void invokeServices();
}

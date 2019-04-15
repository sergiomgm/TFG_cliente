package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl;

import java.util.Arrays;
import java.util.List;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.SSODelegator;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;

public class SMRImpl extends SMR {

	@Override
	public void invokeServices() {
		System.out.println(SSODelegator.getInstance().salute());
		System.out.println(SSODelegator.getInstance().saludar());
		System.out.println(SSODelegator.getInstance().salutare());
		List<TDepartamento> listaDepartamento = Arrays.asList(SSODelegator.getInstance().listarDepartamentos());
    	
    	System.out.println(listaDepartamento.toString());
		
	}
	
}

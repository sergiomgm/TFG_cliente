package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.impl;

import java.util.Arrays;
import java.util.List;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.Delegado;
import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.smr.SMR;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;

public class SMRImpl extends SMR {

	@Override
	public void invokeServices() {
		System.out.println(Delegado.getInstance().salute());
		System.out.println(Delegado.getInstance().saludar());
		//System.out.println(Delegado.getInstance().salutare());
		List<TDepartamento> listaDepartamento = Arrays.asList(Delegado.getInstance().listarDepartamentos());
    	
    	System.out.println(listaDepartamento.toString());
		
	}
	
}

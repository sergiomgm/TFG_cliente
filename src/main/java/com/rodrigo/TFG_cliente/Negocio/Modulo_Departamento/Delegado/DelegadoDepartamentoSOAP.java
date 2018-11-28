package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl.DelegadoDepartamentoSOAPImp;

public abstract class DelegadoDepartamentoSOAP {
	private static DelegadoDepartamentoSOAP ourInstance = new DelegadoDepartamentoSOAPImp();
	
	public static DelegadoDepartamentoSOAP getInstance() {
        return ourInstance;
    }
}

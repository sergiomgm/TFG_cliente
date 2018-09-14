package com.rodrigo.TFG_cliente.Presentacion.seguridad.jaas;

import java.security.Principal;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class RolePrincipal implements Principal {
	
	private String name;
	
	public RolePrincipal(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}

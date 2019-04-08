package com.eduardosergio.TFG_cliente.negocio.seguridad.departamento.credentialTokenizer;

public class UserToken {
	
	String user;
	String password;

	public UserToken(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getPassword() {
		return this.password;
	}
}

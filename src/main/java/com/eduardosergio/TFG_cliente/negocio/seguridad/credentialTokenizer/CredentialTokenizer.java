package com.eduardosergio.TFG_cliente.negocio.seguridad.credentialTokenizer;

public class CredentialTokenizer {
	private final String USERNAME = "user";
	private final String PASSWORD = "pass";
	
	public CredentialTokenizer() {
		
	}
	
	public UserToken getUserToken() {
		return new UserToken(USERNAME, PASSWORD);
	}
}
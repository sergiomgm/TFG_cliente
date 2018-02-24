package com.rodrigo.TFG_cliente.presentacion.seguridad.jaas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule {

	final static Logger log = LoggerFactory.getLogger(EjemploLoginModule.class);

	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroups;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		log.info("subject = [" + subject + "], callbackHandler = [" + callbackHandler + "], sharedState = [" + sharedState + "], options = [" + options + "]");;
		handler = callbackHandler;
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {
			handler.handle(callbacks);
			String name = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());


            log.info("name = '" + name + "' -- " + " password = '" + password + "'");

			// Here we validate the credentials against some
			// authentication/authorization provider.
			// It can be a Database, an external LDAP, a Web Service, etc.
			// For this tutorial we are just checking if user is "user123" and
			// password is "pass123"
			if (name != null && name.equals("") && password != null && password.equals("")) {
				login = name;
				userGroups = new ArrayList<String>();
				userGroups.add("admin");
				log.debug("name = " + name);

				return true;
			}

			if (name != null && name.equals("user") && password != null && password.equals("123")) {
				login = name;
				userGroups = new ArrayList<String>();
				userGroups.add("user");
				log.debug("name = " + name);
				return true;
			}


			if (name != null && name.equals("superuser") && password != null && password.equals("123")) {
				login = name;
				userGroups = new ArrayList<String>();
				userGroups.add("superuser");
				log.debug("name = " + name);
				return true;
			}

			// If credentials are NOT OK we throw a LoginException
			throw new LoginException("Authentication failed");

		} catch (IOException e) {
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}

	}

	@Override
	public boolean commit() throws LoginException {

		userPrincipal = new UserPrincipal(login);
		subject.getPrincipals().add(userPrincipal);

		if (userGroups != null && userGroups.size() > 0) {
			log.info("userGroups = " + userGroups);
			for (String groupName : userGroups) {
				rolePrincipal = new RolePrincipal(groupName);
				subject.getPrincipals().add(rolePrincipal);
			}
		}

		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(rolePrincipal);
		return true;
	}

}

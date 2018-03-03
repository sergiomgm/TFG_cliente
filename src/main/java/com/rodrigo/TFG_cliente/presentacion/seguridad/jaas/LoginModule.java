package com.rodrigo.TFG_cliente.presentacion.seguridad.jaas;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoLoginErroneo;
import com.rodrigo.TFG_cliente.presentacion.proxy.Excepciones.ProxyException;
import com.rodrigo.TFG_cliente.presentacion.proxy.Proxy;
import com.rodrigo.TFG_cliente.presentacion.proxy.imp.Proxy_Empleado;
import com.sun.org.apache.regexp.internal.RE;
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

public class LoginModule implements javax.security.auth.spi.LoginModule {

    final static Logger log = LoggerFactory.getLogger(LoginModule.class);

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {

        log.info("subject = [" + subject + "], callbackHandler = [" + callbackHandler + "], sharedState = [" + sharedState + "], options = [" + options + "]");
        ;
        handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        boolean loginOk = false;

        try {
            handler.handle(callbacks);
            String email = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());


            log.info("name = '" + email + "' -- " + " password = '" + password + "'");


            if (email != null && password != null) {
                Proxy_Empleado proxy_empleado = new Proxy_Empleado();
                loginOk = proxy_empleado.loginEmpleado(email, password);

                if(loginOk){
                    log.info("LOGIN CORRECTO");
                    Empleado emple = proxy_empleado.buscarByEmail(email);
                    login = emple.getEmail();
                    userGroups = new ArrayList<String>();
                    userGroups.add(emple.getRol().toString());
                    log.debug("name = " + email);
                }

                return loginOk;

            }


            // Here we validate the credentials against some
            // authentication/authorization provider.
            // It can be a Database, an external LDAP, a Web Service, etc.
            // For this tutorial we are just checking if user is "user123" and
            // password is "pass123"
            if (email != null && email.equals("") && password != null && password.equals("")) {
                login = email;
                userGroups = new ArrayList<String>();
                userGroups.add("admin");
                log.debug("name = " + email);

                return true;
            }

            if (email != null && email.equals("user") && password != null && password.equals("123")) {
                login = email;
                userGroups = new ArrayList<String>();
                userGroups.add("user");
                log.debug("name = " + email);
                return true;
            }


            if (email != null && email.equals("superuser") && password != null && password.equals("123")) {
                login = email;
                userGroups = new ArrayList<String>();
                userGroups.add("superuser");
                log.debug("name = " + email);
                return true;
            }

            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");

        } catch (IOException e) {
            log.error("Error en login: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            log.error("Error en login: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            throw new LoginException(e.getMessage());
        } catch (ProxyException e) {
            log.error("Error en login: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            throw new LoginException("Authentication failed");
        } catch (EmpleadoException e) {
            log.error("Error en login: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            throw new LoginException("Authentication failed");
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

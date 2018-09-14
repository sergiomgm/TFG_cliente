package com.rodrigo.TFG_cliente.Presentacion.seguridad.jaas;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.FactoriaSA;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Entidad.Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Excepciones.UsuarioException;
import com.rodrigo.TFG_cliente.Negocio.Utils.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
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

            if (new EmailValidator().validate(email)) {
                loginOk = FactoriaSA.getInstance().crearSA_Usuario().loginUsuario(email, password);

                if (loginOk) {
                    log.info("LOGIN CORRECTO");
                    Usuario usuario = FactoriaSA.getInstance().crearSA_Usuario().buscarByEmail(email);
                    login = usuario.getEmail();
                    userGroups = new ArrayList<String>();
                    userGroups.add(usuario.getRol().toString());
                    log.info("name = " + email);
                }

                return loginOk;

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
        } catch (UsuarioException e) {
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

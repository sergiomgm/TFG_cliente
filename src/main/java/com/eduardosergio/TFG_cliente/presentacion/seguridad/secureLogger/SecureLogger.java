package com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.impl.SecureLoggerImpl;

public abstract class SecureLogger {

    private static SecureLogger ourInstance = new SecureLoggerImpl();

    public static SecureLogger getInstance() {
        return ourInstance;
    }

}

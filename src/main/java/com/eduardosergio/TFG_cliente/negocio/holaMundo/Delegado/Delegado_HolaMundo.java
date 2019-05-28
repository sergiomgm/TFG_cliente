package com.eduardosergio.TFG_cliente.negocio.holaMundo.Delegado;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.impl.Delegado_HelloWorldImpl;
import com.eduardosergio.TFG_cliente.negocio.holaMundo.Delegado.impl.Delegado_HolaMundoImpl;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Delegado_HolaMundo  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_HolaMundo.class);



    private static Delegado_HolaMundoImpl ourInstance;

    static {

        try {
            ourInstance = new Delegado_HolaMundoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_HolaMundoImpl getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract String saludar();
}

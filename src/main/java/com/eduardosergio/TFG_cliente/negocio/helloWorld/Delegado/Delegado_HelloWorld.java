package com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado;

import com.eduardosergio.TFG_cliente.negocio.helloWorld.Delegado.impl.Delegado_HelloWorldImpl;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Delegado_HelloWorld  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_HelloWorld.class);



    private static Delegado_HelloWorldImpl ourInstance;

    static {

        try {
            ourInstance = new Delegado_HelloWorldImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_HelloWorldImpl getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract String salute();
}

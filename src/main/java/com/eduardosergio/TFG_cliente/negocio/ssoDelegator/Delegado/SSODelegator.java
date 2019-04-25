package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.impl.SSODelegatorImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SSODelegator  {

    private final static Logger log = LoggerFactory.getLogger(SSODelegator.class);



    private static SSODelegatorImpl ourInstance;

    static {

        try {
            ourInstance = new SSODelegatorImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static SSODelegatorImpl getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract void syncSts1(String user, String pass);
    public abstract void syncSts2(String user, String pass);
    public abstract void syncRest(String user, String pass); 
}

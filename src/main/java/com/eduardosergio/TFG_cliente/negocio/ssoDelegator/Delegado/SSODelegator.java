package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.impl.SSODelegatorImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SSODelegator  {

    private final static Logger log = LoggerFactory.getLogger(SSODelegator.class);



    private static SSODelegator ourInstance;

    static {

        try {
            ourInstance = new SSODelegatorImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static SSODelegator getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract Integer syncSts1(String user, String pass) throws Exception;
    public abstract Integer syncSts2(String user, String pass) throws Exception;
    public abstract Integer syncRest(String user, String pass) throws Exception; 
}

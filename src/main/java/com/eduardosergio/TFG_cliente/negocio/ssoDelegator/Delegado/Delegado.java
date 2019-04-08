package com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado;

import com.eduardosergio.TFG_cliente.negocio.ssoDelegator.Delegado.impl.DelegadoImpl;
import com.eduardosergio.TFG_cliente.presentacion.seguridad.secureLogger.SecureLoggerBusiness;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Delegado  {

    private final static Logger log = LoggerFactory.getLogger(Delegado.class);



    private static DelegadoImpl ourInstance;

    static {

        try {
            ourInstance = new DelegadoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static DelegadoImpl getInstance() {
        log.info("retornando instancia ");
        return ourInstance;
    }


    public abstract String saludar();
    public abstract String salute();
    public abstract String salutare();
    public abstract TDepartamento[] listarDepartamentos();
}

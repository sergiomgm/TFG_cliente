package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado;

import com.rodrigo.TFG_cliente.Negocio.DelegadoDelNegocio;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl.Delegado_DepartamentoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Departamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Delegado_Departamento implements DelegadoDelNegocio {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Departamento.class);

    protected final String SERVICE_NAME = "/departamento";

    protected final String URL = HOST + ":" + PORT + APP_URI +  "/SA_Departamento" + SERVICE_NAME;



    private static Delegado_Departamento ourInstance;

    static {
        try {
            ourInstance = new Delegado_DepartamentoImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }


    public static Delegado_Departamento getInstance() {
        return ourInstance;
    }

    public abstract Departamento crearDepartamento(Departamento departamentoNuevo) throws DepartamentoException;

    public abstract Departamento buscarDepartamentoByID(Long id);

    public abstract boolean eliminarDepartamento(Long id);

    public abstract Departamento[] listarDepartamentos();

    public abstract String saludar(String nombre);

    public abstract Departamento getDepartamentoCompleto(Long id);

    public abstract Departamento buscarByXXXXTest(Long id);

    public abstract Departamento buscarBySiglas(String siglas) throws DepartamentoException;
}

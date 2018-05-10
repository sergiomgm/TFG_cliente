package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado;

import com.rodrigo.TFG_cliente.Negocio.DelegadoDelNegocio;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl.Delegado_DepartamentoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Delegado_Departamento implements DelegadoDelNegocio {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Departamento.class);

    protected final String SERVICE_NAME = "/departamento";

    protected final String URL = HOST + ":" + PORT + APP_URI +  "/SA_Departamento" + SERVICE_NAME;



    private static Delegado_Departamento ourInstance = new Delegado_DepartamentoImpl();


    public static Delegado_Departamento getInstance() {
        return ourInstance;
    }

    public abstract TDepartamento crearDepartamento(TDepartamento departamentoNuevo) throws DepartamentoException;

    public abstract TDepartamentoCompleto buscarDepartamentoByID(Long id);


    public abstract boolean eliminarDepartamento(TDepartamento departEliminar);

    public abstract TDepartamento[] listarDepartamentos();

    public abstract String saludar(String nombre);


    public abstract TDepartamentoCompleto buscarBySiglas(String siglas) throws DepartamentoException;
}

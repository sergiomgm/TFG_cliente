package com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Delegado.impl.Delegado_DepartamentoImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public abstract class Delegado_Departamento  {

    private final static Logger log = LoggerFactory.getLogger(Delegado_Departamento.class);


    private static Delegado_Departamento ourInstance = new Delegado_DepartamentoImpl();


    public static Delegado_Departamento getInstance() {
        return ourInstance;
    }

    public abstract TDepartamento crearDepartamento(TDepartamento departamentoNuevo) throws DepartamentoYaExisteExcepcion, DepartamentoException;

    public abstract TDepartamentoCompleto buscarByID(Long id) throws DepartamentoException;


    public abstract boolean eliminarDepartamento(Long id) throws DepartamentoFieldInvalidException, DepartamentoNoEncontradoException, DepartamentoConEmpleadosException;

    public abstract TDepartamento[] listarDepartamentos();


    public abstract TDepartamentoCompleto buscarBySiglas(String siglas) throws DepartamentoException;
}

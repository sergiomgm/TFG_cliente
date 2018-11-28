package com.eduardosergio.TFG_cliente.negocio.modulo_Departamento.serv_aplicacion;


import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamento;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers.TEmpleadoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoFieldInvalidException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoYaExisteExcepcion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(
		//He tenido que quitar el impl.
        targetNamespace = "http://Serv_aplicacion.modulo_departamento.negocio.TFG_server.eduardosergio.com/",
        name = "SSP_SA_DepartamentoImpl")
public interface SSP_SA_Departamento {

	@WebMethod(operationName="buscarByID")
    public TDepartamentoCompleto buscarByID(@WebParam(name = "id") Long id) throws DepartamentoFieldInvalidException,  DepartamentoException;
	
}


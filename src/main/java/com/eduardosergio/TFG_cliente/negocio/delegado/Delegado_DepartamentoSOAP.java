package com.eduardosergio.TFG_cliente.negocio.delegado;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eduardosergio.TFG_cliente.negocio.delegado.impl.Delegado_DepartamentoSOAPImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Entidad.Transfers.TDepartamentoCompleto;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Departamento.Excepciones.DepartamentoException;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.Delegado_Empleado;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Delegado.impl.Delegado_EmpleadoImpl;
import com.rodrigo.TFG_cliente.Presentacion.Proxy.Excepciones.ProxyException;

public abstract class Delegado_DepartamentoSOAP {
	private static Delegado_DepartamentoSOAP ourInstance;
	private final static Logger log = LoggerFactory.getLogger(Delegado_Empleado.class);
	 
	static {

        try {
            log.info("Delegado_Empleado.static initializer");
            ourInstance = new Delegado_DepartamentoSOAPImpl();
        } catch (ProxyException e) {
            log.error("Error al crear el DelegadoDelNegocio", e);
        }
    }

	
	public static Delegado_DepartamentoSOAP getInstance() {
        return ourInstance;
    }
	
	public abstract TDepartamentoCompleto buscarByID(Long id) throws DepartamentoException;
}

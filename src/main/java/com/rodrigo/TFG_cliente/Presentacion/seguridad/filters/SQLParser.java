package com.rodrigo.TFG_cliente.Presentacion.seguridad.filters;

import com.rodrigo.TFG_cliente.Presentacion.seguridad.filters.imp.SQLParserImp;

public abstract class SQLParser {
	
	protected static SQLParser instance;
	
	public static SQLParser getInstance(){
		if (instance == null) instance= new SQLParserImp();
		return instance;
	}
	
	public abstract boolean isSafe(String cadena);
}

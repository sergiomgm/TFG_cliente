package com.eduardosergio.TFG_cliente.presentacion.seguridad.filters;

import com.eduardosergio.TFG_cliente.presentacion.seguridad.filters.imp.SQLParserImp;

public abstract class SQLParser {
	
	protected static SQLParser instance;
	
	public static SQLParser getInstance(){
		if (instance == null) instance= new SQLParserImp();
		return instance;
	}
	
	public abstract boolean isSafe(String cadena);
}

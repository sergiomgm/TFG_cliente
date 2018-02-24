package com.rodrigo.TFG_cliente.presentacion.actions;

import java.io.Serializable;

public class Eventos implements Serializable{

	//Si fuera Java 7 el switch del controlador entender�a String y no har�a falta esta conversion
	
	public static final int INDICE= 1;
	public static final int FORMULARIO= 2;
	public static final int INFO= 3;
	public static final int SUMAR= 4;
	public static final int RESTAR= 5;
	
	public Eventos(){}
	
	public int getINDICE()
	{
		return INDICE;
	}
	
	public int getFORMULARIO()
	{
		return FORMULARIO;
	}
	
	public int getINFO()
	{
		return INFO;
	}
	
	public int getSUMAR()
	{
		return SUMAR;
	}
	
	public int getRESTAR()
	{
		return RESTAR;
	}
}

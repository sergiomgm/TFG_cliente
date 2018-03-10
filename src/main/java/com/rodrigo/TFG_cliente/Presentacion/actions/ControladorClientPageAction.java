package com.rodrigo.TFG_cliente.Presentacion.actions;

import java.io.Serializable;

public class ControladorClientPageAction implements Serializable {
	
	int evento;
	
	public ControladorClientPageAction()
	{
		evento= 0;
	}
	
	public void setEvento(int eP)
	{
		evento= eP;
	}
	
	public int getEvento()
	{
		return evento;
	}
	

	public String accion()
	{   
		
	/*	String value = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("evento");
	*/	
		String respuesta= null;

		System.out.println("EventoControlador: " + evento);
		switch (evento)
		{

			case Eventos.INDICE: { respuesta= "indice"; break; }
			case Eventos.FORMULARIO : {respuesta= "formulario"; break; }
			case Eventos.INFO : {respuesta= "info"; break; }
			default: { respuesta= "indice"; break; }
		}
		
		return respuesta;
	}

}

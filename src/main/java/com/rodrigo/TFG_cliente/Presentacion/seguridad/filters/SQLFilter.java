package com.rodrigo.TFG_cliente.Presentacion.seguridad.filters;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SQLFilter implements Filter {

	FilterConfig filterConfig= null;
	
	public void init(FilterConfig filterConfig) throws ServletException 
	{
	      this.filterConfig = filterConfig;
	}
	
	public void destroy() 
	{
	   this.filterConfig = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		Map<String, String []> parametros= request.getParameterMap();
		Iterator<String []> it= parametros.values().iterator();
		*/
		
		Iterator it = request.getParameterMap().entrySet().iterator();
		
		boolean isSafe = true;

		while (it.hasNext() && isSafe)
		{
			Map.Entry par = (Map.Entry) it.next();

			if (par != null && !par.getKey().equals("javax.faces.ViewState")) { 
				String[] parametro = (String[]) par.getValue();
				
				for (int i=0; i<parametro.length ; i++) {
					isSafe = isSafe= SQLParser.getInstance().isSafe(parametro[i]);
				}
			}
		}
		
		if (!isSafe) {
			try {
				HttpServletResponse resp = (HttpServletResponse) response;
				
				resp.sendError(400);
			
			} catch (Exception e) { e.printStackTrace(); }
			
		} else chain.doFilter(request, response);
	}
}

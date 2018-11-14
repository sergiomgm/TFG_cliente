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

public class ValidatorFilter implements Filter {

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
		
		Map<String, String []> parametros= request.getParameterMap();
		Iterator<String []> it= parametros.values().iterator();
		
		boolean isSafe = true;
		
		Pattern p = Pattern.compile("[\\w@\\-\\.\\+/=\\s]+");
		Matcher m = null;
		
		while (it.hasNext() && isSafe)
		{
			String [] arrayParametros= it.next();
			
			System.out.println("En el bucle while");
			if (arrayParametros != null) { 
				int longitud= arrayParametros.length;
			
				for (int i=0; i<longitud ; i++) {
					System.out.println(arrayParametros[i]);
					m = p.matcher(arrayParametros[i]);
					
					if (!m.matches()) {
						isSafe = false;
					}
				}
			}
		}
		
		if (!isSafe) {
			try {
				HttpServletRequest req = (HttpServletRequest) request;
				String vista = "/views/Errors/403-error.xhtml";
				System.out.println(vista);
				req.getRequestDispatcher(vista).forward(request, response);
			
			} catch (Exception e) { e.printStackTrace(); }
			
		} else chain.doFilter(request, response);
	}
}

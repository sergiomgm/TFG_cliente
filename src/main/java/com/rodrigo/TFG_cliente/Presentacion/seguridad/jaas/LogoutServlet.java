package com.rodrigo.TFG_cliente.Presentacion.seguridad.jaas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
@WebServlet(name = "logoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Invalidate current HTTP session.
		// Will call LoginModule logout() method
		request.getSession().invalidate();

		// Redirect the user to the secure web page.
		// Since the user is now logged out the
		// authentication form will be shown
		response.sendRedirect(request.getContextPath() + "/views/index.xhtml");
		
	}

}

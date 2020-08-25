package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deconnexion extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		HttpSession session = request.getSession(true); 
		session.setAttribute("utilisateur", null);
		getServletContext().getRequestDispatcher("/").forward(request, response);
	}
	
}

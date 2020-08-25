package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;

public class Ajout extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		getServletContext().getRequestDispatcher("/ajouter.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ 
		HttpSession session = request.getSession(true); 
		if (session.getAttribute("utilisateur")!=null) {
			boolean valide = false;
			String titre = request.getParameter("titre");
			String auteur = request.getParameter("auteur");
			String type = request.getParameter("type");
			String description = request.getParameter("description");
			if (type!=null && titre!=null && auteur!=null && description!=null
					&& !titre.isEmpty() && !auteur.isEmpty() && !description.isEmpty()) {
				try {
					Mediatheque.getInstance().nouveauDocument(Integer.parseInt(type), titre, auteur, description);
					valide = true;
				} catch (Exception e) {
					e.printStackTrace();
					valide = false;
				}
				request.setAttribute("valide", valide);
			}
		}
		getServletContext().getRequestDispatcher("/ajouter.jsp").forward(request, response);
	}
	
}

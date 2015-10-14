package controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import consommation.*;


/**
 * Servlet implementation class Home
 */

@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		action = (action == null ? "show" : action);
		
		switch(action) {
			case "textplain":
				this.show(request, response);
				break;
			
			case "xml":
				this.showXML(request, response);
				break;
				
			case "json":
				this.showJson(request, response);
				break;
				
			case "show":
			default:
				this.show(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		action = (action == null ? "show" : action);
		
		switch(action) {
			case "textplain":
				this.show(request, response);
				break;
			
			case "xml":
				this.showXML(request, response);
				break;
				
			case "json":
				this.showJson(request, response);
				break;
				
			case "show":
			default:
				this.show(request, response);
		}
	}

	/**
	 * Méthode pour afficher la liste dans un tableau
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reponse ;
		try {
			
			
			
			Appel unAppel = new Appel();
			 reponse = unAppel.appelTextPlain();
			request.setAttribute("reponse", reponse);
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/afficheGetParametre.jsp");
			disp.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/erreur.jsp");
			disp.forward(request, response);
		}
	}
	
	
	protected void showXML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reponse ;
		try {
			
			Appel unAppel = new Appel();
			 reponse = unAppel.appelXml();
			request.setAttribute("reponse", reponse);
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/afficheXml.jsp");
			disp.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/erreur.jsp");
			disp.forward(request, response);
		}
	}
	
	protected void showJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reponse ;
		try {
			
			Appel unAppel = new Appel();
			 reponse = unAppel.appelJson();
			request.setAttribute("reponse", reponse);
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/afficheJson.jsp");
			disp.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/erreur.jsp");
			disp.forward(request, response);
		}
	}
	
}

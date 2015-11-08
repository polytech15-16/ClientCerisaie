package controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import consommation.Appel;

/**
 * Servlet implementation class Home
 */

@Controller
public class Controleur extends MultiActionController {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	// Affiche les informations de l'utilisateur "id"
	@RequestMapping(value = "/user/{id:.+}", method = RequestMethod.GET)
	public ModelAndView showUser(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/show_user");
		try {
			Appel unAppel = new Appel();
			String reponse = unAppel.getClient(Integer.parseInt(id));

			model.addObject("user", reponse);
		} catch (Exception e) {
			model.addObject("erreur", e.getMessage());
			model.setViewName("erreur");
		}
		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/list_users");

		String reponse;
		try {
			Appel unAppel = new Appel();
			reponse = unAppel.getClients();
			model.addObject("users", reponse);
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/erreur.jsp");
			disp.forward(request, response);
		}
		return model;
	}

	/**
	 * Méthode pour afficher la liste dans un tableau
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reponse;
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

	protected void showXML(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reponse;
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

	protected void showJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reponse;
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

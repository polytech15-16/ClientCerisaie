package controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import consommation.Appel;
import metier.Client;

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
		model.addObject("title", "Accueil");
		model.addObject("url", request.getContextPath());
		return model;
	}

	// Affiche les informations de l'utilisateur "id"
	@RequestMapping(value = "/user/{id:.+}", method = RequestMethod.GET)
	public ModelAndView showUser(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/show_user");
		model.addObject("title", "Informations utilisateur");
		model.addObject("url", request.getContextPath());

		try {
			Appel unAppel = new Appel();
			String reponse = unAppel.getClient(Integer.parseInt(id));
			System.out.println(reponse);
			model.addObject("user", reponse);
		} catch (Exception e) {
			model.addObject("erreur", e.getMessage());
			model.setViewName("erreur");
		}
		return model;
	}

	// Ajouter un utilisateur
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/add_user");
		model.addObject("title", "Ajouter un utilisateur");
		model.addObject("url", request.getContextPath());
		return model;
	}

	// Editer un utilisateur
	@RequestMapping(value = "/user/{id:.+}/edit", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/add_user");

		Appel unAppel = new Appel();
		String reponse = unAppel.getClient(Integer.parseInt(id));
		ObjectMapper mapper = new ObjectMapper();
		try {
			Client c = mapper.readValue(reponse, Client.class);
			System.out.println(c.toString());
			model.addObject("client", c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("edit", true);
		model.addObject("title", "Editer un utilisateur");
		model.addObject("url", request.getContextPath());
		return model;
	}

	// Delete utilisateur
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();

		String numCli = request.getParameter("numCli");
		Appel unAppel = new Appel();
		String reponse = unAppel.deleteClient(Integer.parseInt(numCli));
		model.setViewName("users/delete_user");
		model.addObject("result", reponse);
		return model.addObject("url", request.getContextPath());
	}

	// Save utilisateur
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.addObject("url", request.getContextPath());

		Client c = new Client();
		Map<String, String> erreur = verifyUser(request, c);

		// On a des erreurs on n'enregistre pas l'utilisateur
		if (erreur.size() > 0) {
			model.addObject("erreur", erreur);
			model.addObject("client", c);
			model.setViewName("users/add_user");
			return model;
		} else {// On enregistre l'utilisateur
			try {
				Appel unAppel = new Appel();
				String reponse = unAppel.saveClient(c);
				model.addObject("notification", reponse);
				model.setViewName("index");
				return model;
			} catch (IOException e) {
				model.addObject("notification", e.getMessage());
				return model;
			}
		}
	}

	private Map<String, String> verifyUser(HttpServletRequest request, Client c) {
		Map<String, String> erreur = new HashMap<String, String>();
		String numCli = request.getParameter("numCli");
		if (numCli != null) {
			c.setNumCli(Integer.parseInt(numCli));
		}

		String nom = request.getParameter("nom");
		if (nom == null | nom.length() < 3) {
			erreur.put("nom", "Le nom doit contenir au moins 3 caractères.");
		} else {
			c.setNomCli(nom);
		}
		String adresse = request.getParameter("adresse");
		if (adresse == null | adresse.length() < 3) {
			erreur.put("adresse", "L'adresse doit contenir au moins 3 caractères.");
		} else {
			c.setAdrRueCli(adresse);
		}
		String ville = request.getParameter("ville");
		if (ville == null | ville.length() < 3) {
			erreur.put("ville", "La ville doit contenir au moins 3 caractères.");
		} else {
			c.setVilleCli(ville);
		}
		String codePostal = request.getParameter("codePostal");
		if (codePostal == null | codePostal.length() != 5) {
			erreur.put("codePostal", "Le code postal doit contenir 5 caractères.");
		} else {
			c.setCpCli(codePostal);
		}
		String pieceCli = request.getParameter("pieceCli");
		if (pieceCli == null | pieceCli.length() < 2) {
			erreur.put("pieceCli", "La pièce du client doit contenir au moins 2 caractères.");
		} else {
			c.setPieceCli(pieceCli);
		}
		String numPieceCli = request.getParameter("numPieceCli");
		if (numPieceCli == null | (numPieceCli.length() < 2 && numPieceCli.length() > 10)) {
			erreur.put("numPieceCli", "Le numéro de pièce client doit contenir entre 2 et 10 caractères.");
		} else {
			c.setNumPieceCli(numPieceCli);
		}
		return erreur;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("users/list_users");
		model.addObject("title", "Informations utilisateurs");
		model.addObject("url", request.getContextPath());

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

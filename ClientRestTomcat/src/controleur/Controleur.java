package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import consommation.Appel;
import metier.Client;
import metier.Emplacement;
import metier.Sejour;

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
			int idClient = Integer.parseInt(id);
			Appel unAppel = new Appel();
			String reponse = unAppel.getClient(idClient);
			model.addObject("user", reponse);
			reponse = "";
			reponse = unAppel.getSejoursOfClient(idClient);
			model.addObject("sejours", reponse);
		} catch (Exception e) {
			model.addObject("erreur", e.getMessage());
			model.setViewName("erreur");
		}
		return model;
	}

	// Affiche les informations d'un séjour
	@RequestMapping(value = "/sejour/{id:.+}", method = RequestMethod.GET)
	public ModelAndView showSejour(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletRequestBindingException {
		String sejour = null;
		Sejour s = null;
		String output = ServletRequestUtils.getStringParameter(request, "output");
		String type = ServletRequestUtils.getStringParameter(request, "type");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Informations séjour");
		model.addObject("url", request.getContextPath());

		try {
			Appel unAppel = new Appel();
			sejour = unAppel.getSejour(Integer.parseInt(id));
			String tmp = sejour;
			
			// Parse le json
			sejour = sejour.replace("{\"activites\":{", "{\"activites\":[{");
			sejour = sejour.replace("}},\"client\":", "}}],\"client\":");
			ObjectMapper mapper = new ObjectMapper();
			s = mapper.readValue(sejour, Sejour.class);
		} catch (Exception e) {
			model.addObject("erreur", e.getMessage());
			model.setViewName("erreur");
			e.printStackTrace();
		}

		if (output == null || "".equals(output)) {
			// return normal view
			model.addObject("sejour", sejour);
			model.setViewName("sejour/show_sejour");
		} else if ("PDF".equals(output.toUpperCase())) {
			// return pdf view
			model.addObject("sejour", s);
			if ("sejour".equals(type)) {
				model.setViewName("PdfFactureSejour");
			} else if ("activite".equals(type)) {
				model.setViewName("PdfFactureActivite");
			}
		} else {
			// return normal view
			model.addObject("sejour", sejour);
			model.setViewName("sejour/show_sejour");
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

	@RequestMapping(value = "/sejour", method = RequestMethod.GET)
	public ModelAndView listSejours(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("sejour/list_sejours");
		model.addObject("title", "Informations séjours");
		model.addObject("url", request.getContextPath());
		String reponse;
		try {
			Appel unAppel = new Appel();
			reponse = unAppel.getSejours();
			model.addObject("sejours", reponse);
		} catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/erreur.jsp");
			disp.forward(request, response);
		}
		return model;
	}

	// Ajouter un séjour
	@RequestMapping(value = "/sejour/add", method = RequestMethod.GET)
	public ModelAndView addSejour(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("sejour/add_sejour");

		// Envoi les clients et emplacement possibles
		Appel unAppel = new Appel();

		addClientsAndEmplacements(model, unAppel);

		model.addObject("title", "Ajouter un séjour");
		model.addObject("url", request.getContextPath());
		return model;
	}

	@RequestMapping(value = "/sejour/{id:.+}/edit", method = RequestMethod.GET)
	public ModelAndView editSejour(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("sejour/edit_sejour");

		Appel unAppel = new Appel();
		String reponse = unAppel.getSejour(Integer.parseInt(id));

		addClientsAndEmplacements(model, unAppel);

		ObjectMapper mapper = new ObjectMapper();
		try {
			Sejour s = mapper.readValue(reponse, Sejour.class);
			model.addObject("sejour", s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("title", "Editer un séjour");
		model.addObject("url", request.getContextPath());
		return model;
	}

	private void addClientsAndEmplacements(ModelAndView model, Appel unAppel) {
		String clients = unAppel.getClients();
		String emplacements = unAppel.getEmplacements();
		model.addObject("clients", clients);
		model.addObject("emplacements", emplacements);
	}

	// Save utilisateur
	@RequestMapping(value = "/sejour/save", method = RequestMethod.POST)
	public ModelAndView saveSejour(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.addObject("url", request.getContextPath());
		Appel unAppel = new Appel();

		Sejour s = new Sejour();
		Map<String, String> erreur = verifySejour(request, s);
		System.out.println(s.toString());

		// On a des erreurs on n'enregistre pas le sejour
		if (erreur.size() > 0) {
			model.addObject("erreur", erreur);
			model.addObject("sejour", s);
			addClientsAndEmplacements(model, unAppel);

			// Si le séjour a un numéro --> modification
			if (s.getNumSej() != null) {
				model.setViewName("sejour/edit_sejour");
			} else {
				model.setViewName("sejour/add_sejour");
			}
			return model;
		} else {// On enregistre l'utilisateur
			try {
				String reponse = unAppel.saveSejour(s);
				model.addObject("notification", reponse);
				model.setViewName("index");
				return model;
			} catch (IOException e) {
				model.addObject("notification", e.getMessage());
				return model;
			}
		}
	}

	// Delete sejour
	@RequestMapping(value = "/sejour/delete", method = RequestMethod.POST)
	public ModelAndView deleteSejour(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		String numSejour = request.getParameter("numSej");
		Appel unAppel = new Appel();
		String reponse = unAppel.deleteSejour(Integer.parseInt(numSejour));
		model.setViewName("sejour/delete_sejour");
		model.addObject("result", reponse);
		return model.addObject("url", request.getContextPath());
	}

	private Map<String, String> verifySejour(HttpServletRequest request, Sejour s) {
		Map<String, String> erreur = new HashMap<String, String>();
		String numSej = request.getParameter("numSej");
		if (numSej != null) {
			s.setNumSej(Integer.parseInt(numSej));
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dateDebSej = null;
		String dateDeb = request.getParameter("dateDebSej");
		try {
			dateDebSej = sdf.parse(dateDeb);
		} catch (ParseException e) {
			erreur.put("dateDebSej", "La date de début n'est pas valide.");
		}
		s.setDateDebSej(dateDebSej);

		Date dateFinSej = null;
		String dateFin = request.getParameter("dateFinSej");
		try {
			dateFinSej = sdf.parse(dateFin);
		} catch (ParseException e) {
			erreur.put("dateFinSej", "La date de fin n'est pas valide.");
		}
		s.setDateFinSej(dateFinSej);

		if (dateDebSej != null && dateFinSej != null && dateDebSej.after(dateFinSej)) {
			erreur.put("dateDebSej", "La date de début est après la date de fin.");
		}

		try {
			int nbPersonnes = Integer.parseInt(request.getParameter("nbPersonnes"));
			s.setNbPersonnes(nbPersonnes);
		} catch (NumberFormatException e) {
			erreur.put("nbPersonnes", "Le nombre de personnes n'est pas correct");
		}

		try {
			int client = Integer.parseInt(request.getParameter("client"));
			Client c = new Client();
			c.setNumCli(client);
			s.setClient(c);
		} catch (NumberFormatException e) {
			erreur.put("client", "Le numéro du client n'est pas correct.");
		}

		try {
			int emplacement = Integer.parseInt(request.getParameter("emplacement"));
			Emplacement e = new Emplacement();
			e.setNumEmpl(emplacement);
			s.setEmplacement(e);
		} catch (NumberFormatException e) {
			erreur.put("emplacement", "Le numéro de l'emplacement n'est pas correct.");
		}
		return erreur;
	}

}

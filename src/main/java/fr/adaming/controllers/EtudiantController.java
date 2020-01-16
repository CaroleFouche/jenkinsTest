package fr.adaming.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

import fr.adaming.services.IEtudiantService;

@Controller
@RequestMapping("/ecole")
public class EtudiantController {

	// Transformation de l'association UML en Java
	@Autowired
	private IEtudiantService etuService;

	private Formateur formateur;

	// Le setter pour l'injection de dependance
	public void setEtuService(IEtudiantService etuService) {
		this.etuService = etuService;
	}

	@PostConstruct
	public void init() {
		this.formateur = new Formateur(1, "a", "a");
	}

	// La méthode appelée pour convertir les valeurs des parametres de la requete en
	// objet java(par ex: Date)
	@InitBinder // Pour quelle soit appelée lors de la conversion
	public void initBinder(WebDataBinder binder) {
		// l'objet WebDataBinder sert a faire le lien entre les params de la requete et
		// les objets java
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		df.setLenient(false);
		// la methode register custom editor: a configurer la conversion du param recu
		// au type de l'attribut
		// L'objet customdateeditor : sert a lier la date recu comme param de la requete
		// a l'attribut de l'objet etudiant

		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	}

	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		// recuperer la liste des étudiants du formateur

		List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);

		return new ModelAndView("accueil", "etudiants", listeEtudiants);
	}

	// =====Fonctionnalité ajouter etudiant ===========

	@RequestMapping(value = "/afficheAdd", method = RequestMethod.GET)
	public String afficheAjouter(Model modele) {
		modele.addAttribute("eAdd", new Etudiant());
		return "ajout";
	}

	@RequestMapping(value = "/submitAdd", method = RequestMethod.POST)
	public String soumettreAjouter(ModelMap modele, @ModelAttribute("eAdd") Etudiant eIn) {
		// appel de la méthode ajouter de service
		Etudiant eOut = etuService.addEtudiant(eIn, this.formateur);
		if (eOut.getId() != 0) {
			// recuperer la liste des etudiants du formateur
			List<Etudiant> listeEtudiants = etuService.getAllEtudiants(this.formateur);
			// Mettre a jour la liste dans le model
			modele.addAttribute("etudiants", listeEtudiants);
			return "accueil";
		} else {
			return "redirect:afficheAdd"; // Redirection vers la méthode afficheAdd pour afficher le formulaire et lui
											// associer l'etudiant
		}
	}

	// ============== Fonctionnalité update Etudiant ==================
	@RequestMapping(value = "/afficheUpdate", method = RequestMethod.GET)
	public ModelAndView affichemodif() {
		return new ModelAndView("modif", "eUp", new Etudiant());
	}

	@RequestMapping(value = "/submitUpdate", method = RequestMethod.POST)
	public String soumettreUpdate(RedirectAttributes rda, Model modele, @ModelAttribute("eUp") Etudiant eIn) {
		boolean b = etuService.updateEtudiant(eIn, this.formateur);
		if (b) {
			return "redirect:liste";
		} else {
			rda.addFlashAttribute("message", "L'étudiant n'existe pas");
			return "redirect:afficheUpdate";
		}
	}

	// ==============Fonctionnalité supprimer ==========================
	@RequestMapping(value = "/afficheDelete", method = RequestMethod.GET)
	public String afficheSupprime() { // pas besoin de model
		return "supp";
	}

	@RequestMapping(value = "/submitDelete", method = RequestMethod.GET)
	public String soumettreSupprime(RedirectAttributes rda, @RequestParam("pId") int idIn) {
		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);
		boolean verif = etuService.deleteEtudiant(eIn, this.formateur);
		if (verif) {
			return "redirect:liste";
		}

		// L'objet redirectAttributes sert a transporter les attributs du modele MVC
		// lors de la redirection
		rda.addFlashAttribute("message", "L'étudiant n'existe pas");
		return "redirect:afficheDelete";
	}

	// ===========Fonctionnalité rechercher============================
	@RequestMapping(value = "/afficheSearch", method = RequestMethod.GET)
	public String afficheRecherche() { // pas besoin de model
		return "recherche";
	}

	@RequestMapping(value = "/submitSearch", method = RequestMethod.GET)
	public String soumettreRecherche(RedirectAttributes rda, @RequestParam("pId") int idIn) {
		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);
		Etudiant eOut = etuService.getEtudiantById(eIn);
		if (eOut != null) {
			rda.addFlashAttribute("eGet", eOut);
			return "redirect:afficheSearch";
		}
		// L'objet redirectAttributes sert a transporter les attributs du modele MVC
		// lors de la redirection
		rda.addFlashAttribute("message", "L'étudiant n'existe pas");
		return "redirect:afficheSearch";
	}
	
	

	@RequestMapping(value = "/linkUpdate", method = RequestMethod.GET)
	public String modifLien(Model modele, @RequestParam("pId") int idIn) {
		Etudiant eIn = new Etudiant();
		eIn.setId(idIn);
		Etudiant eOut = etuService.getEtudiantById(eIn);
		modele.addAttribute("eUp", eOut);
		return "modif";
	}
}

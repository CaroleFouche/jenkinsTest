package fr.adaming.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;
import fr.adaming.services.IEtudiantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class EtudiantServiceTest {

	@Autowired
	private IEtudiantService etuService;

	Formateur f; 
	
	@Before
	public void setUp() {
		this.f = new Formateur();
		this.f.setId(1);
	}
	
	
	@Test
	@Transactional(readOnly=true)
	public void testGetEtudiantById() {
		Etudiant eIn = new Etudiant();
		eIn.setId(7);
		Etudiant eOut = etuService.getEtudiantById(eIn);
		
		String nomRecup = eOut.getNom();
		String nomAttendu = "toto";
		assertEquals(nomRecup, nomAttendu);
	}
	
	
	@Test
	@Transactional(readOnly=true)
	public void testGetAllEtudiantsNom() {
		String rAttendu = "Toto";
		List<Etudiant> listeE = etuService.getAllEtudiants(f);
		String rObtenu = listeE.get(0).getNom();
		assertEquals(rAttendu, rObtenu);
	}
	
	@Test
	@Transactional
	public void testAddEtudiant() {
		
		List<Etudiant> listeAvant = etuService.getAllEtudiants(f);
		int avant = listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(f);
		etuService.addEtudiant(etu, f);
		
		List<Etudiant> listeApres = etuService.getAllEtudiants(f);
		int apres = listeApres.size();
		
		assertEquals(avant +1, apres);
	}
	
	@Test
	@Rollback(value=true)
	@Transactional
	public void testDeleteEtudiant() {
		
		List<Etudiant> listeAv = etuService.getAllEtudiants(this.f);
		int rAttendu = listeAv.size();
		Etudiant eIn = new Etudiant();
		eIn.setId(4);
		eIn.setFormateur(f);
		
		//Mettre l'etudiant dans le contexte
		eIn = etuService.getEtudiantById(eIn);
		
		etuService.deleteEtudiant(eIn, this.f);
		
		List<Etudiant> liste = etuService.getAllEtudiants(this.f);
		
		int rObtenu = liste.size();
		
		assertNotEquals(rAttendu, rObtenu);
		
	}
	
	@Test
	@Transactional
	public void testUpdateEtudiant() {
		Etudiant etu = new Etudiant(4, "titi", "titi", null);
		etuService.updateEtudiant(etu, this.f);
		Etudiant etuOut = etuService.getEtudiantById(etu);
		assertEquals(etu.getNom(), etuOut.getNom());
	}
	
	
	
	
	
	
	
	
}

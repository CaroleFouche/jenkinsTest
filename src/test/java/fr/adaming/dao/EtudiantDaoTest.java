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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class EtudiantDaoTest {

	@Autowired
	IEtudiantDao etuDao;
	
	Formateur f;
	
	@Before
	public void setUp() {
		this.f = new Formateur();
		this.f.setId(1);
	}
	
	@Test
	@Transactional(readOnly=true)
	// @Ignore pour ignorer ce test
	//@Rollback(value=true) pour ne pas modifier la BD =revenir en arriere
	public void testGetEtudiantById() {
		Etudiant eIn = new Etudiant();
		eIn.setId(7);
		Etudiant eOut = etuDao.getEtudiantById(eIn); 
		String nomRecup = eOut.getNom();
		
		String nomAttendu = "toto";
		
		assertEquals(nomRecup, nomAttendu);
	}
	
	@Test
	@Rollback(value=true)
	@Transactional
	public void testDeleteEtudiant() {
		
		List<Etudiant> listeAv = etuDao.getAllEtudiants(this.f);
		int rAttendu = listeAv.size();
		Etudiant eIn = new Etudiant();
		eIn.setId(4);
		eIn.setFormateur(f);
		
		eIn = etuDao.getEtudiantById(eIn);
		
		etuDao.deleteEtudiant(eIn);
		
		List<Etudiant> liste = etuDao.getAllEtudiants(this.f);
		
		int rObtenu = liste.size();
		
		assertNotEquals(rAttendu, rObtenu);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void testGetAllEtudiantsSize() {
		int rAttendu=3;
		List<Etudiant> listeE = etuDao.getAllEtudiants(f);
		int rObtenu = listeE.size();
		assertEquals(rAttendu, rObtenu);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void testGetAllEtudiantsNom() {
		String rAttendu = "Toto";
		List<Etudiant> listeE = etuDao.getAllEtudiants(f);
		String rObtenu = listeE.get(0).getNom();
		assertEquals(rAttendu, rObtenu);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void testGetEtudiantById1() {
		Etudiant eIn = new Etudiant();
		eIn.setId(7);
		Etudiant e1 = etuDao.getEtudiantById(eIn);
		assertNotNull(e1);
	}
	
	@Test
	@Transactional
	public void testAddEtudiant() {
		
		List<Etudiant> listeAvant = etuDao.getAllEtudiants(f);
		int avant = listeAvant.size();
		
		Etudiant etu = new Etudiant();
		etu.setFormateur(f);
		etuDao.addEtudiant(etu);
		
		List<Etudiant> listeApres = etuDao.getAllEtudiants(f);
		int apres = listeApres.size();
		
		assertEquals(avant +1, apres);
	}
	
	@Test
	@Transactional
	public void testAddEtudiantNom() {
		
		Etudiant expected = new Etudiant("toto", "titi", null);
		expected = etuDao.addEtudiant(expected);
		
		Etudiant result = etuDao.getEtudiantById(expected);
		
		assertEquals(expected.getNom(), result.getNom());
	}
	
	@Test
	@Transactional
	public void testUpdateEtudiant() {
		Etudiant etu = new Etudiant(4, "titi", "titi", null);
		etuDao.updateEtudiant(etu);
		Etudiant etuOut = etuDao.getEtudiantById(etu);
		assertEquals(etu.getNom(), etuOut.getNom());
	}
	


}

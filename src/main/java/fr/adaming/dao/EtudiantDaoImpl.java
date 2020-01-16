package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Repository
public class EtudiantDaoImpl implements IEtudiantDao{

	private SessionFactory sf;
	@Autowired //Peut etre sur l'attribut, sur le setter ou le constructeur
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}




	@Override
	public List<Etudiant> getAllEtudiants(Formateur fIn) {
		//Recupere la session
		Session s = sf.getCurrentSession();
		
		//Ecrire la requete hql
		String req = " FROM Etudiant e WHERE e.formateur.id=:pIdF";
		
		//Creer un query
		Query query =  s.createQuery(req);
		//Passage des valeurs aux parametres
		query.setParameter("pIdF", fIn.getId());
		
		List<Etudiant> listeEtu = query.list();
		
		return listeEtu;
	}




	@Override
	public Etudiant getEtudiantById(Etudiant eIn) {
		//Recuperer la session
		Session s = sf.getCurrentSession();
		
		//Recuperer l'etudiant avec la méthode de Hibernate
		Etudiant eOut = (Etudiant) s.get(Etudiant.class, eIn.getId());
		
		return eOut;
	}




	@Override
	public Etudiant addEtudiant(Etudiant eIn) {
		//Recuperer la session
		Session s = sf.getCurrentSession();
		
		//Ajouter l'etudiant avec la méthode save
		s.save(eIn);
		
		return eIn;
	}




	@Override
	public void deleteEtudiant(Etudiant eIn) {
		//recupere la session
		Session s = sf.getCurrentSession();
		
		//Supp l'etudiant
		s.delete(eIn);
		
	}




	@Override
	public void updateEtudiant(Etudiant eIn) {
		///recupere la session
		Session s = sf.getCurrentSession();
		
		//Supp l'etudiant
		s.saveOrUpdate(eIn);
		
	}

}

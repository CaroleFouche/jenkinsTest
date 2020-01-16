package fr.adaming.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IEtudiantDao;
import fr.adaming.entities.Etudiant;
import fr.adaming.entities.Formateur;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

	// Transformation de l'association UML en Java
	private IEtudiantDao etuDao;

	@Autowired
	public void setEtuDao(IEtudiantDao etuDao) {
		this.etuDao = etuDao;
	}

	@Override
	public List<Etudiant> getAllEtudiants(Formateur fIn) {
		//Appel de la méthode de Dao
		return etuDao.getAllEtudiants(fIn);
	}

	@Override
	public Etudiant getEtudiantById(Etudiant eIn) {
		//Appel de la méthode de Dao
		return etuDao.getEtudiantById(eIn);
	}

	@Override
	public Etudiant addEtudiant(Etudiant eIn, Formateur fIn) {
		eIn.setFormateur(fIn);
		return etuDao.addEtudiant(eIn);
	}

	@Override
	public boolean deleteEtudiant(Etudiant eIn, Formateur fIn) {
		//Recuperer l'etudiant 
		Etudiant eOut = etuDao.getEtudiantById(eIn);
		
		if (eOut!=null && fIn.getId()==eOut.getFormateur().getId()) {
			etuDao.deleteEtudiant(eOut);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEtudiant(Etudiant eIn, Formateur fIn) { //eIn contient les nouvelles valeurs
		//recuperer l'etudiant 
		Etudiant eOut = etuDao.getEtudiantById(eIn); //eOut contient les anciennes valeurs 
		
		if (eOut!=null && fIn.getId()==eOut.getFormateur().getId()) {
			//Modifier l'objet recuperé eOut avec les nouvelles valeurs stockée dans l'etudiant eIn
			eOut.setNom(eIn.getNom());
			eOut.setPrenom(eIn.getPrenom());
			eOut.setdN(eIn.getdN());
			//Appel de la méthode de Dao pour supp l'étu
			etuDao.updateEtudiant(eOut);
			return true;
		}
		return false;
	}

}

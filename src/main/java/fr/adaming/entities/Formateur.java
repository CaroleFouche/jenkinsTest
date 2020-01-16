package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="formateurs")
public class Formateur implements Serializable {

	// Declaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_f")
	private int id;
	private String mail;
	private String mdp;
	
	//Transformation de l'assoc UML en java
	@OneToMany(mappedBy="formateur")
	private List<Etudiant> listeEtudiants;
	
	
	//Constructeurs
	public Formateur() {
		super();
	}

	public Formateur(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}

	public Formateur(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}

	//Getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

	@Override
	public String toString() {
		return "Formateur [id=" + id + ", mail=" + mail + ", mdp=" + mdp + ", listeEtudiants=" + listeEtudiants + "]";
	}
	
	

}

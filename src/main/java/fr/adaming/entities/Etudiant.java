package fr.adaming.entities;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="etudiants")
public class Etudiant implements Serializable {

	// Declaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_e")
	private int id;
	private String nom;
	private String prenom;
	
	
	@Temporal(TemporalType.DATE) //Pour transformer la date de java en date de sql
	private Date dN;
	
	//Transfo de l'assoc uml en java
	@ManyToOne
	@JoinColumn(name="f_id", referencedColumnName="id_f")
	private Formateur formateur;

	// Constructeur
	public Etudiant() {
		super();
	}

	public Etudiant(String nom, String prenom, Date dN) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dN = dN;
	}

	public Etudiant(int id, String nom, String prenom, Date dN) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dN = dN;
	}

	// Getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getdN() {
		return dN;
	}

	public void setdN(Date dN) {
		this.dN = dN;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dN=" + dN + "]";
	}


}

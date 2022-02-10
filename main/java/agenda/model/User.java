package agenda.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private int idUser;
	
	private String nom;
	
	private String prenom;
	
	private String nomUtilisateur;
	
	private String motDePasse;
	
	public User() {
		super();
	}
	
	public User(String nom, String prenom, String nomUtilisateur, String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}
	
	public User(User u) {
		super();
		this.nom = u.getNom();
		this.prenom = u.getPrenom();
		this.nomUtilisateur = u.getNomUtilisateur();
		this.motDePasse = u.getMotDePasse();
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int id) {
		this.idUser = id;
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

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}

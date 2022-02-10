package agenda.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	
	private int idCategory;
	
	private String nom;
	
	public Category() {
		super();
	}
	
	public Category(String nom) {
		super();
		this.nom = nom;
	}

	public Category(Category c) {
		super();
		this.nom = c.getNom();
	}
	
	public int getIdCategory() {
		return idCategory;
	}
	
	public void setIdCategory(int id) {
		this.idCategory = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

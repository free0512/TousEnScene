package src.ficheInscription;

import org.apache.struts.action.ActionForm;

public class FicheReglementsForm extends ActionForm {

	private String nom, prenom ;
	private int id_reglements ; 
	private String modeReglement ;
	private String description ;
	
	
	
	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public int getId_reglements() {
		return id_reglements;
	}
	
	public void setId_reglements(int id_reglements) {
		this.id_reglements = id_reglements;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	
}

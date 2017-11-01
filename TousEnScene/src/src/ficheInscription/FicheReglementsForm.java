package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FicheReglementsForm extends ActionForm {

	private String nom, prenom ;
	private int numeroAdherent ;
	private int id_reglements ; 
	private String modeReglement ;
	private String description, ageEleve ;
	
	public void reset (ActionMapping mapping, HttpServletRequest req) {
		this.nom = null ;
		this.prenom= null ;
		this.numeroAdherent = 0 ;
		this.id_reglements= 0 ;
		this.modeReglement= null ;
		this.description= null ;
	}
	
	public int getNumeroAdherent() {
		return numeroAdherent;
	}

	public void setNumeroAdherent(int numeroAdherent) {
		this.numeroAdherent = numeroAdherent;
	}

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

	public String getAgeEleve() {
		return ageEleve;
	}

	public void setAgeEleve(String ageEleve) {
		this.ageEleve = ageEleve;
	}
	
	
}

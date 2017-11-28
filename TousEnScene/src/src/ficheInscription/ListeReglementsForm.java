package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListeReglementsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom, prenom ;
	private int numeroAdherent ;
	private int id_reglements ; 
	private String modeReglement ;
	private String description, ageEleve, atelierEleve, atelierEleveDesc, reglementEleve, reglementEleveDesc ;
	 
	
	public void reset (ActionMapping mapping, HttpServletRequest req) {
		this.nom = null ;
		this.prenom= null ;
		this.numeroAdherent = 0 ;
		this.id_reglements= 0 ;
		this.modeReglement= null ;
		this.ageEleve= null;
		this.atelierEleveDesc=null;
		this.reglementEleve="0";
		this.reglementEleveDesc=null;
		this.atelierEleve="0";
		this.description= null ;
	}
		
	public String getReglementEleve() {
		return reglementEleve;
	}


	public void setReglementEleve(String reglementEleve) {
		this.reglementEleve = reglementEleve;
	}


	public String getReglementEleveDesc() {
		switch (this.reglementEleve) {
		case "0": reglementEleveDesc = "" ;
		break;
		case "a": reglementEleveDesc = "1x = 220";
		break ;
		case "b": reglementEleveDesc =  "2x = 2x110" ;
		break ;
		case "c": reglementEleveDesc = "3x = 2x75 + 1x70";
		break ;
		case "d": reglementEleveDesc =  "6x = 5x36 + 1x40";
		break ;
		case "e": reglementEleveDesc =  "8x = 7x28 + 1x24" ;
		break ;
		case "f": reglementEleveDesc = "1x = 350";
		break;
		case "g": reglementEleveDesc = "2x = 2x175" ;
		break ;
		case "h": reglementEleveDesc = "3x = 2x115 + 1x120";
		break ;
		case "i": reglementEleveDesc = "6x = 5x60 + 1x50";
		break ;
		case "j": reglementEleveDesc = "8x = 7x45 + 1x35";
		break ;
		case "k": reglementEleveDesc = "1x = 250";
		break ;
		case "l": reglementEleveDesc =  "2x = 2x125";
		break ;
		case "m": reglementEleveDesc = "3x = 2x85 + 1x80" ;
		break;
		case "n": reglementEleveDesc = "6x = 5x40 + 1x50";
		break ;
		case "o": reglementEleveDesc = "8x = 7x30 + 1x40";
		break ;
		default: reglementEleveDesc = "mode réglement  Inconnu" ;
		break;
	}
		return reglementEleveDesc;
	}


	public String getAtelierEleve() {
		return atelierEleve;
	}

	public void setAtelierEleve(String atelierEleve) {
		this.atelierEleve = atelierEleve;
	}
	
	public void setAtelierEleveDesc(String atelierEleveDesc) {
		this.atelierEleveDesc = atelierEleveDesc;
	}

	public String getAtelierEleveDesc() {
		
		switch (this.atelierEleve) {
		case "0": atelierEleveDesc = "" ;
		break;
		case "1": atelierEleveDesc = "Lundi 20h00 - 23h00 (Adultes)" ;
			break;
		case "6": atelierEleveDesc = "Mercredi 20h00 - 22h00 (Adultes)" ;
			break;
		case "2": atelierEleveDesc = "Mardi 17h30 - 19h00 (Enfants)" ;
			break;
		case "4": atelierEleveDesc = "Mercredi 15h00 - 16h30 (Enfants)" ;
			break;
		case "3": atelierEleveDesc = "Mardi 19h00 - 20h30 (Ados)" ;
			break;
		case "5": atelierEleveDesc = "Mercredi 16h45 - 18h15 (Pré-ados)" ;
			break;
		default: atelierEleveDesc = "Atelier Inconnu" ;
			break;
		}
		
		return atelierEleveDesc;
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

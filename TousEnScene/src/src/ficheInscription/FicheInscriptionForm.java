package src.ficheInscription;

import java.sql.Date;
import java.time.LocalDate;
 
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.joda.time.DateTime;
import org.joda.time.Period;

public class FicheInscriptionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String 	nomEleve, prenomEleve, dateDeNaissanceEleve , adresse1, adresse2 , classeScolaire,
					ville, telephoneEleve1, telephoneEleve2, emailEleve, sexeEleve, majoriteEleve,
					statutEleve , atelierEleve, reglementEleve, nomMere, nomPere, prenomMere, 
					prenomPere, telephoneMere, telephonePere ,emailPere, emailMere ,
					autoriser, siNonQui, photographier;
	private HashMap sexeRadio, majoriteRadio, atelierRadio, reglementRadio, ouiNonRadio , classRadio;
	private int  codePostal ;
	private GregorianCalendar dateDeNaissance ;
	private String ageEleve , modeAcces ;
	int statusInsert, numeroInterne ; 
	

	public void reset2 (ActionMapping mapping, HttpServletRequest req) {
		this.statusInsert = 9 ; //StatusInsert initialisé à 9. car 0-->Création non aboutie, 1-->Création aboutie
		this.numeroInterne= 0 ;
		this.nomEleve = null;
		this.prenomEleve=null;
		this.classeScolaire=null;
		this.dateDeNaissanceEleve=null;
		this.adresse1=null;
		this.adresse2=null;
		this.codePostal=0;
		this.ville=null;
		this.telephoneEleve1=null;
		this.telephoneEleve2=null;
		this.emailEleve=null;
		this.sexeEleve = "F";  
		this.majoriteEleve = "N";
		this.nomPere=null;
		this.prenomPere=null;
		this.nomMere=null;
		this.prenomMere=null;
		this.telephonePere=null;
		this.telephoneMere=null;
		this.emailPere=null;
		this.emailMere=null;
		this.atelierEleve = "1" ;
		this.reglementEleve="a";
		this.autoriser=null;
		this.siNonQui=null;
		this.photographier=null;
	}
	
	@SuppressWarnings("deprecation")
	public GregorianCalendar getDateDeNaissance() {
		int jour, mois, annee ;
		String[] dateTab = dateDeNaissanceEleve.split("/") ;
		try {
			jour = Integer.parseInt(dateTab[0]) ;
			mois = Integer.parseInt(dateTab[1]) ;
			annee= Integer.parseInt(dateTab[2]) ;
			dateDeNaissance = new GregorianCalendar(annee, mois, jour) ;
					
			return dateDeNaissance ;
		} catch (Exception e) {}
		
		return null ;
	}

	public HashMap getOuiNonRadio() {
		HashMap h = new HashMap () ;
		h.put("O", "Oui") ;
		h.put("N", "Non");
		return h;
	}

	public String getAutoriser() {
		return autoriser;
	}

	public void setAutoriser(String autoriser) {
		this.autoriser = autoriser;
	}

	public String getSiNonQui() {
		return siNonQui;
	}

	public void setSiNonQui(String siNonQui) {
		this.siNonQui = siNonQui;
	}

	public String getReglementEleve() {
		return reglementEleve;
	}

	public void setReglementEleve(String reglementEleve) {
		this.reglementEleve = reglementEleve;
	}

	public HashMap getReglementRadio() {
		HashMap h = new HashMap () ;
		h.put("a", "1x = 220");
		h.put("b", "2x = 2x110");
		h.put("c", "3x = 2x75 + 1x70");
		h.put("d", "6x = 5x36 + 1x40");
		h.put("e", "8x = 7x28 + 1x24") ;
		h.put("f" , "1x = 350");
		h.put("g" , "2x = 2x175");
		h.put("h" , "3x = 2x115 + 1x120");
		h.put("i" , "6x = 5x60 + 1x50");
		h.put("j" ,"8x = 7x45 + 1x35");
		h.put("k" , "1x = 250");
		h.put("l" , "2x = 2x125");
		h.put("m" , "3x = 2x85 + 1x80");
		h.put("n" , "6x = 5x40 + 1x50");
		h.put("o" ,"8x = 7x30 + 1x40");
		return h ;
	}

	public HashMap getAtelierRadio() {
		HashMap h = new HashMap () ;
		h.put("1", "Lundi 20h00 - 23h00 (Adultes)" ) ;
		h.put("6", "Mercredi 20h00 - 22h00 (Adultes)");
		h.put("2", "Mardi 17h30 - 19h00 (Enfants)");
		h.put("4", "Mercredi 15h00 - 16h30 (Enfants)");
		h.put("3", "Mardi 19h00 - 20h30 (Ados)");
		h.put("5", "Mercredi 16h45 - 18h15 (Pré-ados)");
		return h;
	}

	public HashMap<String, String> getSexeRadio() {
		HashMap<String, String> h = new HashMap<String, String>() ;
		h.put("F", "Femme") ;
		h.put("H", "Homme") ;
		return h;
	}
	
	public HashMap<String, String> getMajoriteRadio() {
		HashMap<String, String> h = new HashMap<String, String>() ;
		h.put("M", "Majeur(e)");
		h.put("N", "Mineur(e)");
		return h;
	}

	public HashMap getClassRadio() {
		HashMap<String, String> h = new HashMap<String, String>() ;
		h.put(" ", "Choisir dans la liste");
		h.put("1" , "1er");
		h.put("2", "2eme");
		h.put("3", "3eme");
		h.put("4", "4eme");
		h.put("5", "5eme");
		h.put("6", "6eme");
		h.put("7", "Terminal");
		h.put("8", "Etude Supérieurs") ;
		return h;
	}

	public String getNomEleve() {
		return nomEleve;
	}
	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}
	public String getPrenomEleve() {
		return prenomEleve;
	}
	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}
	public String getDateDeNaissanceEleve() {
		return dateDeNaissanceEleve;
	}
	public void setDateDeNaissanceEleve(String dateDeNaissanceEleve) {
		this.dateDeNaissanceEleve = dateDeNaissanceEleve;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephoneEleve1() {
		return telephoneEleve1;
	}
	public void setTelephoneEleve1(String telephoneEleve1) {
		this.telephoneEleve1 = telephoneEleve1;
	}
	public String getTelephoneEleve2() {
		return telephoneEleve2;
	}
	public void setTelephoneEleve2(String telephoneEleve2) {
		this.telephoneEleve2 = telephoneEleve2;
	}
	public String getEmailEleve() {
		return emailEleve;
	}
	public void setEmailEleve(String emailEleve) {
		this.emailEleve = emailEleve;
	}
	public String getSexeEleve() {
		return this.sexeEleve;
	}
	public void setSexeEleve(String sexeEleve) {
		this.sexeEleve = sexeEleve;
	}
	public String getStatutEleve() {
		return statutEleve;
	}
	public void setStatutEleve(String statutEleve) {
		this.statutEleve = statutEleve;
	}
	public String getNomMere() {
		return nomMere;
	}
	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}
	public String getNomPere() {
		return nomPere;
	}
	public void setNomPere(String nomPere) {
		this.nomPere = nomPere;
	}
	public String getPrenomMere() {
		return prenomMere;
	}
	public void setPrenomMere(String prenomMere) {
		this.prenomMere = prenomMere;
	}
	public String getPrenomPere() {
		return prenomPere;
	}
	public void setPrenomPere(String prenomPere) {
		this.prenomPere = prenomPere;
	}
	public String getTelephoneMere() {
		return telephoneMere;
	}
	public void setTelephoneMere(String telephoneMere) {
		this.telephoneMere = telephoneMere;
	}
	public String getTelephonePere() {
		return telephonePere;
	}
	public void setTelephonePere(String telephonePere) {
		this.telephonePere = telephonePere;
	}
	public String getEmailPere() {
		return emailPere;
	}
	public void setEmailPere(String emailPere) {
		this.emailPere = emailPere;
	}
	public String getEmailMere() {
		return emailMere;
	}
	public void setEmailMere(String emailMere) {
		this.emailMere = emailMere;
	}
	public String getClasseScolaire() {
		return classeScolaire;
	}
	public void setClasseScolaire(String classeScolaire) {
		this.classeScolaire = classeScolaire;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getAtelierEleve() {
		return atelierEleve;
	}

	public void setAtelierEleve(String atelierEleve) {
		this.atelierEleve = atelierEleve;
	}

	public String getPhotographier() {
		return photographier;
	}

	public void setPhotographier(String photographier) {
		this.photographier = photographier;
	}

	public String getMajoriteEleve() {
		return majoriteEleve;
	}

	public void setMajoriteEleve(String majoriteEleve) {
		this.majoriteEleve = majoriteEleve;
	}

	public String getAgeEleve() {
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(new java.util.Date());
		DateTime dateNow = new DateTime(calNow);
		try {
		String[] maDate = this.dateDeNaissanceEleve.split("/");
		String dateleveStr = maDate[2] + "-" + maDate[1] + "-" + maDate[0] ;
		DateTime dateleve = new DateTime(dateleveStr) ;
		Period p = new Period(dateleve , dateNow ) ;
		return String.valueOf(p.getYears()) ;
		} catch(Exception e)
		{
			return null ;
		}
	}

	public void setAgeEleve(String ageEleve) {
		this.ageEleve = ageEleve;
	}

	public int getStatusInsert() {
		return statusInsert;
	}

	public void setStatusInsert(int statusInsert) {
		this.statusInsert = statusInsert;
	}

	public int getNumeroInterne() {	
		return numeroInterne;
	}

	public void setNumeroInterne(int numeroInterne) {
		this.numeroInterne = numeroInterne;
	}

	public String getModeAcces() {
		return modeAcces;
	}

	public void setModeAcces(String modeAcces) {
		this.modeAcces = modeAcces;
	}
	
	
}

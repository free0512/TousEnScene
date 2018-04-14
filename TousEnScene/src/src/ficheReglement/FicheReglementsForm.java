package src.ficheReglement;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.joda.time.DateTime;

public class FicheReglementsForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String 	modeReglement, libelleReglement, description, dateReglement ;
	private double montantReglement ;
	private DateTime dateReglementGC ;
	private int numeroId ;
	HashMap<String, String> modeRegList = new HashMap<String, String>() ;
	
	public void reset (ActionMapping mapping, HttpServletRequest req) {
		this.modeReglement = null ; 
		this.dateReglement=null;
		this.description = null ;
		this.montantReglement = 0 ;
		this.modeRegList = null ;
		this.libelleReglement= null;
	}
		
	public HashMap getModeRegList() {
		HashMap<String, String> h = new HashMap<String, String>() ;
		h.put("CH", "Chèque") ;
		h.put("ES", "Espèce") ;
		h.put("CA", "Carte Bancaire") ;
		h.put("C", "Chèque") ;
		h.put("E", "Espèce") ;
		return h;
	}

	public void setModeRegList(HashMap modeRegList) {
		this.modeRegList = modeRegList;
	}

	public DateTime getDateReglementGC() {
		//int jour, mois, annee ;
		String[] maDate = dateReglement.split("/") ;
		try {
			String datregStr = maDate[2] + "-" + maDate[1] + "-" + maDate[0] ;
			DateTime datereg = new DateTime(datregStr) ;
			return datereg ;
		} catch (Exception e) { }
		
		return null ;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}
	
	public String getLibelleReglement() {
		String libelle =  (String) getModeRegList().get(this.modeReglement) ;
		return libelle.trim() ;
	}

	public void setLibelleReglement(String libelleReglement) {
		this.libelleReglement = libelleReglement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}

	public double getMontantReglement() {
		return montantReglement;
	}

	public void setMontantReglement(double montantReglement) {
		this.montantReglement = montantReglement;
	}

	public int getNumeroId() {
		return numeroId;
	}

	public void setNumeroId(int numeroId) {
		this.numeroId = numeroId;
	}	
}

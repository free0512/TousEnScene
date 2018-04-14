package src.recapAdherents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import src.ficheInscription.FicheInscriptionForm;
import src.ficheReglement.FicheReglementsForm;
import src.utils.Page;
import src.utils.Pageable;

public class RecapAdhParCreneauForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creneauSel ;
	//private HashMap<String, String> recapAdhList = new HashMap<String, String> ();
	private List<FicheCreneauForm> recapAdhList = null ;
	
	private List<FicheInscriptionForm> listeAdherents = null ;
	private Pageable <FicheInscriptionForm> pagination = new Pageable<FicheInscriptionForm> (null) ;
	
	
	public String getCreneauSel() {
		return creneauSel;
	}

	public void setCreneauSel(String creneauSel) {
		this.creneauSel = creneauSel;
	}

	public List<FicheCreneauForm> getRecapAdhList() {
		return recapAdhList;
	}

	public void setRecapAdhList(List<FicheCreneauForm> recapAdhList) {
		this.recapAdhList = recapAdhList;
	}	

	// vérification si la liste posséde une page suivante ou précédente
		public boolean isAfficherPrecedent () {
			return this.pagination.hasPreviousPage();
		}
		public boolean isAfficherSuivant() {
			return this.pagination.hasNextPage();
		}
		
		public int getChoixPage() {
			return this.pagination.getPage() ;
		}
		public void setChoixPage(int choixPage) {
			this.pagination.setPage(choixPage);
		}
		
		public void setChoixTaillePage(int choixTaillePage) {
			this.pagination.setPageSize(choixTaillePage);
		}
		public int getChoixTaillePage () {
			return this.pagination.getPageSize();
		}
		
		public List<Page> getListePages() {
			return this.pagination.getListePages();
		}
		
		public int getPageCourante() {
			return this.pagination.getPage() ;
		}
		public void setPageCourante(int pageCourante) {
			this.pagination.setPage(pageCourante);
		}

		public List<FicheInscriptionForm> getListeAdherents() {
			return (this.pagination.getListForPage().size()>0 &&
					this.pagination.getPageSize()>0)? this.pagination.getListForPage():null ;
		}

		public void setListeAdherents(List<FicheInscriptionForm> listeAdherents) {
			this.listeAdherents = listeAdherents;
			if (null==this.pagination) {
				this.pagination= new Pageable<FicheInscriptionForm> (this.listeAdherents) ;
			} else {
				this.pagination.setList(this.listeAdherents);
			}
			this.pagination.setPage(1);
		}

		public void setPageSize(int pageSize){
			this.pagination.setPageSize(pageSize);
		}
}

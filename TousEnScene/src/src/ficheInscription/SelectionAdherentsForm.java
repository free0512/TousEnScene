package src.ficheInscription;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import src.utils.Page;
import src.utils.Pageable;

public class SelectionAdherentsForm  extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String choixNomEleve, choixPrenomEleve , ageEleve, atelierEleve, reglementEleve;
	private String nomEleveChoisi, prenomEleveChoisi ;
	private int rang ;
	private String selRadio ; 

	private List<FicheInscriptionForm> selectionAdherents = null ;
	private Pageable <FicheInscriptionForm> pagination = new Pageable<FicheInscriptionForm> (null) ;
	
	public void reset (ActionMapping mapping, HttpServletRequest req) {
		this.choixNomEleve=null;
		this.choixPrenomEleve=null;
		//this.selectionAdherents=null;
		//this.pagination=null;
	}
	
	public String getReglementEleve() {
		return reglementEleve;
	}

	public void setReglementEleve(String reglementEleve) {
		this.reglementEleve = reglementEleve;
	}

	public String getAtelierEleve() {
		return atelierEleve;
	}

	public void setAtelierEleve(String atelierEleve) {
		this.atelierEleve = atelierEleve;
	}

	public String getNomEleveChoisi() {
		return nomEleveChoisi;
	}

	public void setNomEleveChoisi(String nomEleveChoisi) {
		this.nomEleveChoisi = nomEleveChoisi;
	}

	public String getPrenomEleveChoisi() {
		return prenomEleveChoisi;
	}

	public void setPrenomEleveChoisi(String prenomEleveChoisi) {
		this.prenomEleveChoisi = prenomEleveChoisi;
	}

	public String getAgeEleve() {
		return ageEleve;
	}

	public void setAgeEleve(String ageEleve) {
		this.ageEleve = ageEleve;
	}

	public String getSelRadio() {
		return selRadio;
	}

	public void setSelRadio(String selRadio) {
		this.selRadio = selRadio;
	}

	public   int getRang() {
		return rang;
	}

	public   void setRang(int rang) {
		this.rang = rang;
	}

	public String getChoixNomEleve() {
		return choixNomEleve;
	}

	public void setChoixNomEleve(String choixNomEleve) {
		this.choixNomEleve = choixNomEleve;
	}

	public String getChoixPrenomEleve() {
		return choixPrenomEleve;
	}

	public void setChoixPrenomEleve(String choixPrenomEleve) {
		this.choixPrenomEleve = choixPrenomEleve;
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

	public List<FicheInscriptionForm> getselectionAdherents() {
		return (this.pagination.getListForPage().size()>0 &&
				this.pagination.getPageSize()>0)? this.pagination.getListForPage():null ;
	}

	public void setselectionAdherents(List<FicheInscriptionForm> selectionAdherents) {
		this.selectionAdherents = selectionAdherents;
		if (null==this.pagination) {
			this.pagination= new Pageable<FicheInscriptionForm> (this.selectionAdherents) ;
		} else {
			this.pagination.setList(this.selectionAdherents);
		}
		this.pagination.setPage(1);
	}

	public void setPageSize(int pageSize){
		this.pagination.setPageSize(pageSize);
	}
}

package src.ficheInscription;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import src.utils.Page;
import src.utils.Pageable;

public class ListeAdherentsForm  extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String choixNomEleve, choixPrenomEleve ;
	private int rang ;
	private String selRadio ;

	private List<FicheInscriptionForm> listeAdherents = null ;
	private Pageable <FicheInscriptionForm> pagination = new Pageable<FicheInscriptionForm> (null) ;
	
	public void reset (ActionMapping mapping, HttpServletRequest req) {
		this.choixNomEleve=null;
		this.choixPrenomEleve=null;
		//this.listeAdherents=null;
		//this.pagination=null;
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

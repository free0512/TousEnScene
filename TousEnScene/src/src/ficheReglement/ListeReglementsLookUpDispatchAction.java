package src.ficheReglement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import src.ficheReglementDB.ListeReglementsDB;

public class ListeReglementsLookUpDispatchAction extends LookupDispatchAction{

	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap () ;
		map.put("selAdhrent", "selectionAdht");
		map.put("initialiser", "initialiserPage");
		map.put("suivant", "pageSuivante");
		map.put("precedent", "pagePrecedante");
		map.put("zoneList", "defilement");
		map.put("choixDunReglement", "choixDunReglement");
		return map;
	}

	public ActionForward choixDunReglement (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		HttpSession session = request.getSession(false);
		FicheReglementsForm ficheReglementsForm = (FicheReglementsForm) session.getAttribute("ficheReglementsForm");
		
		ListeReglementsForm listeReglementsForm = (ListeReglementsForm) form ;
		listeReglementsForm.setModeAcces("CHG");
		ficheReglementsForm.setDateReglement(listeReglementsForm.getDateReglementChoisi());
		ficheReglementsForm.setDescription(listeReglementsForm.getDescriptionChoisi());
		ficheReglementsForm.setMontantReglement(listeReglementsForm.getMontantReglementChoisi());
		ficheReglementsForm.setModeReglement(listeReglementsForm.getModeReglementChoisi());
		ficheReglementsForm.setNumeroId(listeReglementsForm.getNumeroIdChoisi());
		session.setAttribute("ficheReglementsForm", ficheReglementsForm);
	   
		return mapping.findForward("success");
	}
	
	public ActionForward pageSuivante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
		listeReglementForm.setChoixPage(listeReglementForm.getChoixPage()+1);
		return this.defilement(mapping, form, request, response);
	}
	
	public ActionForward pagePrecedante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
		listeReglementForm.setChoixPage(listeReglementForm.getChoixPage()-1);
		return this.defilement(mapping, form, request, response);
	}
	
	public ActionForward suite (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
		listeReglementForm.setChoixPage(listeReglementForm.getChoixPage());
		return this.defilement(mapping, form, request, response);
	}
	
	public ActionForward initialiserPage (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		HttpSession session = request.getSession(false);
		//SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
		session.setAttribute("savselectionAdherentsForm", null);
		ListeReglementsForm f  = (ListeReglementsForm) form ;
		f.reset(mapping, request);

		return mapping.findForward("success") ;
	}
	
	public ActionForward selectionAdht (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
		listeReglementForm.setDateReglementChoisi(null);
		listeReglementForm.setDescriptionChoisi(null);
		listeReglementForm.setModeReglementChoisi(null);
		listeReglementForm.setMontantReglementChoisi(0);
		listeReglementForm.setNumeroIdChoisi(0);
		listeReglementForm.setModeAcces("CRT");
		listeReglementForm.setListeReglements(null);	
		HttpSession session = request.getSession();
		FicheReglementsForm ficheReglementsForm = (FicheReglementsForm) session.getAttribute("ficheReglementsForm");
		ficheReglementsForm.setDateReglement(null);
		ficheReglementsForm.setDescription(null);
		ficheReglementsForm.setModeReglement(null);
		ficheReglementsForm.setMontantReglement(0);
		ficheReglementsForm.setNumeroId(0);
		session.setAttribute("ficheReglementsForm", ficheReglementsForm);
		
		return mapping.findForward("selectionAdherentFwd") ;
	}
	
	public ActionForward defilement (	ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ActionErrors err = new ActionErrors() ;
		Connection cnx = null ;
//		ConnextionBean cb = new ConnextionBean () ;
//		cb.getDataSystem();
//		try {
//			Class.forName(cb.getDriver()) ;
//		} catch (ClassNotFoundException e) {
//			err.add("erreur", new ActionMessage("errChargeDriver"));
//			addErrors(request, err);
//		}
		
		try {
			Context  initialContext = new InitialContext() ;	
			Context localContext = (Context) initialContext.lookup("java:comp/env/") ;
			DataSource ds = (DataSource) localContext.lookup("jdbc/JNDI") ;		
			cnx = ds.getConnection() ;
			
			HttpSession session = request.getSession(false) ;
			ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
			if (listeReglementForm.getListeReglements() ==null ||
					listeReglementForm.getListeReglements().size()==0) {
		//cnx = DriverManager.getConnection(cb.getUrl(), cb.getProfil(), cb.getMdp()) ;
				ListeReglementsDB.rechercherListeReglements(listeReglementForm, cnx);
			} else {
				listeReglementForm.setPageCourante(listeReglementForm.getChoixPage());
			}
			
			return mapping.findForward("success") ;
		} catch (Exception e) {
			err.add("erreur" , new ActionMessage("errchrgData"));
			addErrors(request, err);
			return mapping.findForward("failed") ;
		}
		finally {
			if ( cnx != null ) {
	            try {
	                cnx.close();
	            } catch ( SQLException ignore ) {}
			}
		}
	}
	
}

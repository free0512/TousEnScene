package src.ficheInscription;

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


import src.ficheInscription.DB.ListeInscriptionDB;

public class ListeInscriptionLookUpDispatchAction extends LookupDispatchAction{

	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap () ;
		map.put("valider", "selection");
		map.put("suivant", "pageSuivante");
		map.put("precedent", "pagePrecedante");
		map.put("zoneList", "defilement");
	//	map.put("initialiser" , "init");
		map.put("annuler", "annulerListe");
		map.put("modifier", "ouvrirFicheAdh");
		return map;
	}

	public ActionForward ouvrirFicheAdh (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ActionErrors err = new ActionErrors() ;
		ListeAdherentsForm laForm = (ListeAdherentsForm) form ;
		if (laForm.getRang() == 0) {
			err.add("erreur" , new ActionMessage("selUnAdherent"));
			addErrors(request, err);
			return mapping.findForward("failed") ;
		}
		FicheInscriptionForm fiForm = new FicheInscriptionForm() ;
		
		fiForm.setNumeroInterne(laForm.getRang());
		fiForm.setModeAcces("CHG");
		FicheInscriptionExecute2 fi  = new FicheInscriptionExecute2();
		ActionForward af = fi.execute(mapping, fiForm, request, response);
		
		return mapping.findForward("fiche") ;
	}
	
	public ActionForward annulerListe (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) {
		
		return mapping.findForward("annulerFiche") ;
	}
	
	public ActionForward selection (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ListeAdherentsForm laForm = (ListeAdherentsForm) form ;
		laForm.setListeAdherents(null);

		return this.defilement(mapping, form, request, response);
	}
	
	public ActionForward pageSuivante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		ListeAdherentsForm laForm = (ListeAdherentsForm) form ;
		//laForm.setListeAdherents(null);
		laForm.setChoixPage(laForm.getChoixPage()+1);
		return this.defilement(mapping, form, request, response);
	}
	
	public ActionForward pagePrecedante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		ListeAdherentsForm laForm = (ListeAdherentsForm) form ;
		//laForm.setListeAdherents(null);
		laForm.setChoixPage(laForm.getChoixPage()-1);
		return this.defilement(mapping, form, request, response);
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
			HttpSession session = request.getSession(false) ;
			
			Context  initialContext = new InitialContext() ;	
			Context localContext = (Context) initialContext.lookup("java:comp/env/") ;
			DataSource ds = (DataSource) localContext.lookup("jdbc/JNDI") ;		
			cnx = ds.getConnection() ;
			
			ListeAdherentsForm laForm = (ListeAdherentsForm) form ;
			if (laForm.getListeAdherents()==null ||
				laForm.getListeAdherents().size()==0) {
//				cnx = DriverManager.getConnection(cb.getUrl(), cb.getProfil(), cb.getMdp()) ;
				ListeInscriptionDB.rechercherListeAdherents(laForm, cnx);
			} else {
				laForm.setPageCourante(laForm.getChoixPage());
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

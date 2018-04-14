package src.ficheInscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import src.ficheInscription.DB.SelectionInscriptionDB;
import src.ficheReglementDB.ListeReglementsDB;
import src.system.ConnextionBean;

public class SelectionAdherentLookUpDispatchAction extends LookupDispatchAction{

	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap () ;
		map.put("suivant", "pageSuivante");
		map.put("precedent", "pagePrecedante");
		map.put("zoneList", "suite");
		map.put("valider", "selection");
		map.put("choixDunAdherent", "choixDunAdherent");
		
		return map;
	}
	
	public ActionForward choixDunAdherent (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		HttpSession session = request.getSession(false);
		SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
		session.setAttribute("savselectionAdherentsForm", laForm);

		return mapping.findForward("success");
	}

	public ActionForward selection (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
		laForm.setselectionAdherents(null);
		
		defilement(mapping, form, request, response);
		return mapping.getInputForward();
	}
	
	public ActionForward pageSuivante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
		laForm.setChoixPage(laForm.getChoixPage()+1);
		defilement(mapping, form, request, response);
		return mapping.getInputForward();
	}
	
	public ActionForward pagePrecedante (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
		laForm.setChoixPage(laForm.getChoixPage()-1);
		defilement(mapping, form, request, response);
		return mapping.getInputForward();
	}
	
	public ActionForward suite (ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		defilement(mapping, form, request, response);
		return mapping.getInputForward();
	}
	
	public ActionForward defilement (	ActionMapping mapping ,
			ActionForm form ,
			HttpServletRequest request ,
			HttpServletResponse response ) throws Exception {
		
		ActionErrors err = new ActionErrors() ;
		Connection cnx = null ;
		ConnextionBean cb = new ConnextionBean () ;
		cb.getDataSystem();
		try {
			Class.forName(cb.getDriver()) ;
		} catch (ClassNotFoundException e) {
			err.add("erreur", new ActionMessage("errChargeDriver"));
			addErrors(request, err);
		}
		
		try {
			HttpSession session = request.getSession(false) ;
			SelectionAdherentsForm laForm = (SelectionAdherentsForm) form ;
			if (laForm.getselectionAdherents() ==null ||
				laForm.getselectionAdherents().size()==0) {
				cnx = DriverManager.getConnection(cb.getUrl(), cb.getProfil(), cb.getMdp()) ;
				SelectionInscriptionDB.rechercherListeAdherents(laForm, cnx);
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

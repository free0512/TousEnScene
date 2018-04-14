package src.recapAdherents;

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

import src.ficheInscription.ListeAdherentsForm;
import src.ficheInscription.DB.ListeInscriptionDB;
import src.ficheReglement.ListeReglementsForm;
import src.ficheReglementDB.ListeReglementsDB;
import src.recapAdherentsDB.RecapAdhParCreneauxDB;

public class RecapAdhParCreneauLookUpDispatchAction extends LookupDispatchAction {

	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap () ;
		map.put("nbreAdherent", "nbreAdherentmethode");
		map.put("selectionCreneauMethd", "selAdhtDuCreneauMethod") ;
		return map;
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
			
			RecapAdhParCreneauForm recapAdhParCreneauForm = (RecapAdhParCreneauForm) form ;
			RecapAdhParCreneauxDB recapAdhParCreneauxDB = new RecapAdhParCreneauxDB() ;
			recapAdhParCreneauxDB.chargerRecapAdhParCreneau(recapAdhParCreneauForm, cnx);
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
	
	public ActionForward selAdhtDuCreneauMethod (	ActionMapping mapping ,
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

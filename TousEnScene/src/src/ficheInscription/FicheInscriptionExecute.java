package src.ficheInscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import src.system.ConnextionBean;

public class FicheInscriptionExecute extends Action {

	public ActionForward execute (ActionMapping mapping , ActionForm form , 
								  HttpServletRequest req, HttpServletResponse resp	) throws Exception {
		
		HttpSession session = req.getSession(false);
		FicheInscriptionForm fiForm = (FicheInscriptionForm) form ;
		fiForm.setModeAcces("CRT");
		fiForm.reset2(mapping, req);
		session.setAttribute("ficheInscription", fiForm);
		return mapping.findForward("success") ;
		
	}
	
	public ActionForward rechercherAdherent(ActionMapping mapping,
											HttpServletRequest req,
											FicheInscriptionForm fiForm) 
			throws Exception {
		
		HttpSession session = req.getSession(false);
		ActionErrors err = new ActionErrors() ;
		
		ConnextionBean cb = new ConnextionBean () ;
		cb.getDataSystem();
		Connection connexion = null;
		
		try {
			Class.forName(cb.getDriver()) ;
		} catch ( ClassNotFoundException e ) 
			{ err.add("erreur", 
						new ActionMessage("errChargeDriver"));
			addErrors(req, err);
			}
		try {
			connexion = DriverManager.getConnection(cb.getUrl(), 
													cb.getProfil(),
													cb.getMdp());
			
			FicheInscriptionBD finsDB = new FicheInscriptionBD() ;
			FicheInscriptionForm fi = finsDB.lireFicheAdherent(mapping, req, connexion, fiForm) ;
			session.setAttribute("ficheInscription", fi);
			if (fi==null) {
				err.add("erreur" , new ActionMessage("errchrgData"));
				addErrors(req, err);
			}
			return mapping.findForward("success") ;
		} catch (SQLException e)  
			{ 
			err.add("erreur", new ActionMessage("Err_connexion_bd")); 
			addErrors(req, err);
			return mapping.findForward("failed") ;
			}	
	 finally {
		connexion.close() ;		
	 		}
	}
}

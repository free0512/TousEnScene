package src.ficheReglement;

import java.sql.Connection;
import java.sql.Date;
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

import src.ficheReglementDB.FicheReglementBD;
import src.system.ConnextionBean;

public class FicheReglementLookUpDispatchAction extends LookupDispatchAction {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap mapFiche = new HashMap () ;
		mapFiche.put("valider", "validerFiche") ;
		return mapFiche ;
	}
	
	public ActionForward validerFiche (ActionMapping mapping ,
									   ActionForm  form ,
									   HttpServletRequest request ,
									   HttpServletResponse response ) 
											   throws Exception

	{
		ActionErrors actionErrors = new ActionErrors() ;
		
		// Validation des informations saisie par l'utilisateur 
		boolean validsaisie = validationSaisie(mapping, form, request) ;
		if (!validsaisie) {
			return mapping.findForward("failed") ;
		}
		ConnextionBean cb = new ConnextionBean () ;
		cb.getDataSystem();
		Connection connexion = null;
		
//		try {
//			Class.forName(cb.getDriver()) ;
//		} catch ( ClassNotFoundException e ) 
//			{ actionErrors.add("erreur", 
//						new ActionMessage("errChargeDriver"));
//			}
//		try {
//			connexion = DriverManager.getConnection(cb.getUrl(), 
//													cb.getProfil(),
//													cb.getMdp());

		try {
			Context initialContext = new InitialContext();
			Context localContext = (Context) initialContext.lookup("java:comp/env/");
			DataSource ds = (DataSource) localContext.lookup("jdbc/JNDI");
			connexion = ds.getConnection() ;
			
			FicheReglementBD ficheReglementBD = new FicheReglementBD() ;
			
			return ficheReglementBD.insertFicheReg(request ,mapping, connexion, form) ;
		 
		} catch (SQLException e)  
			{ 
			actionErrors.add("erreur", new ActionMessage("Err_connexion_bd")); 
			return mapping.findForward("failed") ;
			}	
	 finally { if ( connexion != null ) {
         try {
             connexion.close();
         } catch ( SQLException ignore ) {}
		}
		 
	 		}
	}
	
	public boolean validationSaisie (ActionMapping mapping, ActionForm form ,
			 HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) session.getAttribute("listeReglementForm");
		FicheReglementsForm ficheReglementsForm = (FicheReglementsForm) form ;
		ActionErrors err = new ActionErrors() ;
		@SuppressWarnings("unused")
		Date dateDeNaissanceSQL = null ;

		// Si le numeroInterne est renseigné, on bloque la création afin d'éviter de créer des doublons
		if ((listeReglementForm.getNumeroAdherent() == 0) ) {
			err.add("dejaCREE" , new ActionMessage("errors.idAdh.FichReg")) ;
			addErrors(req, err);
			return false ;
		}
		
		// Validation de la date du réglement 
		if (ficheReglementsForm.getDateReglementGC()==null ) {
			err.add("DateErreur", new ActionMessage("errors.date.Reglement"));
			addErrors(req, err);
			return false ;
		}
		
		// Montant réglement doit être renseigné 
		if (ficheReglementsForm.getMontantReglement()== 0 ) {
			err.add("DateErreur", new ActionMessage("errors.montant.Reglement"));
			addErrors(req, err);
			return false ;
		}
		return true;
	}

}
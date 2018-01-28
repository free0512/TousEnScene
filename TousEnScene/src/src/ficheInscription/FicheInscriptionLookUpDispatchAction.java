package src.ficheInscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.tomcat.jdbc.pool.DataSource;


public class FicheInscriptionLookUpDispatchAction extends LookupDispatchAction {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap mapFiche = new HashMap () ;
		mapFiche.put("valider", "validerFiche") ;
		mapFiche.put("imprimer", "imprimerFiche") ;
		mapFiche.put("age", "ageElv") ;
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
//		ConnextionBean cb = new ConnextionBean () ;
//		cb.getDataSystem();
//		Connection connexion = null;
//		
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
			
		Context initialContext = new InitialContext() ;	
		Context localContext = (Context) initialContext.lookup("java:comp/env/") ;
		DataSource ds = (DataSource) localContext.lookup("jdbc/JNDI") ;
		Connection connexion = ds.getConnection() ;
					
			FicheInscriptionForm fins = (FicheInscriptionForm) form ;
			FicheInscriptionBD finsDB = new FicheInscriptionBD() ;
			if (fins.getNumeroInterne()==0) {
				return finsDB.insertFicheIns(mapping, connexion, form) ;
						
			} else {
			return finsDB.changeFicheIns(mapping, connexion, form) ;
			}
			
//		} catch (SQLException e)  
//			{ 
//			actionErrors.add("erreur", new ActionMessage("Err_connexion_bd")); 
//			return mapping.findForward("failed") ;
//			}	
//	 finally {
//		connexion.close() ;		
//	 		}
	}	

	public ActionForward imprimerFiche (	ActionMapping mapping ,
										ActionForm form ,
										HttpServletRequest request ,
										HttpServletResponse response ) {
		
		return mapping.findForward("fiche") ;
	}
	
	public boolean validationSaisie (ActionMapping mapping, ActionForm form ,
								 HttpServletRequest req) {
		
		FicheInscriptionForm fins = (FicheInscriptionForm) form; 
		ActionErrors err = new ActionErrors() ;
		@SuppressWarnings("unused")
		Date dateDeNaissanceSQL = null ;
			
		// Si le numeroInterne est renseigné, on bloque la création afin d'éviter de créer des doublons
		if ((fins.getNumeroInterne() != 0) && (fins.getModeAcces()== "CRT")) {
			err.add("dejaCREE" , new ActionMessage("errors.dejaCree")) ;
			addErrors(req, err);
			return false ;
		}
				
		//Saisie obligatoire du Nom
		if (fins.getNomEleve().trim().isEmpty()) {
			err.add("nomKO", new ActionMessage("errors.nomko"));
			addErrors(req, err);
			return false;
		}
		
		//Saisie obligatoire du prénom
		if (fins.getPrenomEleve().trim().isEmpty()) {
			err.add("prenomKO", new ActionMessage("errors.prenomko"));
			addErrors(req, err);
			return false;
				}

		//Validation de l'age, par conséquant la date de naissance de l'élève
		String age  = (fins.getAgeEleve() != null) ? fins.getAgeEleve() : "" ;
		if (age.isEmpty()) {
			err.add("datNaissKO", new ActionMessage("errors.dateNaissko"));
			addErrors(req, err);
			return false;
				}
		
		// si l'adhérent n'est pas majeur
		if ( Integer.valueOf(age)< 18) {
		
		// Saisie obligatoire de l'autorisation
		if ((fins.getAutoriser()!=null ? fins.getAutoriser() : "").isEmpty()) {
			err.add("autoKO", new ActionMessage("errors.autoko"));
			addErrors(req, err) ;
			return false;
		}
		// Saisie obligatoire du nom de la personne qui va chercher l'enfant
				if (fins.getAutoriser().trim().equals("N")) {
					if ((fins.getSiNonQui()!=null ? fins.getSiNonQui() : "").trim().isEmpty()) {
						err.add("siOuiQuiKO", new ActionMessage("errors.siNonQuiko"));
						addErrors(req, err);
						return false;
					}
				}
		
		// Saisie obligatoire de l'autorisation de photographier
		if ((fins.getPhotographier() != null ? fins.getPhotographier() : "").isEmpty()) {
			err.add("photoKO", new ActionMessage("error.photoko"));
			addErrors(req , err) ;
			return false;
		}
		}
		return true;
	}
}
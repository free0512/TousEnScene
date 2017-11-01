package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FicheReglementsExecute extends Action{
	
	public ActionForward execute (ActionMapping mapping ,
								  ActionForm form,
								  HttpServletRequest req,
								  HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession(false);
		SelectionAdherentsForm laForm = (SelectionAdherentsForm) session.getAttribute("savselectionAdherentsForm");
		if (laForm == null) {
			laForm = new SelectionAdherentsForm() ;}
		else {
		FicheReglementsForm frForm = (FicheReglementsForm) form ;
		frForm.setNom(laForm.getNomEleveChoisi());
		frForm.setPrenom(laForm.getPrenomEleveChoisi());
		frForm.setNumeroAdherent(laForm.getRang());
		frForm.setAgeEleve(laForm.getAgeEleve());
		}
		return mapping.findForward("success") ;
	}	
}

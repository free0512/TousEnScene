package src.ficheReglement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import src.ficheInscription.SelectionAdherentsForm;

public class ListeReglementsExecute extends Action{
	
	public ActionForward execute (ActionMapping mapping ,
								  ActionForm form,
								  HttpServletRequest req,
								  HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession(false);
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) form ;
		SelectionAdherentsForm selectionAdherentForm = (SelectionAdherentsForm) session.getAttribute("savselectionAdherentsForm");
		if (selectionAdherentForm == null) {
			selectionAdherentForm = new SelectionAdherentsForm() ;}
		else {
		
			listeReglementForm.setNom(selectionAdherentForm.getNomEleveChoisi());
			listeReglementForm.setPrenom(selectionAdherentForm.getPrenomEleveChoisi());
			listeReglementForm.setNumeroAdherent(selectionAdherentForm.getRang());
			listeReglementForm.setAgeEleve(selectionAdherentForm.getAgeEleve());
			listeReglementForm.setAtelierEleve(selectionAdherentForm.getAtelierEleve());
			listeReglementForm.setReglementEleve(selectionAdherentForm.getReglementEleve());
		}
		session.setAttribute("listeReglementForm", listeReglementForm) ;
		ListeReglementsLookUpDispatchAction li = new ListeReglementsLookUpDispatchAction();
		return li.defilement(mapping, form, req, resp);
	}	
}

package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ListeInscriptionExecute extends Action {

	public ActionForward execute (ActionMapping mapping , ActionForm form , 
								  HttpServletRequest req, HttpServletResponse resp	) throws Exception {
		
		//return mapping.findForward("success") ;
		ListeAdherentsForm liForm = (ListeAdherentsForm) form ;
		liForm.setListeAdherents(null);
		liForm.setRang(0);
		ListeInscriptionLookUpDispatchAction li = new ListeInscriptionLookUpDispatchAction();
		return li.defilement(mapping, form, req, resp);
	}
}

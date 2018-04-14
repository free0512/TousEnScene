package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SelectionAdherentsExecute extends Action {

	public ActionForward execute (ActionMapping mapping , ActionForm form , 
								  HttpServletRequest req, HttpServletResponse resp	) 
								throws Exception {
		
		SelectionAdherentsForm liForm = (SelectionAdherentsForm)form ;
		liForm.setselectionAdherents(null);
		liForm.setRang(0);
		SelectionAdherentLookUpDispatchAction li = new SelectionAdherentLookUpDispatchAction();
		li.defilement(mapping, form, req, resp);
		return mapping.getInputForward();
	}
	
}

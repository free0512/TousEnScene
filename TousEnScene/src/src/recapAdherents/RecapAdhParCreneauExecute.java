package src.recapAdherents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import antlr.collections.impl.LList;
import src.ficheInscription.SelectionAdherentsForm;

public class RecapAdhParCreneauExecute extends Action{
	
	public ActionForward execute (ActionMapping mapping ,
								  ActionForm form,
								  HttpServletRequest req,
								  HttpServletResponse resp) throws Exception {
		
		RecapAdhParCreneauForm recapAdhParCreneauForm = (RecapAdhParCreneauForm) form ;
		recapAdhParCreneauForm.setRecapAdhList(null);
		RecapAdhParCreneauLookUpDispatchAction li = new RecapAdhParCreneauLookUpDispatchAction() ;
		return li.defilement(mapping, form, req, resp);
		
	}	
}

package src.ficheInscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FicheReglementsExecute extends Action{
	
	public ActionForward execute (ActionMapping mapping ,
								  ActionForm form,
								  HttpServletRequest req,
								  HttpServletResponse resp) throws Exception {
		return mapping.findForward("success") ;
	}	
}

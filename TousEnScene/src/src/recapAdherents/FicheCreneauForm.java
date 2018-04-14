package src.recapAdherents;

import org.apache.struts.action.ActionForm;

public class FicheCreneauForm extends ActionForm {
	
	private String codeCreneau, libellCreneau, nombreAdherents ;

	public String getCodeCreneau() {
		return codeCreneau;
	}

	public void setCodeCreneau(String codeCreneau) {
		this.codeCreneau = codeCreneau;
	}

	public String getLibellCreneau() {
		return libellCreneau;
	}

	public void setLibellCreneau(String libellCreneau) {
		this.libellCreneau = libellCreneau;
	}

	public String getNombreAdherents() {
		return nombreAdherents;
	}

	public void setNombreAdherents(String nombreAdherents) {
		this.nombreAdherents = nombreAdherents;
	}
	
	
}

package src.parametres;

import java.util.HashMap;

public class TableDeBase {
	
	private HashMap atelierRadio ;
	
	public HashMap getAtelierRadio() {
		HashMap h = new HashMap () ;
		h.put("1", "Lundi 20h00 - 23h00 (Adultes)" ) ;
		h.put("6", "Mercredi 20h00 - 22h00 (Adultes)");
		h.put("2", "Mardi 17h30 - 19h00 (Enfants)");
		h.put("4", "Mercredi 15h00 - 16h30 (Enfants)");
		h.put("3", "Mardi 19h00 - 20h30 (Ados)");
		h.put("5", "Mercredi 16h45 - 18h15 (Pré-ados)");
		return h;
	}
	
	public void setAtelierRadio(HashMap atelierRadio) {
		this.atelierRadio = atelierRadio;
	}
	
	public String getLibelleAtelier (String code) {
		String atelierLibelle = (String) atelierRadio.get(code) ;
		return atelierLibelle ;
	}
}

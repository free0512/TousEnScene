package src.ficheInscription.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.struts.action.ActionErrors;
import org.joda.time.DateTime;

import src.ficheInscription.FicheInscriptionForm;
import src.ficheInscription.ListeAdherentsForm;

public class ListeInscriptionDB {

	public static void rechercherListeAdherents (ListeAdherentsForm laForm, Connection cnx)
						throws Exception {
		ActionErrors err = new ActionErrors () ;
		try {
			int taillePage = laForm.getChoixTaillePage() ;
			int pageCourante = laForm.getPageCourante() ;
			int choixPage = laForm.getChoixPage() ;
			laForm.setPageCourante(choixPage);
			String sql = constituerRequet(laForm) ;
			int startRow = pageCourante * taillePage ;
			int lastRow = startRow + taillePage ;
			Statement lecture = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
													ResultSet.CONCUR_READ_ONLY) ;
			ResultSet rs = lecture.executeQuery(sql) ;
			rs.absolute(startRow) ;
			ArrayList<FicheInscriptionForm> listfiForm = new ArrayList<FicheInscriptionForm>();
			
			while (rs.next()) {
				FicheInscriptionForm fiForm = mapperVariables(rs) ;
				listfiForm.add(fiForm);
			}
			laForm.setListeAdherents(listfiForm);
		} catch (Exception e) {
			
		}
	}
	
	public static String constituerRequet (ListeAdherentsForm laForm) throws Exception {
		String sql = "select * from adherents " ;
		String whereCond = "";
		String nom = (laForm.getChoixNomEleve()!=null ? laForm.getChoixNomEleve().toUpperCase() :"" ) ;
		String prenom = (laForm.getChoixPrenomEleve()!=null ? laForm.getChoixPrenomEleve().toUpperCase() : "");
		if (!nom.isEmpty()) {
			whereCond += "where upper(nomadh) like '%" + nom + "%'" ; 
		}
		if (!prenom.isEmpty()) {
			if (whereCond.isEmpty()) {
			whereCond += "where upper(pread) like '%" + prenom +"%'" ;	
			} else {
			whereCond += "and upper(pread) like '%" + prenom +"%'" ;
			}
		}	
		sql += whereCond + " order by nomadh" ;
		return sql ;
	}
	
	public static FicheInscriptionForm mapperVariables (ResultSet rs) throws Exception {
		FicheInscriptionForm fiForm = new FicheInscriptionForm();
		 
		fiForm.setNomEleve(rs.getString("nomadh").trim());
		fiForm.setPrenomEleve(rs.getString("pread").trim());
		DateTime date = new DateTime(rs.getDate("datnaissadh")) ;
		String dateAlpha = String.valueOf(date.dayOfMonth().get()) +'/'+
						   String.valueOf(date.monthOfYear().get())+'/'+
						   String.valueOf(date.year().get());
		fiForm.setDateDeNaissanceEleve(dateAlpha);
		fiForm.setAdresse1(rs.getString("adresse1").trim());
		fiForm.setAdresse2(rs.getString("adresse2").trim());
		fiForm.setCodePostal(rs.getInt("codpostal"));
		fiForm.setVille(rs.getString("ville").trim());
		fiForm.setClasseScolaire(rs.getString("clasadh")!=null ? rs.getString("clasadh").trim() : "");
		return fiForm ;
		 
	}
	
}

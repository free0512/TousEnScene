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
import src.ficheInscription.SelectionAdherentsForm;

public class SelectionInscriptionDB {

	public static void rechercherListeAdherents (SelectionAdherentsForm laForm, Connection cnx)
						throws Exception {
		ActionErrors err = new ActionErrors () ;
		ResultSet rs = null ;
		Statement lecture = null;
		try {
			int taillePage = laForm.getChoixTaillePage() ;
			int pageCourante = laForm.getPageCourante() ;
			int choixPage = laForm.getChoixPage() ;
			laForm.setPageCourante(choixPage);
			String sql = constituerRequet(laForm) ;
			int startRow = pageCourante * taillePage ;
			int lastRow = startRow + taillePage ;
			 lecture = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
													ResultSet.CONCUR_READ_ONLY) ;
			 rs = lecture.executeQuery(sql) ;
			rs.absolute(startRow) ;
			ArrayList<FicheInscriptionForm> listfiForm = new ArrayList<FicheInscriptionForm>();
			
			while (rs.next() ) {
				FicheInscriptionForm fiForm = mapperVariables(rs) ;
				listfiForm.add(fiForm);
			}
			laForm.setselectionAdherents(listfiForm);
		} catch (Exception e) {}
		finally {
			rs.close();
			lecture.close();
		}
	}
	
	public static String constituerRequet (SelectionAdherentsForm laForm) throws Exception {
		String sql = "select * from adherents " ;
		String whereCond = "";
		String nom = (laForm.getChoixNomEleve()!=null ? laForm.getChoixNomEleve().toUpperCase() :"" ) ;
		String prenom = (laForm.getChoixPrenomEleve()!=null ? laForm.getChoixPrenomEleve().toUpperCase() : "");
		if (!nom.isEmpty()) {
			whereCond += "where upper(nomadh) like '%" + nom + "%'" ; 
		}
		if (!prenom.isEmpty()) {
			if (whereCond.isEmpty()) {
			whereCond += "where upper(preadh) like '%" + prenom +"%'" ;	
			} else {
			whereCond += "and upper(preadh) like '%" + prenom +"%'" ;
			}
		}	
		sql += whereCond + " order by nomadh" ;
		return sql ;
	}
	
	public static FicheInscriptionForm mapperVariables (ResultSet rs) throws Exception {
		FicheInscriptionForm fiForm = new FicheInscriptionForm();
		
		fiForm.setNumeroInterne(rs.getInt("identifiant"));
		fiForm.setNomEleve(rs.getString("nomadh").trim());
		fiForm.setPrenomEleve(rs.getString("preadh").trim());
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
		fiForm.setAtelierEleve(rs.getString("atladh")!=null ? rs.getString("atladh").trim() : "");
		fiForm.setReglementEleve(rs.getString("regadh")!=null ? rs.getString("regadh").trim() : "");
		
		return fiForm ;
		 
	}
	
}

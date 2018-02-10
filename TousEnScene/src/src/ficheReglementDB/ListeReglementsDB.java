package src.ficheReglementDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.struts.action.ActionErrors;
import org.joda.time.DateTime;

import src.ficheInscription.FicheInscriptionForm;
import src.ficheReglement.FicheReglementsForm;
import src.ficheReglement.ListeReglementsForm;

public class ListeReglementsDB {

	public static void rechercherListeReglements (ListeReglementsForm laForm, Connection cnx)
						throws Exception {
		ActionErrors err = new ActionErrors () ;
		ResultSet rs = null ;
		Statement lecture = null;
		try {
			int taillePage = laForm.getTaillePage() ;
			int pageCourante = laForm.getPageCourante() ;
			int choixPage = laForm.getChoixPage() ;
			laForm.setPageCourante(choixPage);
			String sql = constituerRequet(laForm) ;
			int startRow = pageCourante * taillePage ;
			int lastRow = startRow + taillePage ;
			lecture = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_READ_ONLY) ;
			try {	
			rs = lecture.executeQuery(sql) ;
			rs.absolute(startRow) ;
			ArrayList<FicheReglementsForm> listfiForm = new ArrayList<FicheReglementsForm>();
			
			while (rs.next()) {
				FicheReglementsForm fiForm = mapperVariables(rs) ;
				listfiForm.add(fiForm);
			}
			laForm.setListeReglements(listfiForm);
			} catch(SQLException e) {System.out.println(e.getMessage());}	
			} catch (Exception e) {}
		finally {
			rs.close();
			lecture.close();
		}	
	}
	
	public static String constituerRequet (ListeReglementsForm laForm) throws Exception {
		String sql = "select * from reglement " ;
		String whereCond = "";
		int numeroAdh= laForm.getNumeroAdherent() ;
		whereCond += "where identifiant = " + numeroAdh ; 	
		sql += whereCond + " order by id_reglement desc" ;
		return sql ;
	}
	
	public static FicheReglementsForm mapperVariables (ResultSet rs) throws Exception {
		FicheReglementsForm fiForm = new FicheReglementsForm();
		
	//	fiForm.set setNumeroInterne(rs.getInt("id_reglement"));
		fiForm.setModeReglement(rs.getString("modepaiement").trim());
		fiForm.setDescription(rs.getString("description").trim());
		DateTime date = new DateTime(rs.getDate("datereglement")) ;
		String dateAlpha = String.valueOf(date.dayOfMonth().get()) +'/'+
						   String.valueOf(date.monthOfYear().get())+'/'+
						   String.valueOf(date.year().get());
		fiForm.setDateReglement(dateAlpha);
		fiForm.setMontantReglement(rs.getDouble("montantreglement"));
		fiForm.setNumeroId(rs.getInt("id_reglement"));
		return fiForm ;
		 
	}
	
}

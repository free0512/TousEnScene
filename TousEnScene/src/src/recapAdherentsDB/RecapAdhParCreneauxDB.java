package src.recapAdherentsDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionErrors;

import antlr.collections.List;
import src.parametres.TableDeBase;
import src.recapAdherents.FicheCreneauForm;
import src.recapAdherents.RecapAdhParCreneauForm;

public class RecapAdhParCreneauxDB {

	public static void chargerRecapAdhParCreneau 
			(RecapAdhParCreneauForm recapAdhParCreneauForm, Connection cnx)
			throws Exception {
		ActionErrors err = new ActionErrors () ;
		ResultSet rs = null ;
		Statement lecture = null;
		try {
			
			String sql = constituerRequet() ;
			
			lecture = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_READ_ONLY) ;
			try {	
			rs = lecture.executeQuery(sql) ;
			
			ArrayList<FicheCreneauForm> recapAdhListMap = new ArrayList<FicheCreneauForm>() ;
			HashMap<String, String> libelleAtelierMap = new HashMap<String, String>();
			TableDeBase tableDeBase = new TableDeBase() ;
			libelleAtelierMap = tableDeBase.getAtelierRadio() ;
			while (rs.next()) {
				FicheCreneauForm ficheCrenau = new FicheCreneauForm();
				ficheCrenau.setCodeCreneau(rs.getString("atladh").trim()) ;
				ficheCrenau.setLibellCreneau(libelleAtelierMap.get(ficheCrenau.getCodeCreneau())) ;
				String nbre = String.valueOf(rs.getInt("nbr")) ;
				ficheCrenau.setNombreAdherents(nbre) ;
				recapAdhListMap.add(ficheCrenau);
			}
			recapAdhParCreneauForm.setRecapAdhList(recapAdhListMap);
			
			} catch(SQLException e) {
				System.out.println(e.getMessage());
				}	
			} catch (Exception e) {}
		finally {
			rs.close();
			lecture.close();
		}	
	}
	
	public static String constituerRequet () throws Exception {
		String sql = "select atladh, count(*) nbr from adherents group by atladh order by atladh" ;
		return sql ;
	}	
}

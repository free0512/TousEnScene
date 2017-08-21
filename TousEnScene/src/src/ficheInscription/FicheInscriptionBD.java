package src.ficheInscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class FicheInscriptionBD {
	
	public ActionForward insertFicheIns (ActionMapping mapping, 
										 Connection cnx, 
										 ActionForm  form)
						 throws SQLException {
		int status = 0;
		FicheInscriptionForm fins = (FicheInscriptionForm) form; 
		//ActionErrors err = new ActionErrors() ;
		PreparedStatement st = null ;
		//Statement st = null ;
		ResultSet rs = null ;
		String reqSQL = "insert into adherents (nomadh,    pread,   datnaissadh, adresse1, "
				+ "adresse2, codpostal, ville,   sexadh,   majadh,  atladh,      regadh,"
				+ "nompere,  prenpere,  nommere, prenmere, telpere, telmere,     emailpere, emailmere,"
				+ "autadh,   autnom,    phoadh, clasadh )"
				+ " values(                     ?,         ?,       ?,           ?, "
				+ "?,        ?,        ?,       ?,         ?,       ?,           ?,"
				+ "?,        ?,        ?,       ?,         ?,       ?,           ?,        ?,"
				+ "?,        ?,        ?,	    ? )" ;
		try {
			st = cnx.prepareStatement(reqSQL);
			//st = cnx.createStatement() ;
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate locadate = LocalDate.parse(fins.getDateDeNaissanceEleve(), formatter) ;
			Date sqlDate = Date.valueOf(locadate);
//			String valeurReq = "'" + ((fins.getNomEleve()  != null) ? fins.getNomEleve() : "")+ "'," +
//			"'"+	((fins.getPrenomEleve() != null) ? fins.getPrenomEleve() : "") + "'," +
//			"'"+	sqlDate.toString() + "'," +
//			"'"+	(fins.getAdresse1() != null ? fins.getAdresse1() : "") + "'," +
//			"'"+	(fins.getAdresse2() != null ? fins.getAdresse2() : "") + "'," +
//			"'"+	Integer.toString(fins.getCodePostal()) + "'," +
//			"'"+	(fins.getVille() != null ? fins.getVille() : null)+ "'," +
//			"'"+	(fins.getSexeEleve() != null ? fins.getSexeEleve() : "") + "'," +
//			"'"+	(fins.getMajoriteEleve() != null ? fins.getMajoriteEleve()  : "") + "'," +
//			"'"+	(fins.getAtelierEleve() != null ? fins.getAtelierEleve()   : "") + "'," +
//			"'"+	(fins.getReglementEleve() != null ? fins.getReglementEleve()  : "") + "'," +
//			"'"+	(fins.getNomPere() != null ? fins.getNomPere()  : "") + "'," +
//			"'"+	(fins.getPrenomPere() != null ? fins.getPrenomPere()  : "") + "'," +
//			"'"+	(fins.getNomMere() != null ? fins.getNomMere() : "") + "'," +
//			"'"+	(fins.getPrenomMere() != null ? fins.getPrenomMere()  : "") + "'," +
//			"'"+	(fins.getTelephonePere()!= null ? fins.getTelephonePere()  : "") + "'," +
//			"'"+	(fins.getTelephoneMere() != null ? fins.getTelephoneMere()  : "") + "'," +
//			"'"+	(fins.getEmailPere()!= null ? fins.getEmailPere()  : "") + "'," +
//			"'"+	(fins.getEmailMere() != null ? fins.getEmailMere()  : "") + "'," +
//			"'"+	(fins.getAutoriser() != null ? fins.getAutoriser()  : "") + "'," +
//			"'"+	(fins.getSiNonQui() != null ? fins.getSiNonQui()  : "") + "'," +
//			"'"+	(fins.getPhotographier() != null ? fins.getPhotographier()  : "") + "',"  +
//			"'"+    (fins.getClasseScolaire()!= null ? fins.getClasseScolaire() : "") + "'" ;

			st.setString(1, (fins.getNomEleve()  != null) ? fins.getNomEleve() : "");
			st.setString(2, (fins.getPrenomEleve() != null) ? fins.getPrenomEleve() : "");			
			st.setDate(3, sqlDate);
			st.setString(4, fins.getAdresse1() != null ? fins.getAdresse1() : "");
			st.setString(5, fins.getAdresse2() != null ? fins.getAdresse2() : "");
			st.setLong(6, fins.getCodePostal());
			st.setString(7, fins.getVille() != null ? fins.getVille() : null);
			st.setString(8, fins.getSexeEleve() != null ? fins.getSexeEleve() : "") ;
			st.setString(9, fins.getMajoriteEleve() != null ? fins.getMajoriteEleve()  : "") ;
			st.setString(10, fins.getAtelierEleve() != null ? fins.getAtelierEleve()   : "") ;
			st.setString(11, fins.getReglementEleve() != null ? fins.getReglementEleve()  : "") ;
			st.setString(12, fins.getNomPere() != null ? fins.getNomPere()  : "") ;
			st.setString(13, fins.getPrenomPere() != null ? fins.getPrenomPere()  : "") ;
			st.setString(14, fins.getNomMere() != null ? fins.getNomMere() : "") ;
			st.setString(15, fins.getPrenomMere() != null ? fins.getPrenomMere()  : "") ;
			st.setString(16, fins.getTelephonePere()!= null ? fins.getTelephonePere()  : "") ;
			st.setString(17, fins.getTelephoneMere() != null ? fins.getTelephoneMere()  : "") ;
			st.setString(18, fins.getEmailPere()!= null ? fins.getEmailPere()  : "") ;
			st.setString(19, fins.getEmailMere() != null ? fins.getEmailMere()  : "") ;
			st.setString(20, fins.getAutoriser() != null ? fins.getAutoriser()  : "") ;
			st.setString(21, fins.getSiNonQui() != null ? fins.getSiNonQui()  : "") ;
			st.setString(22, fins.getPhotographier() != null ? fins.getPhotographier()  : "") ;
			st.setString(23, fins.getClasseScolaire() != null ? fins.getClasseScolaire() : "");
//			String reqSQL = "insert into adherents (nomadh,    pread,   datnaissadh, adresse1, "
//					+ "adresse2, codpostal, ville,   sexadh,   majadh,  atladh,      regadh,"
//					+ "nompere,  prenpere,  nommere, prenmere, telpere, telmere,     emailpere, emailmere,"
//					+ "autadh,   autnom,    phoadh,  clasadh )"
//					+ " values("  + valeurReq.trim() + ")" ;
			
			//status =  st.executeUpdate(reqSQL, Statement.RETURN_GENERATED_KEYS);			
			status = st.executeUpdate();
			fins.setStatusInsert(status);
			//rs = st.getGeneratedKeys() ;
			//while (rs.next()) {
			//	fins.setNumeroInterne(rs.getInt("identifiant"));
			//}
			//cnx.commit();
			String reqSQLinterne = "select max(identifiant) from adherents" ;
			Statement st2=cnx.createStatement() ;
			ResultSet rs2 = st2.executeQuery(reqSQLinterne) ;
			while (rs2.next()) {
				fins.setNumeroInterne(rs2.getInt(1));
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			//cnx.rollback();
			fins.setStatusInsert(0);
			return mapping.findForward("failed") ;
		} finally {
			cnx.close();
		}
	}

}

	
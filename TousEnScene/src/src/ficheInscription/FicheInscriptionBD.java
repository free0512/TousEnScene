package src.ficheInscription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
		ResultSet rs2 = null ;
		String reqSQL = "insert into adherents (nomadh,    preadh,   datnaissadh, adresse1, "
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
	
			status = st.executeUpdate();
			fins.setStatusInsert(status);

			String reqSQLinterne = "select max(identifiant) from adherents" ;
			Statement st2=cnx.createStatement() ;
			rs2 = st2.executeQuery(reqSQLinterne) ;
			while (rs2.next()) {
				fins.setNumeroInterne(rs2.getInt(1));
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			//cnx.rollback();
			fins.setStatusInsert(0);
			return mapping.findForward("failed") ;
		} finally {
			rs2.close();
			st.close();
			
		}
	}
	
	public ActionForward changeFicheIns (ActionMapping mapping, 
			 Connection cnx, 
			 ActionForm  form)
					 throws SQLException {
		int status = 0;
		FicheInscriptionForm fins = (FicheInscriptionForm) form; 
		
		PreparedStatement st = null ;

		//ResultSet rs2 = null ;
		String reqSQL = "update adherents set nomadh = ?, preadh=?, datnaissadh=?, adresse1=?, " 
		+ " adresse2=?, codpostal=?, ville=?,   sexadh=?,   majadh=?,  atladh=?, regadh=?,"
		+ " nompere=?,  prenpere=?,  nommere=?, prenmere=?, telpere=?, telmere=?, emailpere=?, emailmere=?,"
		+ " autadh=?,   autnom=?,    phoadh=?, clasadh=?  " 
		+ "where identifiant = ? " ;
	
		try {
			st = cnx.prepareStatement(reqSQL);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate locadate = LocalDate.parse(fins.getDateDeNaissanceEleve(), formatter) ;
			Date sqlDate = Date.valueOf(locadate);

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
			st.setInt(24, fins.getNumeroInterne());

			status = st.executeUpdate();
			
			return mapping.findForward("success");
		} catch (Exception e) {
			//cnx.rollback();
			return mapping.findForward("failed") ;
		} finally {
			//rs2.close();
			st.close();

		}
	}

	public FicheInscriptionForm lireFicheAdherent (ActionMapping mapping, 
											HttpServletRequest req ,
											Connection cnx, 
			 								FicheInscriptionForm  form)
			 								throws SQLException {
		
		Statement st = null ;
		ResultSet rs = null ;
		//HttpSession session = req.getSession(false);
		String reqSQL = "select * from adherents where identifiant=" + form.getNumeroInterne() ;
		try {
	
			st = cnx.createStatement() ;
			rs = st.executeQuery(reqSQL) ;
			while (rs.next()) {
				form.setNomEleve(rs.getString("nomadh").trim());
				form.setPrenomEleve(rs.getString("preadh").trim());
				form.setClasseScolaire(rs.getString("clasadh").trim());
				form.setAdresse1(rs.getString("adresse1").trim());
				form.setAdresse2(rs.getString("adresse2").trim());
				form.setCodePostal(rs.getInt("codpostal"));
				form.setVille(rs.getString("ville").trim());
				Date  date = rs.getDate("datnaissadh") ;
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				form.setDateDeNaissanceEleve(dateFormat.format(date)) ;	
				form.setSexeEleve(rs.getString("sexadh").trim());
				form.setStatutEleve(rs.getString("majadh").trim());
				form.setNomPere(rs.getString("nompere").trim());
				form.setPrenomPere(rs.getString("prenpere").trim());
				form.setNomMere(rs.getString("nommere").trim());
				form.setPrenomMere(rs.getString("prenmere").trim());
				form.setTelephonePere(rs.getString("telpere").trim());
				form.setTelephoneMere(rs.getString("telmere").trim());
				form.setEmailPere(rs.getString("emailpere").trim());
				form.setEmailMere(rs.getString("emailmere").trim());
				form.setAtelierEleve(rs.getString("atladh").trim());
				form.setReglementEleve(rs.getString("regadh").trim());
				form.setAutoriser(rs.getString("autadh").trim());
				form.setSiNonQui(rs.getString("autnom").trim());
				form.setPhotographier(rs.getString("phoadh").trim());
				
				//session.setAttribute("ficheInscription", form);
			}
			return form ;
		} catch (SQLException e) {
			return null;
		}
		finally {
			if (rs != null) {
				try { 
					rs.close();
				} catch (SQLException e) {}
			}
			if (st != null) {
				try { 
					st.close();
				} catch (SQLException e) {
					String er= "erue"; // juste pour débuger le SQLException
				}
			}
		}
			
	}
}

	
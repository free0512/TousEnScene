package src.ficheReglementDB;

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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.joda.time.DateTime;

import src.ficheInscription.FicheInscriptionForm;
import src.ficheReglement.FicheReglementsForm;
import src.ficheReglement.ListeReglementsForm;

public class FicheReglementBD {
	
	public ActionForward insertFicheReg (HttpServletRequest req,  ActionMapping mapping, 
										 Connection cnx, 
										 ActionForm  form)
						 throws SQLException {
		int status = 0;
		FicheReglementsForm ficheReglementForm = (FicheReglementsForm) form; 
		HttpSession session = req.getSession(false);
		ListeReglementsForm listeReglementForm = (ListeReglementsForm) session.getAttribute("listeReglementForm");
	
		PreparedStatement st = null ;	
		ResultSet rs2 = null ;
		String reqSQLselect = "select * from reglement where identifiant = ? and id_reglement=?" ;
		
		
		String reqSQLcreation = "insert into reglement "
				+ "(identifiant, modepaiement,datereglement, montantreglement, description) "				
				+ " values(  ?,       ?,           ?,          ?,                 ? )" ;
		try {
			
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			LocalDate locadate = LocalDate.parse(ficheReglementForm.getDateReglement(), formatter) ;	 
//			Date sqlDate = Date.valueOf(locadate);
			String[] tabDate = ficheReglementForm.getDateReglement().split("/");
			String 	 aaaa_mm_aa= tabDate[2]+'-'+tabDate[1]+'-'+tabDate[0]; 		
			Date sqlDate = Date.valueOf(aaaa_mm_aa) ;
			
			st = cnx.prepareStatement(reqSQLselect);
			st.setInt(1, listeReglementForm.getNumeroAdherent()) ;
			st.setInt(2, ficheReglementForm.getNumeroId());
			ResultSet RSselect = st.executeQuery() ;
			//session.setAttribute("listeReglementForm", listeReglementForm) ;
			
			// .getRow() returns the current row number, 0 if there is no current row
			//          == 0 means that the record doesn't exist.         
			if (!RSselect.next()) {
		
			st = null; 
			st = cnx.prepareStatement(reqSQLcreation, PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, listeReglementForm.getNumeroAdherent() );
			st.setString(2, (ficheReglementForm.getModeReglement() != null) ? ficheReglementForm.getModeReglement() : "");			
			st.setDate(3, sqlDate); 
			st.setDouble(4, ficheReglementForm.getMontantReglement() );
			st.setString(5, (ficheReglementForm.getDescription() != null) ? ficheReglementForm.getDescription() : "");			 
			
			st.executeUpdate(); 
			
			rs2 = st.getGeneratedKeys();
			while (rs2.next()) {
				ficheReglementForm.setNumeroId(rs2.getInt(1));
				// J'utilise un objet List en cas d'ajout et ArrayList en cas de création. 
				// car l'objet List retourne un message "NullPointerException" en cas de création
				// Bref, si List est null, il n'accepte la métode add, Or ArrayList l'accepte !!
				List<FicheReglementsForm> listReglement = new ArrayList<FicheReglementsForm>() ;
				List<FicheReglementsForm> listReglement2 = new ArrayList<FicheReglementsForm>() ;
				listReglement = listeReglementForm.getListeReglements() ;
				if (listReglement==null) {
					listReglement2.add(ficheReglementForm) ; 
				} else {
					listReglement2.add(ficheReglementForm);
					for (FicheReglementsForm  liste : listReglement) {
						listReglement2.add(liste) ;
					}
				}	
				listeReglementForm.setListeReglements(listReglement2);
//				listReglement = listeReglementForm.getListeReglements() ;
//				if (listReglement != null) {
//					listReglement.add(ficheReglementForm) ;
//					listeReglementForm.setListeReglements(listReglement);
//				} else {
//					ArrayList<FicheReglementsForm> listReglement2 = new ArrayList<FicheReglementsForm>() ;
//					listReglement2.add(ficheReglementForm) ;
//					listeReglementForm.setListeReglements(listReglement2);
//				}
			}
		} else { // en cas de modification
			String reqSQLmodif = "update reglement set modepaiement = ?, description=?,"
					+ "montantreglement=  ?, datereglement = ? "
					+ "where identifiant = ? and  id_reglement = ? " ;
			st = null; 
			st = cnx.prepareStatement(reqSQLmodif) ;
			
			st.setString(1, (ficheReglementForm.getModeReglement() != null) ? ficheReglementForm.getModeReglement() : "");	
			st.setString(2, (ficheReglementForm.getDescription() != null) ? ficheReglementForm.getDescription() : "");			 
			st.setDouble(3, ficheReglementForm.getMontantReglement() );
			st.setDate(4, sqlDate); 
			st.setInt(5, listeReglementForm.getNumeroAdherent() );
			st.setInt(6, ficheReglementForm.getNumeroId());
			
			st.executeUpdate(); 
		}
//			D'abord en ajoute ficheReglemnetsForm à la liste 
			
			return mapping.findForward("success");
			
		} catch (Exception e) {
			//cnx.rollback();
//			fins.setStatusInsert(0);
			return mapping.findForward("failed") ;
		} finally {
			//rs2.close();			st.close();
			
		}
	}
	
}

	
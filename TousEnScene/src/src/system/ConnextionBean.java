package src.system;

import java.util.ResourceBundle;

public class ConnextionBean {

	private static final String PARAMETRES_SYSTEM = "ressources.Systemes" ;
	private static final String SGBDR = "SGBD" ;
	private static final String DRIVER = "Driver" ;
	private static final String HOTE = "Hote" ;
	private static final String PORT = "Port" ;
	private static final String NOMBD = "Nombd" ;
	private static final String PROFIL = "Profil" ;
	private static final String MDP = "Mdp" ;
	private static final String URL = "Url";
	private static final String LIBELLE = "Libelle" ;
	
	private String SGBD, Driver, Hote, Port, Nombd, Profil, Mdp ,Libelle ;
	private String url ;
	
	public void getDataSystem ()
	{
		ResourceBundle parametres = ResourceBundle.getBundle(PARAMETRES_SYSTEM) ;
		setSGBD(parametres.getString(SGBDR));
		setDriver(parametres.getString(DRIVER));
		setHote(parametres.getString(HOTE));
		setPort(parametres.getString(PORT));
		setNombd(parametres.getString(NOMBD));
		setProfil(parametres.getString(PROFIL));
		setMdp(parametres.getString(MDP));
		setLibelle(parametres.getString(LIBELLE));
		setUrl(parametres.getString(URL));
	}
	
	public String getSGBD() {
		return SGBD;
	}
	public void setSGBD(String sGBD) {
		SGBD = sGBD;
	}
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	public String getHote() {
		return Hote;
	}
	public void setHote(String hote) {
		Hote = hote;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public String getNombd() {
		return Nombd;
	}
	public void setNombd(String nombd) {
		Nombd = nombd;
	}
	public String getProfil() {
		return Profil;
	}
	public void setProfil(String profil) {
		Profil = profil;
	}
	public String getMdp() {
		return Mdp;
	}
	public void setMdp(String mdp) {
		Mdp = mdp;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

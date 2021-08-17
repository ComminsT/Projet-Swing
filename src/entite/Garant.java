package entite;

public class Garant {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String cp;
	private String ville;
	private String pays;
	private String tel;
	private String mail;
	private int id_contratl;
	@Override
	public String toString() {
		return "Garant [nom=" + nom + ", prenom=" + prenom + ", id_contratl=" + id_contratl + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId_contratl() {
		return id_contratl;
	}
	public void setId_contratl(int id_contratl) {
		this.id_contratl = id_contratl;
	}
	public Garant() {
		super();
		// TODO Auto-generated constructor stub
	}

}

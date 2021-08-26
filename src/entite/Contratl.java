package entite;

public class Contratl {
	private int id;
	private String date;
	private int id_locataire;
	private int id_bien;
	private String datefin;
	public String getDatefin() {
		return datefin;
	}
	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}
	@Override
	public String toString() {
		return "Contratl [date=" + date + ", id_locataire=" + id_locataire + ", id_bien=" + id_bien + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId_locataire() {
		return id_locataire;
	}
	public void setId_locataire(int id_locataire) {
		this.id_locataire = id_locataire;
	}
	public int getId_bien() {
		return id_bien;
	}
	public void setId_bien(int id_bien) {
		this.id_bien = id_bien;
	}
	public Contratl() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package entite;

public class Contratp {
	private int id;
	private String date;
	private int id_bien;
	@Override
	public String toString() {
		return "Contratp [date=" + date + ", id_bien=" + id_bien + "]";
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
	public int getId_bien() {
		return id_bien;
	}
	public void setId_bien(int id_bien) {
		this.id_bien = id_bien;
	}
	public Contratp() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

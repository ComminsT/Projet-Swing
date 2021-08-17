package entite;

public class Image {
	private int id;
	private String nom;
	private int id_bien;
	@Override
	public String toString() {
		return "Image [nom=" + nom + ", id_bien=" + id_bien + "]";
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
	public int getId_bien() {
		return id_bien;
	}
	public void setId_bien(int id_bien) {
		this.id_bien = id_bien;
	}
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

}

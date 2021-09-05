package entite;

public class Comptabilite {
	
	private int id;
	private String datedue;
	private String datepaye;
	private String categorie;
	private Double montantdu;
	private Double montantpaye;
	private int id_contratl;
	private int visible;
	
	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public Comptabilite() {

	}

	public int getId() {
		return id;
	}

	public String getDatedue() {
		return datedue;
	}

	public String getDatepaye() {
		return datepaye;
	}

	public String getCategorie() {
		return categorie;
	}

	public Double getMontantdu() {
		return montantdu;
	}

	public Double getMontantpaye() {
		return montantpaye;
	}

	public int getId_contratl() {
		return id_contratl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDatedue(String datedue) {
		this.datedue = datedue;
	}

	public void setDatepaye(String datepaye) {
		this.datepaye = datepaye;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setMontantdu(Double montantdu) {
		this.montantdu = montantdu;
	}

	public void setMontantpaye(Double montantpaye) {
		this.montantpaye = montantpaye;
	}

	public void setId_contratl(int id_contratl) {
		this.id_contratl = id_contratl;
	}

	@Override
	public String toString() {
		return " ID "+ id;
	}
	
	

}

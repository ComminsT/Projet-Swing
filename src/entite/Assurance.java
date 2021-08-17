package entite;

public class Assurance {
	
	private int id;
	private String type;
	private String numero;
	private int id_contratl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getId_contratl() {
		return id_contratl;
	}
	public void setId_contratl(int id_contratl) {
		this.id_contratl = id_contratl;
	}
	@Override
	public String toString() {
		return "Assurance [numero=" + numero + "]";
	}
	public Assurance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

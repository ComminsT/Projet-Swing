package entite;

public class Historique {
	private int id;
	private String date;
	private String action;
	private int id_agent;
	@Override
	public String toString() {
		return "Historique [date=" + date + ", action=" + action + ", id_agent=" + id_agent + "]";
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getId_agent() {
		return id_agent;
	}
	public void setId_agent(int id_agent) {
		this.id_agent = id_agent;
	}
	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Agent;
import entite.Database;

public class AgentDAO {
	public AgentDAO() {

	}

	public void save(Agent agent) {

		try {

			if (agent.getId() != 0) {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"UPDATE agent set nom=?, prenom=?,identifiant=?,mdp=?,adresse=?,cp=?,ville=?,dateentree=?,statut=?,mail=?,tel=? WHERE id=?");
				ps.setString(1, agent.getNom());
				ps.setString(2, agent.getPrenom());
				ps.setString(3, agent.getIdentifiant());
				ps.setString(4, agent.getMdp());
				ps.setString(5, agent.getAdresse());
				ps.setString(6, agent.getCp());
				ps.setString(7, agent.getVille());
				ps.setString(8, agent.getDateentree());
				ps.setString(9, agent.getStatut());
				ps.setString(10, agent.getMail());
				ps.setString(11, agent.getTel());
				ps.setInt(12, agent.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"INSERT INTO agent (nom,prenom,identifiant,mdp,adresse,cp,ville,dateentree,statut,mail,tel) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, agent.getNom());
				ps.setString(2, agent.getPrenom());
				ps.setString(3, agent.getIdentifiant());
				ps.setString(4, agent.getMdp());
				ps.setString(5, agent.getAdresse());
				ps.setString(6, agent.getCp());
				ps.setString(7, agent.getVille());
				ps.setString(8, agent.getDateentree());
				ps.setString(9, agent.getStatut());
				ps.setString(10, agent.getMail());
				ps.setString(11, agent.getTel());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Agent getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM agent WHERE id=?");
			ps.setInt(1, id);

			ResultSet resultat = ps.executeQuery();

			Agent agent = new Agent();
			if (resultat.next()) {
				agent.setId(resultat.getInt("id"));
				agent.setNom(resultat.getString("nom"));
				agent.setPrenom(resultat.getString("prenom"));
				agent.setIdentifiant(resultat.getString("identifiant"));
				agent.setMdp(resultat.getString("mdp"));
				agent.setAdresse(resultat.getString("adresse"));
				agent.setCp(resultat.getString("cp"));
				agent.setVille(resultat.getString("ville"));
				agent.setDateentree(resultat.getString("dateentree"));
				agent.setStatut(resultat.getString("statut"));
				agent.setMail(resultat.getString("mail"));
				agent.setTel(resultat.getString("tel"));

			}
			return agent;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Agent> getAll() {
		ArrayList<Agent> agents = new ArrayList<Agent>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM agent");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Agent agent = new Agent();
				agent.setId(resultat.getInt("id"));
				agent.setNom(resultat.getString("nom"));
				agent.setPrenom(resultat.getString("prenom"));
				agent.setIdentifiant(resultat.getString("identifiant"));
				agent.setMdp(resultat.getString("mdp"));
				agent.setAdresse(resultat.getString("adresse"));
				agent.setCp(resultat.getString("cp"));
				agent.setVille(resultat.getString("ville"));
				agent.setDateentree(resultat.getString("dateentree"));
				agent.setStatut(resultat.getString("statut"));
				agent.setMail(resultat.getString("mail"));
				agent.setTel(resultat.getString("tel"));
				agents.add(agent);
			}
			return agents;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM agent WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

	public ArrayList<String> getAllMail() {
		ArrayList<String> mails = new ArrayList<String>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM agent");
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				mails.add(resultat.getString("mail"));
			}
			return mails;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<String> getAllPhone() {
		ArrayList<String> phones = new ArrayList<String>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM agent");
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				phones.add(resultat.getString("tel"));
			}
			return phones;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Agent> getByIdentifiant(String identifiant,String mdp) {
try {
	PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM agent WHERE identifiant=? AND mdp=?");
	ps.setString(1, identifiant);
	ps.setString(2, mdp);
	ResultSet resultat=ps.executeQuery();
	ArrayList<Agent>agents = new ArrayList<Agent>();
	if(resultat.next()) {
		Agent agent = new Agent();
		agent.setId(resultat.getInt("id"));
		agent.setNom(resultat.getString("nom"));
		agent.setPrenom(resultat.getString("prenom"));
		agent.setIdentifiant(resultat.getString("identifiant"));
		agent.setMdp(resultat.getString("mdp"));
		agent.setAdresse(resultat.getString("adresse"));
		agent.setCp(resultat.getString("cp"));
		agent.setVille(resultat.getString("ville"));
		agent.setDateentree(resultat.getString("dateentree"));
		agent.setStatut(resultat.getString("statut"));
		agent.setMail(resultat.getString("mail"));
		agent.setTel(resultat.getString("tel"));
		agents.add(agent);
	}
		return agents;
	}catch(Exception ex) {
		ex.printStackTrace();
		return null;
	}
}

}

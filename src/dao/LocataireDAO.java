package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Database;
import entite.Locataire;

public class LocataireDAO {
	public LocataireDAO() {

	}

	public void save(Locataire locataire) {

		try {

			if (locataire.getId() != 0) {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"UPDATE locataire set nom=?, prenom=?,adresse=?,ville=?,cp=?,pays=?,tel=?,naissance=?,statut=?,situation=?,mail=? WHERE id=?");
				ps.setString(1, locataire.getNom());
				ps.setString(2, locataire.getPrenom());
				ps.setString(3, locataire.getAdresse());
				ps.setString(4, locataire.getVille());
				ps.setString(5, locataire.getCp());
				ps.setString(6, locataire.getPays());
				ps.setString(7, locataire.getTel());
				ps.setString(8, locataire.getNaissance());
				ps.setString(9, locataire.getStatut());
				ps.setString(10, locataire.getSituation());
				ps.setString(11, locataire.getMail());
				ps.setInt(12, locataire.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"INSERT INTO locataire (nom,prenom,adresse,ville,cp,pays,tel,naissance,statut,situation,mail) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, locataire.getNom());
				ps.setString(2, locataire.getPrenom());
				ps.setString(3, locataire.getAdresse());
				ps.setString(4, locataire.getVille());
				ps.setString(5, locataire.getCp());
				ps.setString(6, locataire.getPays());
				ps.setString(7, locataire.getTel());
				ps.setString(8, locataire.getNaissance());
				ps.setString(9, locataire.getStatut());
				ps.setString(10, locataire.getSituation());
				ps.setString(11, locataire.getMail());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Locataire getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire WHERE id=?");
			ps.setInt(1, id);

			ResultSet resultat = ps.executeQuery();

			Locataire locataire = new Locataire();
			if (resultat.next()) {
				locataire.setId(resultat.getInt("id"));
				locataire.setNom(resultat.getString("nom"));
				locataire.setPrenom(resultat.getString("prenom"));
				locataire.setAdresse(resultat.getString("adresse"));
				locataire.setVille(resultat.getString("ville"));
				locataire.setCp(resultat.getString("cp"));
				locataire.setPays(resultat.getString("pays"));
				locataire.setTel(resultat.getString("tel"));
				locataire.setNaissance(resultat.getString("naissance"));
				locataire.setStatut(resultat.getString("statut"));
				locataire.setSituation(resultat.getString("situation"));
				locataire.setMail(resultat.getString("mail"));
				locataire.setId(resultat.getInt("id"));
			}
			return locataire;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Locataire> getAll() {
		ArrayList<Locataire> locataires = new ArrayList<Locataire>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Locataire locataire = new Locataire();
				locataire.setId(resultat.getInt("id"));
				locataire.setNom(resultat.getString("nom"));
				locataire.setPrenom(resultat.getString("prenom"));
				locataire.setAdresse(resultat.getString("adresse"));
				locataire.setVille(resultat.getString("ville"));
				locataire.setCp(resultat.getString("cp"));
				locataire.setPays(resultat.getString("pays"));
				locataire.setTel(resultat.getString("tel"));
				locataire.setNaissance(resultat.getString("naissance"));
				locataire.setStatut(resultat.getString("statut"));
				locataire.setSituation(resultat.getString("situation"));
				locataire.setMail(resultat.getString("mail"));
				locataire.setId(resultat.getInt("id"));
				locataires.add(locataire);
			}
			return locataires;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM locataire WHERE id=?");
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
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire");
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
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire");
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

	public ArrayList<Locataire> getAllByIdAgent(int id) {
		ArrayList<Locataire>locataires=new ArrayList<Locataire>();
		try {PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire WHERE id IN(SELECT id_locataire FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?)) ");
		ps.setInt(1, id);
		ResultSet resultat=ps.executeQuery();
		while(resultat.next()) {
			Locataire locataire = new Locataire();
			locataire.setId(resultat.getInt("id"));
			locataire.setNom(resultat.getString("nom"));
			locataire.setPrenom(resultat.getString("prenom"));
			locataire.setAdresse(resultat.getString("adresse"));
			locataire.setVille(resultat.getString("ville"));
			locataire.setCp(resultat.getString("cp"));
			locataire.setPays(resultat.getString("pays"));
			locataire.setTel(resultat.getString("tel"));
			locataire.setNaissance(resultat.getString("naissance"));
			locataire.setStatut(resultat.getString("statut"));
			locataire.setSituation(resultat.getString("situation"));
			locataire.setMail(resultat.getString("mail"));
			locataire.setId(resultat.getInt("id"));
			locataires.add(locataire);
		}
return locataires;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	public ArrayList<Locataire>getByKeywordsAndByIdAgent(String keyword,int id){
		ArrayList<Locataire>locataires=new ArrayList<Locataire>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM locataire WHERE (nom LIKE ? OR prenom LIKE ? OR ville LIKE ?) AND  id IN(SELECT id_locataire FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?))");
			ps.setString(1,"%" + keyword + "%");
			ps.setString(2,"%" + keyword + "%");
			ps.setString(3,"%" + keyword + "%");
			ps.setInt(4, id);
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				Locataire locataire = new Locataire();
				locataire.setId(resultat.getInt("id"));
				locataire.setNom(resultat.getString("nom"));
				locataire.setPrenom(resultat.getString("prenom"));
				locataire.setAdresse(resultat.getString("adresse"));
				locataire.setVille(resultat.getString("ville"));
				locataire.setCp(resultat.getString("cp"));
				locataire.setPays(resultat.getString("pays"));
				locataire.setTel(resultat.getString("tel"));
				locataire.setNaissance(resultat.getString("naissance"));
				locataire.setStatut(resultat.getString("statut"));
				locataire.setSituation(resultat.getString("situation"));
				locataire.setMail(resultat.getString("mail"));
				locataire.setId(resultat.getInt("id"));
				locataires.add(locataire);
			}
			return locataires;
		
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
			
		}
	}

}

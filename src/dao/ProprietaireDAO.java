package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Database;
import entite.Proprietaire;

public class ProprietaireDAO {
	public ProprietaireDAO() {

	}

	public void save(Proprietaire proprietaire) {

		try {

			if (proprietaire.getId() != 0) {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"UPDATE proprietaire set nom=?,prenom=?,adresse=?,ville=?,cp=?,pays=?,tel=?,naissance=?,mail=?,visible=? WHERE id=? ");
				ps.setString(1, proprietaire.getNom());
				ps.setString(2, proprietaire.getPrenom());
				ps.setString(3, proprietaire.getAdresse());
				ps.setString(4, proprietaire.getVille());
				ps.setString(5, proprietaire.getCp());
				ps.setString(6, proprietaire.getPays());
				ps.setString(7, proprietaire.getTel());
				ps.setString(8, proprietaire.getNaissance());
				ps.setString(9, proprietaire.getMail());
				ps.setInt(10, proprietaire.getVisible());
				ps.setInt(11, proprietaire.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"INSERT INTO proprietaire (nom,prenom,adresse,ville,cp,pays,tel,naissance,mail) VALUES(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, proprietaire.getNom());
				ps.setString(2, proprietaire.getPrenom());
				ps.setString(3, proprietaire.getAdresse());
				ps.setString(4, proprietaire.getVille());
				ps.setString(5, proprietaire.getCp());
				ps.setString(6, proprietaire.getPays());
				ps.setString(7, proprietaire.getTel());
				ps.setString(8, proprietaire.getNaissance());
				ps.setString(9, proprietaire.getMail());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Proprietaire getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM proprietaire WHERE id=? AND visible=0");
			ps.setInt(1, id);

			ResultSet resultat = ps.executeQuery();

			Proprietaire proprietaire = new Proprietaire();
			if (resultat.next()) {
				proprietaire.setId(resultat.getInt("id"));
				proprietaire.setNom(resultat.getString("nom"));
				proprietaire.setPrenom(resultat.getString("prenom"));
				proprietaire.setAdresse(resultat.getString("adresse"));
				proprietaire.setVille(resultat.getString("ville"));
				proprietaire.setCp(resultat.getString("cp"));
				proprietaire.setPays(resultat.getString("pays"));
				proprietaire.setTel(resultat.getString("tel"));
				proprietaire.setNaissance(resultat.getString("naissance"));
				proprietaire.setMail(resultat.getString("mail"));
				proprietaire.setVisible(resultat.getInt("visible"));

			}
			return proprietaire;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Proprietaire> getAll() {
		ArrayList<Proprietaire> proprietaires = new ArrayList<Proprietaire>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM proprietaire WHERE visible=0 ORDER BY nom");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Proprietaire proprietaire = new Proprietaire();
				proprietaire.setId(resultat.getInt("id"));
				proprietaire.setNom(resultat.getString("nom"));
				proprietaire.setPrenom(resultat.getString("prenom"));
				proprietaire.setAdresse(resultat.getString("adresse"));
				proprietaire.setVille(resultat.getString("ville"));
				proprietaire.setCp(resultat.getString("cp"));
				proprietaire.setPays(resultat.getString("pays"));
				proprietaire.setTel(resultat.getString("tel"));
				proprietaire.setNaissance(resultat.getString("naissance"));
				proprietaire.setMail(resultat.getString("mail"));
				proprietaire.setVisible(resultat.getInt("visible"));

				proprietaires.add(proprietaire);
			}
			return proprietaires;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("UPDATE proprietaire SET visible=1 WHERE id=?");
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
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM proprietaire WHERE visible=0");
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
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM proprietaire WHERE visible=0");
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

	public ArrayList<Proprietaire> getAllByIdAgent(int id) {
		ArrayList<Proprietaire> proprietaires = new ArrayList<Proprietaire>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement(
					"SELECT * FROM proprietaire WHERE id IN(SELECT id_proprietaire FROM bien WHERE id_agent=?) AND visible=0 ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Proprietaire proprietaire = new Proprietaire();
				proprietaire.setId(resultat.getInt("id"));
				proprietaire.setNom(resultat.getString("nom"));
				proprietaire.setPrenom(resultat.getString("prenom"));
				proprietaire.setAdresse(resultat.getString("adresse"));
				proprietaire.setVille(resultat.getString("ville"));
				proprietaire.setCp(resultat.getString("cp"));
				proprietaire.setPays(resultat.getString("pays"));
				proprietaire.setTel(resultat.getString("tel"));
				proprietaire.setNaissance(resultat.getString("naissance"));
				proprietaire.setMail(resultat.getString("mail"));
				proprietaire.setVisible(resultat.getInt("visible"));
				proprietaires.add(proprietaire);
			}
			return proprietaires;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Proprietaire> getByKeywordsAndByIdAgent(String keyword, int id) {
		ArrayList<Proprietaire> proprietaires = new ArrayList<Proprietaire>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement(
					"SELECT * FROM proprietaire WHERE (nom LIKE ? OR prenom LIKE ? OR ville LIKE ?) AND id IN(SELECT id_proprietaire FROM bien WHERE id_agent=?) AND visible=0");
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");
			ps.setInt(4, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Proprietaire proprietaire = new Proprietaire();
				proprietaire.setId(resultat.getInt("id"));
				proprietaire.setNom(resultat.getString("nom"));
				proprietaire.setPrenom(resultat.getString("prenom"));
				proprietaire.setAdresse(resultat.getString("adresse"));
				proprietaire.setVille(resultat.getString("ville"));
				proprietaire.setCp(resultat.getString("cp"));
				proprietaire.setPays(resultat.getString("pays"));
				proprietaire.setTel(resultat.getString("tel"));
				proprietaire.setNaissance(resultat.getString("naissance"));
				proprietaire.setMail(resultat.getString("mail"));
				proprietaire.setVisible(resultat.getInt("visible"));
				proprietaires.add(proprietaire);
			}
			return proprietaires;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;

		}
	}

}

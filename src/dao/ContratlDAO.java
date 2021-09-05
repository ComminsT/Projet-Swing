package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Contratl;
import entite.Database;
import entite.Proprietaire;

public class ContratlDAO {
	public ContratlDAO() {

	}

	public void save(Contratl contratl) {

		try {

			if (contratl.getId() != 0) {
				PreparedStatement ps = Database.connexion.prepareStatement(
						"UPDATE contratl set date=?, id_locataire=?, id_bien=?, datefin=? WHERE id=?");
				ps.setString(1, contratl.getDate());
				ps.setInt(2, contratl.getId_locataire());
				ps.setInt(3, contratl.getId_bien());
				ps.setString(4, contratl.getDatefin());
				ps.setInt(5, contratl.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion
						.prepareStatement("INSERT INTO contratl (date,id_locataire,id_bien) VALUES(?,?,?)");
				ps.setString(1, contratl.getDate());
				ps.setInt(2, contratl.getId_locataire());
				ps.setInt(3, contratl.getId_bien());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Contratl getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratl WHERE id=?");
			ps.setInt(1, id);

			ResultSet resultat = ps.executeQuery();

			Contratl cl = new Contratl();
			if (resultat.next()) {
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				cl.setDatefin(resultat.getString("datefin"));
			}
			return cl;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Contratl> getAll() {
		ArrayList<Contratl> contratls = new ArrayList<Contratl>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratl");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				cl.setDatefin(resultat.getString("datefin"));
				contratls.add(cl);
			}

			return contratls;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM contratl WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

	public ArrayList<Contratl> getAllByLocataireId(int id) {
		ArrayList<Contratl> contrats = new ArrayList<Contratl>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratl WHERE id_locataire=? ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setDatefin(resultat.getString("datefin"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				contrats.add(cl);
			}
			return contrats;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Contratl> getAllByIdAgentNOTFINISHED(int id) {
		ArrayList<Contratl> contrats = new ArrayList<Contratl>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement(
					"SELECT * FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND datefin IS NULL ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setDatefin(resultat.getString("datefin"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				contrats.add(cl);
			}
			return contrats;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Contratl> getAllByIdAgentFINISHED(int id) {
		ArrayList<Contratl> contrats = new ArrayList<Contratl>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement(
					"SELECT * FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND datefin IS NOT NULL ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setDatefin(resultat.getString("datefin"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				cl.setDatefin(resultat.getString("datefin"));
				contrats.add(cl);
			}
			return contrats;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Contratl> getAllByBienId(int id) {
		ArrayList<Contratl> contrats = new ArrayList<Contratl>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratl WHERE id_bien=? ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while (resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt("id"));
				cl.setDate(resultat.getString("date"));
				cl.setDatefin(resultat.getString("datefin"));
				cl.setId_locataire(resultat.getInt("id_locataire"));
				cl.setId_bien(resultat.getInt("id_bien"));
				cl.setDatefin(resultat.getString("datefin"));
				contrats.add(cl);
			}
			return contrats;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public ArrayList<Integer> getcontratCountByAgentId(int id) {
		ArrayList<Integer> result = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/01/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			int nbr=0;
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("1");
			}
			
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/02/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("2");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/03/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("3");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/04/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("4");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/05/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("5");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/06/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("6");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/07/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("7");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/08/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("8");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/09/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("9");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/10/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("10");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/11/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("11");
			}
			ps = Database.connexion.prepareStatement(
					"SELECT COUNT(*) FROM contratl WHERE id_bien IN(SELECT id FROM bien WHERE id_agent=?) AND DATE between '2021/01/01' AND '2021/12/31' AND datefin IS NULL  ");
			ps.setInt(1, id);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbr = resultat.getInt(1);
				result.add(nbr);
				System.out.println("12");
			}

			return result;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;

		}

	}
}

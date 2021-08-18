package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Contratp;
import entite.Database;

public class ContratpDAO {
	public ContratpDAO() {
		
	}

	public void save(Contratp contratp) {

		try {

			if (contratp.getId() != 0) {
				PreparedStatement ps = Database.connexion
						.prepareStatement("UPDATE contratp set date=?, id_bien=? WHERE id=?");
				ps.setString(1, contratp.getDate());
				ps.setInt(2, contratp.getId_bien());
				ps.setInt(3, contratp.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion
						.prepareStatement("INSERT INTO contratp (date,id_bien) VALUES(?,?)");
				ps.setString(1, contratp.getDate());
				ps.setInt(2, contratp.getId_bien());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Contratp getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratp WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();

			Contratp cp = new Contratp();
			if (resultat.next()) {
				cp.setId(resultat.getInt("id"));
				cp.setDate(resultat.getString("date"));
				cp.setId_bien(resultat.getInt("id_bien"));
			}
			return cp;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Contratp> getAll() {
		ArrayList<Contratp> contratps = new ArrayList<Contratp>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM contratp");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Contratp cp = new Contratp();
				cp.setId(resultat.getInt("id"));
				cp.setDate(resultat.getString("date"));
				cp.setId_bien(resultat.getInt("id_bien"));
				contratps.add(cp);
			}

			return contratps;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM contratp WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}

}

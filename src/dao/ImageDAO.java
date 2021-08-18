package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Image;
import entite.Database;

public class ImageDAO {
	public ImageDAO() {
		
	}

	public void save(Image image) {

		try {

			if (image.getId() != 0) {
				PreparedStatement ps = Database.connexion
						.prepareStatement("UPDATE Image set nom=?, id_bien=? WHERE id=?");
				ps.setString(1, image.getNom());
				ps.setInt(2, image.getId_bien());
				ps.setInt(3, image.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connexion
						.prepareStatement("INSERT INTO Image (nom,id_bien) VALUES(?,?)");
				ps.setString(1, image.getNom());
				ps.setInt(2, image.getId_bien());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	public Image getById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM Image WHERE id=?");
			ps.setInt(1, id);

			ResultSet resultat = ps.executeQuery();

			Image img = new Image();
			while (resultat.next()) {
				img.setId(resultat.getInt("id"));
				img.setNom(resultat.getString("nom"));
				img.setId_bien(resultat.getInt("id_bien"));
			}
			return img;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ArrayList<Image> getAll() {
		ArrayList<Image> images = new ArrayList<Image>();
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM image");

			ResultSet resultat = ps.executeQuery();

			while (resultat.next()) {
				Image img = new Image();
				img.setId(resultat.getInt("id"));
				img.setNom(resultat.getString("nom"));
				img.setId_bien(resultat.getInt("id_bien"));
				images.add(img);
			}

			return images;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM Image WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DELETED NO");
		}
	}
}

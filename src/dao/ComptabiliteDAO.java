package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Database;
import entite.Comptabilite;

public class ComptabiliteDAO {

	public ComptabiliteDAO() {

	}
	
	
public void save(Comptabilite comptabilite) {
		
		try {
			
			if(comptabilite.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE comptabilite set datedue=?, datepaye=?, categorie=?,"
						+ " montantdu=?, montantpaye=? id_contratl=? WHERE id=?");
				ps.setString(1,comptabilite.getDatedue());
				ps.setString(2,comptabilite.getDatepaye());
				ps.setString(3,comptabilite.getCategorie());
				ps.setDouble(4,comptabilite.getMontantdu());
				ps.setDouble(5,comptabilite.getMontantpaye());
				ps.setInt(6,comptabilite.getId_contratl());
				ps.setInt(7,comptabilite.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO comptabilite (datedue,datepaye,categorie,"
						+ "montantdu,montantpaye,id_contratl) VALUES(?,?,?,?,?,?)");
				ps.setString(1,comptabilite.getDatedue());
				ps.setString(2,comptabilite.getDatepaye());
				ps.setString(3,comptabilite.getCategorie());
				ps.setDouble(4,comptabilite.getMontantdu());
				ps.setDouble(5,comptabilite.getMontantpaye());
				ps.setInt(6,comptabilite.getId_contratl());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Comptabilite getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM comptabilite WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Comptabilite c = new Comptabilite();
			while(resultat.next()) {
				c.setId(resultat.getInt( "id" ));
				c.setDatedue(resultat.getString( "datedue" ));
				c.setDatepaye(resultat.getString( "datepaye" ));
				c.setCategorie(resultat.getString( "categorie" ));
				c.setMontantdu(resultat.getDouble( "montantdu" ));
				c.setMontantpaye(resultat.getDouble( "montantpaye" ));
				c.setId_contratl(resultat.getInt( "id_contratl" ));
			}
			return c;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Comptabilite> getAll() {
	ArrayList<Comptabilite> comptabilites = new ArrayList<Comptabilite>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM comptabilite");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Comptabilite c = new Comptabilite();
				c.setId(resultat.getInt( "id" ));
				c.setDatedue(resultat.getString( "datedue" ));
				c.setDatepaye(resultat.getString( "datepaye" ));
				c.setCategorie(resultat.getString( "categorie" ));
				c.setMontantdu(resultat.getDouble( "montantdu" ));
				c.setMontantpaye(resultat.getDouble( "montantpaye" ));
				c.setId_contratl(resultat.getInt( "id_contratl" ));
				comptabilites.add(c);
			}
			
			
			return comptabilites;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM comptabilite WHERE id=?");
			ps.setInt(1,id);
			
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}


}

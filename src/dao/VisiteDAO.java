package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Visite;
import entite.Database;

public class VisiteDAO {

	public VisiteDAO() {

	}
	
public void save(Visite visite) {
		
		try {
			
			if(visite.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE visite set date=?, nom=?, remarque=?, id_bien=? WHERE id=?");
				ps.setString(1,visite.getDate());
				ps.setString(2,visite.getNom());
				ps.setString(3,visite.getRemarque());
				ps.setInt(4,visite.getId_bien());
				ps.setInt(5,visite.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO visite (date,nom,remarque,id_bien) VALUES(?,?,?)");
				ps.setString(1,visite.getDate());
				ps.setString(2,visite.getNom());
				ps.setString(3,visite.getRemarque());
				ps.setInt(4,visite.getId_bien());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Visite getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM visite WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Visite v = new Visite();
			while(resultat.next()) {
				v.setId(resultat.getInt( "id" ));
				v.setDate(resultat.getString( "date" ));
				v.setNom(resultat.getString( "nom" ));
				v.setRemarque(resultat.getString( "remarque" ));
				v.setId_bien(resultat.getInt( "id_bien" ));
			}
			return v;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Visite> getAll() {
	ArrayList<Visite> visites = new ArrayList<Visite>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM visite");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Visite v = new Visite();
				v.setId(resultat.getInt( "id" ));
				v.setDate(resultat.getString( "date" ));
				v.setNom(resultat.getString( "nom" ));
				v.setRemarque(resultat.getString( "remarque" ));
				v.setId_bien(resultat.getInt( "id_bien" ));
				visites.add(v);
			}
			
			
			return visites;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM visite WHERE id=?");
			ps.setInt(1,id);
			
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}


}

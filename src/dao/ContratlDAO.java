package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Contratl;
import entite.Database;

public class ContratlDAO {
	public ContratlDAO() {
		
	}

	public void save(Contratl contratl) {
		
		try {
			
			if(contratl.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE contratl set date=?, id_locataire=?, id_bien=? WHERE id=?");
				ps.setString(1,contratl.getDate());
				ps.setInt(2,contratl.getId_locataire());
				ps.setInt(3,contratl.getId_bien());
				ps.setInt(4,contratl.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO contratl (date,id_locataire,id_bien) VALUES(?,?,?)");
				ps.setString(1,contratl.getDate());
				ps.setInt(2,contratl.getId_locataire());
				ps.setInt(3,contratl.getId_bien());
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
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM contratl WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Contratl cl = new Contratl();
			if(resultat.next()) {
				cl.setId(resultat.getInt( "id" ));
				cl.setDate(resultat.getString( "date" ));
				cl.setId_locataire(resultat.getInt( "id_locataire" ));
				cl.setId_bien(resultat.getInt( "id_bien" ));
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
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM contratl");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Contratl cl = new Contratl();
				cl.setId(resultat.getInt( "id" ));
				cl.setDate(resultat.getString( "date" ));
				cl.setId_locataire(resultat.getInt( "id_locataire" ));
				cl.setId_bien(resultat.getInt( "id_bien" ));
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
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM contratl WHERE id=?");
			ps.setInt(1,id);
			
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}

}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Historique;
import entite.Database;

public class HistoriqueDAO {

	public HistoriqueDAO() {

	}

public void save(Historique historique) {
		
		try {
			
			if(historique.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE historique set date=?, action=?, id_agent=? WHERE id=?");
				ps.setString(1,historique.getDate());
				ps.setString(2,historique.getAction());
				ps.setInt(3,historique.getId_agent());
				ps.setInt(4,historique.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO historique (date,action,id_agent) VALUES(?,?,?)");
				ps.setString(1,historique.getDate());
				ps.setString(2,historique.getAction());
				ps.setInt(3,historique.getId_agent());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Historique getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM historique WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Historique h = new Historique();
			while(resultat.next()) {
				h.setId(resultat.getInt( "id" ));
				h.setDate(resultat.getString( "date" ));
				h.setAction(resultat.getString( "action" ));
				h.setId_agent(resultat.getInt( "id_agent" ));
			}
			return h;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Historique> getAll() {
	ArrayList<Historique> historiques = new ArrayList<Historique>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM historique");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Historique h = new Historique();
				h.setId(resultat.getInt( "id" ));
				h.setDate(resultat.getString( "date" ));
				h.setAction(resultat.getString( "action" ));
				h.setId_agent(resultat.getInt( "id_agent" ));
				historiques.add(h);
			}
			
			
			return historiques;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM historique WHERE id=?");
			ps.setInt(1,id);
			
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}

public ArrayList<Historique> getAllByAgentId(int id) {
	ArrayList<Historique> historiques = new ArrayList<Historique>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM historique WHERE id_agent=?");
			ps.setInt(1, id);
			
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				Historique h = new Historique();
				h.setId(resultat.getInt( "id" ));
				h.setDate(resultat.getString( "date" ));
				h.setAction(resultat.getString( "action" ));
				h.setId_agent(resultat.getInt( "id_agent" ));
				historiques.add(h);
			}
			
			
			return historiques;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

}

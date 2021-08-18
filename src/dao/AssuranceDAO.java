package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Agent;
import entite.Assurance;
import entite.Database;

public class AssuranceDAO {
public AssuranceDAO() {
		
	}
	
	public void save(Assurance assurance) {
		
		try {
			
			if(assurance.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE assurance set type=?, numero=?,id_contratl=? WHERE id=?");
				ps.setString(1,assurance.getType());
				ps.setString(2,assurance.getNumero());
				ps.setInt(3,assurance.getId_contratl());
				ps.setInt(4,assurance.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO assurance (type,numero,id_contratl) VALUES(?,?,?)");
				ps.setString(1,assurance.getType());
				ps.setString(2,assurance.getNumero());
				ps.setInt(3,assurance.getId_contratl());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Assurance getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM assurance WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Assurance assurance = new Assurance();
			if(resultat.next()) {
				assurance.setId(resultat.getInt( "id" ));
				assurance.setType(resultat.getString( "type" ));
				assurance.setNumero(resultat.getString( "numero" ));
				assurance.setId_contratl(resultat.getInt( "id_contratl" ));
			}
			return assurance;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Agent> getAll() {
	ArrayList<Agent> agents = new ArrayList<Agent>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM agent");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Agent agent = new Agent();
				agent.setId(resultat.getInt( "id" ));
				agent.setNom(resultat.getString( "nom" ));
				agent.setPrenom(resultat.getString( "prenom" ));
				agent.setIdentifiant(resultat.getString( "identifiant" ));
				agent.setMdp(resultat.getString( "mdp" ));
				agent.setAdresse(resultat.getString( "adresse" ));
				agent.setCp(resultat.getString( "cp" ));
				agent.setVille(resultat.getString( "ville" ));
				agent.setDateentree(resultat.getString( "dateentree" ));
				agent.setStatut(resultat.getString( "statut" ));
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
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM agent WHERE id=?");
			ps.setInt(1,id);
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}

}

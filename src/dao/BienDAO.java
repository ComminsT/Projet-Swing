package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Bien;
import entite.Database;

public class BienDAO {
public BienDAO() {
		
	}
	
	public void save(Bien bien) {
		
		try {
			
			if(bien.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE bien set nom=?,adresse=?,cp=?,ville=?,pays=?,type=?,valeur=?,surface=?,statut=?,id_agent=?,id_proprietaire=?,annee=?,visible=? WHERE id=?");
				ps.setString(1,bien.getNom());
				ps.setString(2,bien.getAdresse());
				ps.setString(3,bien.getCp());
				ps.setString(4,bien.getVille());
				ps.setString(5,bien.getPays());
				ps.setString(6,bien.getType());
				ps.setDouble(7, bien.getValeur());
				ps.setDouble(8,bien.getSurface());
				ps.setString(9, bien.getStatut());
				ps.setInt(10,bien.getId_agent());
				ps.setInt(11,bien.getId_proprietaire());
				ps.setInt(12,bien.getAnnee());
				ps.setInt(13, bien.getVisible());
				ps.setInt(14, bien.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO bien (nom,adresse,cp,ville,pays,type,valeur,surface,statut,id_agent,id_proprietaire,annee,visible) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,bien.getNom());
				ps.setString(2,bien.getAdresse());
				ps.setString(3,bien.getCp());
				ps.setString(4,bien.getVille());
				ps.setString(5,bien.getPays());
				ps.setString(6,bien.getType());
				ps.setDouble(7, bien.getValeur());
				ps.setDouble(8,bien.getSurface());
				ps.setString(9, bien.getStatut());
				ps.setInt(10,bien.getId_agent());
				ps.setInt(11,bien.getId_proprietaire());
				ps.setInt(12,bien.getAnnee());
				ps.setInt(13,bien.getVisible());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Bien getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id=? AND visible=0");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Bien bien= new Bien();
			if(resultat.next()) {
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				
			}
			return bien;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Bien> getAll() {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE visible=0");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE bien SET visible=1 WHERE id=?");
			ps.setInt(1,id);
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}



public ArrayList<Bien> getAllByIdAgent(int id) {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id_agent=? and visible=0");
			ps.setInt(1, id);
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}
public ArrayList<Bien> getAllByProprietaireId(int id) {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id_proprietaire=? AND visible=0");
			ps.setInt(1, id);
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Bien> getByKeywordsAndByIdAgent(String keyword, int id) {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id_agent=? AND visible=0 AND (nom LIKE ? OR ville LIKE ? )");
			ps.setInt(1, id);
			ps.setString(2,"%" + keyword + "%");
			ps.setString(3,"%" + keyword + "%");
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}
public ArrayList<Bien> getByKeywordsAndByIdAgentLIBRE(String keyword, int id) {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id_agent=? AND visible=0 AND statut=\"En recherche de locataire\" AND (nom LIKE ? OR ville LIKE ?)");
			ps.setInt(1, id);
			ps.setString(2,"%" + keyword + "%");
			ps.setString(3,"%" + keyword + "%");
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public ArrayList<Bien> getAllByIdAgentLIBRE(int id) {
	ArrayList<Bien> biens = new ArrayList<Bien>();
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE id_agent=? AND visible=0 AND statut=\"En recherche de locataire\"");
			ps.setInt(1, id);
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Bien bien  = new Bien();
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				biens.add(bien);
			}
			return biens;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public Bien getByIdComptabilite(int id) {
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM bien WHERE visible=0 AND id IN(SELECT id_bien FROM contratl WHERE id IN(SELECT id_contratl FROM comptabilite WHERE id=?))");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Bien bien= new Bien();
			if(resultat.next()) {
				bien.setId(resultat.getInt( "id" ));
				bien.setNom(resultat.getString( "nom" ));
				bien.setAdresse(resultat.getString("adresse"));
				bien.setCp(resultat.getString("cp"));
				bien.setVille(resultat.getString("ville"));
				bien.setPays(resultat.getString("pays"));
				bien.setType(resultat.getString("type"));
				bien.setValeur(resultat.getDouble("valeur"));
				bien.setSurface(resultat.getDouble("surface"));
				bien.setStatut(resultat.getString("statut"));
				bien.setId_agent(resultat.getInt("id_agent"));
				bien.setId_proprietaire(resultat.getInt("id_proprietaire"));
				bien.setAnnee(resultat.getInt("annee"));
				bien.setVisible(resultat.getInt("visible"));
				
			}
			return bien;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


}

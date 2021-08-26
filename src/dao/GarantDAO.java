package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Database;
import entite.Garant;

public class GarantDAO {
public GarantDAO() {
		
	}
	
	public void save(Garant garant) {
		
		try {
			
			if(garant.getId() != 0) {
				PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE garant set nom=?,prenom=?,adresse=?,cp=?,ville=?,pays=?,tel=?,mail=?,id_contratl=? WHERE id=?");
				ps.setString(1,garant.getNom());
				ps.setString(2,garant.getPrenom());
				ps.setString(3,garant.getAdresse());
				ps.setString(4,garant.getCp());
				ps.setString(5,garant.getVille());
				ps.setString(6,garant.getPays());
				ps.setString(7,garant.getTel());
				ps.setString(8, garant.getMail());
				ps.setInt(9,garant.getId_contratl());
				ps.setInt(10,garant.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO garant (nom,prenom,adresse,cp,ville,pays,tel,mail,id_contratl) VALUES(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,garant.getNom());
				ps.setString(2,garant.getPrenom());
				ps.setString(3,garant.getAdresse());
				ps.setString(4,garant.getCp());
				ps.setString(5,garant.getVille());
				ps.setString(6,garant.getPays());
				ps.setString(7,garant.getTel());
				ps.setString(8, garant.getMail());
				ps.setInt(9,garant.getId_contratl());
				ps.executeUpdate();
			}
			System.out.println("SAVED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("SAVED NO");
        }
	
}

public Garant getById(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM garant WHERE id=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Garant garant = new Garant();
			if(resultat.next()) {
				garant.setId(resultat.getInt( "id" ));
				garant.setNom(resultat.getString( "nom" ));
				garant.setPrenom(resultat.getString( "prenom" ));
				garant.setAdresse(resultat.getString("adresse"));
				garant.setCp(resultat.getString("cp"));
				garant.setVille(resultat.getString("ville"));
				garant.setPays(resultat.getString("pays"));
				garant.setTel(resultat.getString("tel"));
				garant.setMail(resultat.getString("mail"));
				garant.setId_contratl(resultat.getInt("id_contratl"));
				
			}
			return garant;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}


public ArrayList<Garant> getAll() {
	ArrayList<Garant> garants = new ArrayList<Garant>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM garant");
			
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Garant garant  = new Garant();
				garant.setId(resultat.getInt( "id" ));
				garant.setNom(resultat.getString( "nom" ));
				garant.setPrenom(resultat.getString( "prenom" ));
				garant.setAdresse(resultat.getString("adresse"));
				garant.setCp(resultat.getString("cp"));
				garant.setVille(resultat.getString("ville"));
				garant.setPays(resultat.getString("pays"));
				garant.setTel(resultat.getString("tel"));
				garant.setMail(resultat.getString("mail"));
				garant.setId_contratl(resultat.getInt("id_contratl"));
				garants.add(garant);
			}
			return garants;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void deleteById(int id) {
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM garant WHERE id=?");
			ps.setInt(1,id);
			ps.executeUpdate();
			
			System.out.println("DELETED OK");
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("DELETED NO");
    }
}

public Garant getByBienId(int id) {
	try {
	
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM garant WHERE id_bien=?");
			ps.setInt(1,id);
			
			ResultSet resultat=ps.executeQuery();
			
			Garant garant = new Garant();
			if(resultat.next()) {
				garant.setId(resultat.getInt( "id" ));
				garant.setNom(resultat.getString( "nom" ));
				garant.setPrenom(resultat.getString( "prenom" ));
				garant.setAdresse(resultat.getString("adresse"));
				garant.setCp(resultat.getString("cp"));
				garant.setVille(resultat.getString("ville"));
				garant.setPays(resultat.getString("pays"));
				garant.setTel(resultat.getString("tel"));
				garant.setMail(resultat.getString("mail"));
				garant.setId_contratl(resultat.getInt("id_contratl"));
				
			}
			return garant;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

public void saveWithoutBien(Garant garant) {
	
	try {
			PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO garant (nom,prenom,adresse,cp,ville,pays,tel,mail) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,garant.getNom());
			ps.setString(2,garant.getPrenom());
			ps.setString(3,garant.getAdresse());
			ps.setString(4,garant.getCp());
			ps.setString(5,garant.getVille());
			ps.setString(6,garant.getPays());
			ps.setString(7,garant.getTel());
			ps.setString(8, garant.getMail());
			ps.executeUpdate();
		System.out.println("SAVED OK");
	} catch (Exception ex) {
    	ex.printStackTrace();
    	System.out.println("SAVED NO");
    }
	
	
}

public ArrayList<Garant> getAllByContratId(int id) {
	ArrayList<Garant> garants = new ArrayList<Garant>();
	try {
		
			PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM garant WHERE id_contratl=?");
			ps.setInt(1, id);
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Garant garant  = new Garant();
				garant.setId(resultat.getInt( "id" ));
				garant.setNom(resultat.getString( "nom" ));
				garant.setPrenom(resultat.getString( "prenom" ));
				garant.setAdresse(resultat.getString("adresse"));
				garant.setCp(resultat.getString("cp"));
				garant.setVille(resultat.getString("ville"));
				garant.setPays(resultat.getString("pays"));
				garant.setTel(resultat.getString("tel"));
				garant.setMail(resultat.getString("mail"));
				garant.setId_contratl(resultat.getInt("id_contratl"));
				garants.add(garant);
			}
			return garants;
		
	} catch (Exception ex) {
    	ex.printStackTrace();
    	return null;
    }
}

}

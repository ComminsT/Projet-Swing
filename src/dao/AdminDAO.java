package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entite.Admin;
import entite.Database;

public class AdminDAO {
	public AdminDAO() {
		
	}

		
		public void save(Admin admin) {
			
			try {
				
				if(admin.getId() != 0) {
					PreparedStatement ps  = Database.connexion.prepareStatement("UPDATE admin set identifiant=?, mdp=? WHERE id=?");
					ps.setString(1,admin.getIdentifiant());
					ps.setString(2,admin.getMdp());
					ps.setInt(3,admin.getId());
					ps.executeUpdate();
				}else {
					PreparedStatement ps  = Database.connexion.prepareStatement("INSERT INTO users (identifiant,mdp) VALUES(?,?)");
					ps.setString(1,admin.getIdentifiant());
					ps.setString(2,admin.getMdp());
					ps.executeUpdate();
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
		
	}
	
	public Admin getById(int id) {
		try {
		
				PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM admin WHERE id=?");
				ps.setInt(1,id);
				
				ResultSet resultat=ps.executeQuery();
				
				Admin a = new Admin();
				while(resultat.next()) {
					a.setId(resultat.getInt( "id" ));
					a.setIdentifiant(resultat.getString( "identifiant" ));
					a.setMdp(resultat.getString( "mdp" ));
				}
				return a;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	
	public ArrayList<Admin> getAll() {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		try {
			
				PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM admin");
				
				ResultSet resultat=ps.executeQuery();

				while(resultat.next()) {
					Admin a = new Admin();
					a.setId(resultat.getInt( "id" ));
					a.setIdentifiant(resultat.getString( "identifiant" ));
					a.setMdp(resultat.getString( "mdp" ));
					admins.add(a);
				}
				
				
				return admins;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement ps  = Database.connexion.prepareStatement("DELETE FROM admin WHERE id=?");
				ps.setInt(1,id);
				
				ps.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
	public ArrayList<Admin> getByIdentifiant(String identifiant, String mdp) {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		try {
				PreparedStatement ps  = Database.connexion.prepareStatement("SELECT * FROM admin WHERE identifiant = ? AND mdp=? ");
				ps.setString(0, identifiant);
				ps.setString(1, mdp);
				ResultSet resultat=ps.executeQuery();
				while(resultat.next()) {
					Admin a = new Admin();
					a.setId(resultat.getInt( "id" ));
					a.setIdentifiant(resultat.getString( "identifiant" ));
					a.setMdp(resultat.getString( "mdp" ));
					admins.add(a);
				}
				
				
				return admins;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}

}

package entite;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static String dburl="jdbc:mysql://localhost:8889/exoswing";
	private static String dbuser="root";
	private static String dbpass="root";
	public static Connection connexion=null;
	
	public static void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion=DriverManager.getConnection(dburl,dbuser,dbpass);
		} catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}

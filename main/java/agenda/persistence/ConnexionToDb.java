package agenda.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionToDb {
	
	private static Connection connexion;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion= DriverManager.getConnection("jdbc:mysql://localhost:3308/agenda_db","root","admin");
			//System.out.print("Connexion r�ussit");
		} catch (Exception e) {
			//System.out.print("Connexion �chou�");
			e.printStackTrace();
		}	
	}

	public static Connection getConnexion() {
		return connexion;
	}
}

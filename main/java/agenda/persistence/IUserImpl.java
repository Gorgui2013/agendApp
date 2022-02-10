package agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agenda.auth.Authentification;
import agenda.model.Event;
import agenda.model.User;

public class IUserImpl implements IUser {
    Connection conn = ConnexionToDb.getConnexion();

	@Override
	public User ajouter(User u) {
		Authentification auth = new Authentification();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into user(nom, prenom, nomUtilisateur, motDePasse) values(?, ?, ?, ?)");
			ps.setString(1, u.getNom());
			ps.setString(2, u.getPrenom());
			ps.setString(3, u.getNomUtilisateur());
			ps.setString(4, auth.encodeMotDePasse(u.getMotDePasse()));
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setIdUser(Integer.parseInt(rs.getString("idUser")));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setNomUtilisateur(rs.getString("nomUtilisateur"));
				users.add(u);								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User obtenir(int id) {
		User u = new User();
		try {
			PreparedStatement ps = conn.prepareStatement("select idUser, nom, prenom, nomUtilisateur, motDePasse from user where idUser = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setIdUser(rs.getInt("idUser"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setNomUtilisateur(rs.getString("nomUtilisateur"));
				u.setMotDePasse(rs.getString("motDePasse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User obtenirParInfo(String nom, String pass) {
		User u = new User();
		try {
			PreparedStatement ps = conn.prepareStatement("select idUser, nom, prenom, nomUtilisateur, motDePasse from user where nomUtilisateur = ? and motDePasse = ?");
			ps.setString(1, nom);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setIdUser(rs.getInt("idUser"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setNomUtilisateur(rs.getString("nomUtilisateur"));
				u.setMotDePasse(rs.getString("motDePasse"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User modification(int id, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> listUserEvents(User u) {
		List<Event> events = new ArrayList<Event>();
		IEvent ev = new IEventImpl();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from event_users where user = ?");
			ps.setInt(1, u.getIdUser());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Event p = ev.obtenir(rs.getInt("event"));
				events.add(p);								
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
}

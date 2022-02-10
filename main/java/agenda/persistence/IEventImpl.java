package agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agenda.model.Event;
import agenda.model.User;

public class IEventImpl implements IEvent {

	Connection conn = ConnexionToDb.getConnexion();


	@Override
	public List<Event> listEvents() {
		List<Event> events = new ArrayList<Event>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from event");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Event p = new Event();
				p.setNom(rs.getString("nom"));
				p.setIdEvent(Integer.parseInt(rs.getString("idEvent")));
				events.add(p);								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public int tailleEvents() {
		List<Event> events = new ArrayList<Event>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from event");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Event p = new Event();
				p.setNom(rs.getString("nom"));
				p.setIdEvent(Integer.parseInt(rs.getString("idEvent")));
				events.add(p);								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events.get(events.size()-1).getIdEvent();
	}

	@Override
	public Event ajouter(Event event) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into event(nom, dateHeureDebut, dateHeureFin, lieu, category, priorite, status) values(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, event.getNom());
			ps.setString(2, event.getDateHeureDebut());
			ps.setString(3, event.getDateHeureFin());
			ps.setString(4, event.getLieu());
			ps.setInt(5, event.getCategory());
			ps.setInt(6, event.getPriorite());
			ps.setBoolean(7, event.isStatus());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public void ajouterWithUser(Event event, User user) {
		try {
			this.ajouter(event);
			PreparedStatement ps = conn.prepareStatement("insert into event_users(event, user) values(?, ?)");
			ps.setInt(1, this.tailleEvents());
			ps.setInt(2, user.getIdUser());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		//return event;
	}

	@Override
	public void ajouterUserInEvent(Event event, User user) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into event_users(event, user) values(?, ?)");
			ps.setInt(1, event.getIdEvent());
			ps.setInt(2, user.getIdUser());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Event obtenir(int id) {
		Event u = new Event();
		try {
			PreparedStatement ps = conn.prepareStatement("select idEvent, nom , dateHeureDebut, dateHeureFin, lieu, category, priorite, status from event where idEvent = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setIdEvent(rs.getInt("idEvent"));
				u.setNom(rs.getString("nom"));
				u.setDateHeureDebut(rs.getString("dateHeureDebut"));
				u.setDateHeureFin(rs.getString("dateHeureFin"));
				u.setLieu(rs.getString("lieu"));
				u.setCategory(rs.getInt("category"));
				u.setPriorite(rs.getInt("priorite"));
				u.setStatus(rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Event modification(int id, Event event) {
		try {
			PreparedStatement ps = conn.prepareStatement("update event set nom=?, dateHeureDebut=?, dateHeureFin=?, lieu=?, category=?, priorite=?, status=? where idEvent = ?");
			ps.setString(1, event.getNom());
			ps.setString(2, event.getDateHeureDebut());
			ps.setString(3, event.getDateHeureFin());
			ps.setString(4, event.getLieu());
			ps.setInt(5, event.getCategory());
			ps.setInt(6, event.getPriorite());
			ps.setBoolean(7, event.isStatus());
			ps.setInt(8, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public void supprimer(int id) {
		try {
			PreparedStatement ps = conn.prepareStatement("delete from event where idEvent = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void supprimerWithUserLink(int id) {
		this.supprimer(id);
		try {
			PreparedStatement ps = conn.prepareStatement("delete from event_users where event = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void supprimerUserInEvent(Event event, User user) {
		try {
			PreparedStatement ps = conn.prepareStatement("delete from event_users where user = ? and event = ?");
			ps.setInt(1, user.getIdUser());
			ps.setInt(2, event.getIdEvent());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> listEventUsers(Event event) {
		List<User> users = new ArrayList<User>();
		IUser ui = new IUserImpl();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from event_users where event = ?");
			ps.setInt(1, event.getIdEvent());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = ui.obtenir(rs.getInt("user"));
				users.add(u);								
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> listEventNoUsers(Event event) {
		List<User> users = new ArrayList<User>();
		IUser ui = new IUserImpl();
		try {
			PreparedStatement ps = conn.prepareStatement("(select idUser from user where idUser not in (select DISTINCT(e.user) from event_users e)) union (select user from event_users where event != ?)");
			ps.setInt(1, event.getIdEvent());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = ui.obtenir(rs.getInt("idUser"));
				users.add(u);								
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}

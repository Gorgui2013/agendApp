package agenda.persistence;

import java.util.List;

import agenda.model.Event;
import agenda.model.User;

public interface IEvent {
    public Event ajouter(Event e);
    public List<Event> listEvents();
    public Event obtenir(int id);
    public Event modification(int id, Event e);
    public void supprimer(int id);
	public void ajouterWithUser(Event e, User u);
	public void supprimerWithUserLink(int id);
	List<User> listEventUsers(Event event);
	void supprimerUserInEvent(Event event, User user);
	void ajouterUserInEvent(Event event, User user);
	List<User> listEventNoUsers(Event event);
	int tailleEvents();
    
}

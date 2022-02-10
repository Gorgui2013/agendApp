package agenda.persistence;

import java.util.List;

import agenda.model.Event;
import agenda.model.User;

public interface IUser {
    public User ajouter(User u);
    public List<User> listUsers();
    public List<Event> listUserEvents(User u);
    public User obtenir(int id);
    public User obtenirParInfo(String nom, String pass);
    public User modification(int id, User u);
    public void supprimer(int id);
}

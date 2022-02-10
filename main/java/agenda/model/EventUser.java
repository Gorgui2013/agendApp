package agenda.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EventUser implements Serializable {

	private int idUserEvent;
	
	private Event event;
	
	private User user;
	
	public EventUser() {
		super();
	}
	
	public EventUser(Event v, User u) {
		super();
		this.event = new Event(v);
		this.user = new User(u);
	}
	
	public EventUser(EventUser p) {
		super();
		this.event = p.getEvent();
		this.user = p.getUser();
	}

	public int getIdUserEvent() {
		return idUserEvent;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

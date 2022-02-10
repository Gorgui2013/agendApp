package agenda.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import agenda.auth.Authentification;
import agenda.model.Category;
import agenda.model.Event;
import agenda.model.Jour;
import agenda.model.User;
import agenda.persistence.ICategory;
import agenda.persistence.ICategoryImpl;
import agenda.persistence.IEvent;
import agenda.persistence.IEventImpl;
import agenda.persistence.IUser;
import agenda.persistence.IUserImpl;

/**
 * Servlet implementation class Agenda
 */
@WebServlet(name="agenda.path", urlPatterns= {"/", "*.path"})
public class Agenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String path;
	IUser persistenceUser;
	ICategory persistenceCategory;
	IEvent persistenceEvent;
	User u = new User();
	List<User> users;
	List<User> noUsers;
	List<Event> events;
	List<Event> eventsDay;
	List<Category> categories;
	Authentification auth;
	String message = null;
	String nomUtilisateur;
	String motDePasse;
	Jour[][] tab;
	int days;
	String month;
	Date date;

	String[] mois = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Agenda() {
		super();
		this.auth = new Authentification();
		this.persistenceUser = new IUserImpl();
		this.persistenceCategory = new ICategoryImpl();
		this.persistenceEvent = new IEventImpl();
		this.nomUtilisateur = "";
		this.motDePasse = "";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.path = request.getServletPath();
		request.setAttribute("path", path);
		HttpSession session = request.getSession();
		if(session.getAttribute("connected") != null) {
			this.u = (User) session.getAttribute("user");
			if(this.path.equals("/agenda.path")) {
				this.events =  this.persistenceUser.listUserEvents(this.u);
				request.setAttribute("events", this.events);
				if(request.getParameter("month")!=null && request.getParameter("jour") == null) {
					this.date.setMonth(Integer.parseInt(request.getParameter("month")));
					this.month = this.mois[date.getMonth()];
					this.days = this.getDays(date.getMonth());
					//this.eventsDay = this.eventsDay(this.date.getDay(), this.date.getMonth(), this.events);
					//request.setAttribute("eventsDay", this.eventsDay);
					this.tab = this.getCalendrier(this.date.getYear(), this.date.getMonth(), this.events);
				} else if(request.getParameter("month")!=null && request.getParameter("jour")!=null) {
					this.date.setMonth(Integer.parseInt(request.getParameter("month")));
					this.month = this.mois[date.getMonth()];
					this.days = this.getDays(date.getMonth());
					try {
						this.eventsDay = this.eventsDay(Integer.parseInt(request.getParameter("jour")), this.date.getMonth(), this.events);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					request.setAttribute("eventsDay", this.eventsDay);
				} else {
					this.date = new Date(Calendar.getInstance().getTime().getTime());
					this.month = this.mois[date.getMonth()];
					this.days = this.getDays(this.date.getMonth());
					//this.eventsDay = this.eventsDay(this.date.getDay(), this.date.getMonth(), this.events);
					this.tab = this.getCalendrier(this.date.getYear(), this.date.getMonth(), this.events);
				}
				request.setAttribute("date", this.date);
				request.setAttribute("month", this.month);
				request.setAttribute("days", this.days);
				request.setAttribute("tab", this.tab);
				request.getRequestDispatcher("agenda/home.jsp").forward(request, response);
			} else if(this.path.equals("/event.path")) {
				if(request.getParameter("editEvent") != null) {
					Event e = this.persistenceEvent.obtenir(Integer.parseInt(request.getParameter("editEvent")));
					request.setAttribute("event", e);
					//this.users.remove(this.u);
					if(request.getParameter("removeUser") != null) {
						User user0 = this.persistenceUser.obtenir(Integer.parseInt(request.getParameter("removeUser")));
						this.persistenceEvent.supprimerUserInEvent(e, user0);
						this.message = "Supression faite avec succé";
						request.setAttribute("message", this.message);
					} else if(request.getParameter("addUser") != null) {
						User user1 = this.persistenceUser.obtenir(Integer.parseInt(request.getParameter("addUser")));
						this.persistenceEvent.ajouterUserInEvent(e, user1);
						this.message = "Ajout faite avec succé";
						request.setAttribute("message", this.message);
					}
					this.users = this.persistenceEvent.listEventUsers(e);
					this.filterListUser(users, u);
					this.noUsers = this.persistenceEvent.listEventNoUsers(e);
					this.filterListUser(noUsers, u);
					this.filterListUserInCurrentEvent(noUsers, users);
					//this.filterListOccurenceUser(users, noUsers);
					request.setAttribute("noUsers", this.noUsers);
					request.setAttribute("users", this.users);
				} else if(request.getParameter("deleteEvent") != null) {
					//Event e = this.persistenceEvent.obtenir(Integer.parseInt(request.getParameter("editEvent")));
					this.persistenceEvent.supprimerWithUserLink(Integer.parseInt(request.getParameter("deleteEvent")));
					//this.filterListOccurenceUser(noUsers, users);
					this.message = "Supression faite avec succé";
					request.setAttribute("message", this.message);
				}  else {
					Event e = new Event();
					request.setAttribute("event", e);
				}
				this.categories = this.persistenceCategory.listCategories();
				request.setAttribute("categories", this.categories);	
				//request.setAttribute("user", this.u);
				//this.events =  this.persistenceUser.listUserEvents(this.u);
				//request.setAttribute("events", this.events);
				request.getRequestDispatcher("agenda/home.jsp").forward(request, response);
			} else if(this.path.equals("/category.path")) {
				if(request.getParameter("idCategory") != null) {
					this.persistenceCategory.supprimer(Integer.parseInt(request.getParameter("idCategory")));
					this.message = "Suppression faite avec succé";
					request.setAttribute("message", this.message);
				}
				this.categories = this.persistenceCategory.listCategories();
				request.setAttribute("categories", this.categories);
				//request.setAttribute("user", this.u);
				//this.events =  this.persistenceUser.listUserEvents(this.u);
				//request.setAttribute("events", this.events);
				request.getRequestDispatcher("agenda/home.jsp").forward(request, response);
			}
		} else {
			//request.getRequestDispatcher("index.jsp");
			response.sendRedirect("/agendApp");
		}
	}

	public void filterListUser(List<User> users, User user) {
		for(int i = 0;i < users.size();i++) {
			if(user.getIdUser() == users.get(i).getIdUser()) {
				users.remove(i);
			}
		}
	}
	
	public void filterListUserInCurrentEvent(List<User> users1, List<User> users2) {
		for(int i = 0;i < users2.size();i++) {
			this.filterListUser(users1, users2.get(i));
			/*for(int j = 0;j < users1.size();j++) {
				if(users1.get(i).getIdUser() == users2.get(i).getIdUser()) {
					users2.remove(i);
				}
			}*/
		}
	}

	protected List<Event> eventsDay(int day, int month, List<Event> events) throws ParseException {
		List<Event> evDay = new ArrayList<Event>();
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).getMonthDebut() == month && events.get(i).getDayDebut() == day) {
				evDay.add(events.get(i));
			}
		}
		return evDay;
	}

	protected int getDays(int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	protected String getNameOfDay(int numberOfDay){
		String day = null;
		switch (numberOfDay) {
		case Calendar.MONDAY:
			day="Lundi";
			break;
		case Calendar.TUESDAY:
			day="Mardi";
			break;
		case Calendar.WEDNESDAY:
			day="Mercredi";
			break;
		case Calendar.THURSDAY:
			day="Jeudi";
			break;
		case Calendar.FRIDAY:
			day="Vendredi";
			break;
		case Calendar.SATURDAY:
			day="Samedi";
			break;
		case Calendar.SUNDAY:
			day="Dimanche";
			break;

		default:
			break;
		}
		return day;
	}

	protected Jour[][] getCalendrier(int year, int month, List<Event> events) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1900+year);
		cal.set(Calendar.MONTH, month);
		//int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK)-1;
		String firstDayName = getNameOfDay(cal.get(Calendar.DAY_OF_WEEK));
		int pas;
		if(firstDayOfMonth == 0) {
			firstDayOfMonth = 7;
		}
		if(firstDayName.equals("Samedi") || firstDayName.equals("Dimanche")) {
			pas = this.days/7+2;
		} else {
			pas = this.days/7+1;
		}
		Jour[][] tab = new Jour[pas][7];
		int d = 1;
		for(int i = 0; i < pas; i++) {
			for(int j = 0; j < 7; j++) {
				if(i == 0 && j < firstDayOfMonth - 1) {
					tab[i][j] = new Jour();
					tab[i][j].setNumJour(0);
					tab[i][j].setHasEvent(false);
				} else {
					tab[i][j] = new Jour();
					tab[i][j].setNumJour(d);
					try {
						tab[i][j].setHasEvent(this.existEvent(month, tab[i][j].getNumJour(), events));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					d++;
				}
				if( d >= this.days+2 ) {
					tab[i][j] = new Jour();
					tab[i][j].setNumJour(0);
					tab[i][j].setHasEvent(false);
				}
			}
		}
		return tab;
	}

	protected boolean existEvent(int month, int day, List<Event> events) throws ParseException {
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).getMonthDebut() == month && events.get(i).getDayDebut() == day) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.path = request.getServletPath();
		HttpSession session = request.getSession();
		if(this.path.equals("/agenda.path")) {
			this.nomUtilisateur = request.getParameter("nomUtilisateur");
			this.motDePasse = request.getParameter("motDePasse");
			this.u = this.auth.Authentifier(this.nomUtilisateur, auth.encodeMotDePasse(this.motDePasse));
			if(this.u != null) {
				session = request.getSession(true);
				session.setAttribute("user", this.u);
				session.setAttribute("connected", true);
				this.events =  this.persistenceUser.listUserEvents(this.u);
				request.setAttribute("events", this.events);
				this.date = new Date(Calendar.getInstance().getTime().getTime());
				this.month = this.mois[date.getMonth()];
				this.days = this.getDays(this.date.getMonth());
				this.tab = this.getCalendrier(this.date.getYear(), this.date.getMonth(), this.events);
				//this.eventsDay = this.eventsDay(this.date.getDay(), this.date.getMonth(), this.events);
				request.setAttribute("date", this.date);
				request.setAttribute("month", this.month);
				request.setAttribute("days", this.days);
				request.setAttribute("tab", this.tab);
				//request.setAttribute("eventsDay", this.eventsDay);
				request.getRequestDispatcher("agenda/home.jsp").forward(request, response);
			} else {
				//HttpSession session = request.getSession();
				session.invalidate();
				this.message = "Nom d'utililsateur ou mot de passe incorrect.";
				request.setAttribute("message", this.message);
				doGet(request, response);
			}
		} else if (this.path.equals("/event.path")) {
			if(request.getParameter("editEvent") != null) {
				Event e = this.persistenceEvent.obtenir(Integer.parseInt(request.getParameter("editEvent")));
				//System.out.println(e.getIdEvent());
				e.setNom(request.getParameter("nom"));
				e.setDateHeureDebut(request.getParameter("dateHeureDebut"));
				e.setDateHeureFin(request.getParameter("dateHeureFin"));
				e.setLieu(request.getParameter("lieu"));
				e.setCategory(Integer.parseInt(request.getParameter("category")));
				e.setPriorite(Integer.parseInt(request.getParameter("priorite")));
				e.setStatus(false);
				this.persistenceEvent.modification(e.getIdEvent(), e);
				this.message = "Modification faite avec succé";
				request.setAttribute("event", e);
			} else {
				Event e = new Event();
				e.setNom(request.getParameter("nom"));
				e.setDateHeureDebut(request.getParameter("dateHeureDebut"));
				e.setDateHeureFin(request.getParameter("dateHeureFin"));
				e.setLieu(request.getParameter("lieu"));
				e.setCategory(Integer.parseInt(request.getParameter("category")));
				e.setPriorite(Integer.parseInt(request.getParameter("priorite")));
				e.setStatus(false);
				this.persistenceEvent.ajouterWithUser(e, this.u);
				this.message = "Ajout faite avec succé";
				e.setIdEvent(this.persistenceEvent.tailleEvents());
				request.setAttribute("event", e);
			}
			request.setAttribute("message", this.message);
			doGet(request, response);
		} else if (this.path.equals("/category.path")) {
			Category c = new Category();
			c.setNom(request.getParameter("nom"));
			this.persistenceCategory.ajouter(c);
			this.message = "Ajout faite avec succé";
			request.setAttribute("message", this.message);
			doGet(request, response);
		}else if (this.path.equals("/register.path")) {
			User newUser = new User();
			newUser.setNom(request.getParameter("nom"));
			newUser.setPrenom(request.getParameter("prenom"));
			newUser.setNomUtilisateur(request.getParameter("nomUtilisateur"));
			newUser.setMotDePasse(request.getParameter("motDePasse"));
			this.persistenceUser.ajouter(newUser);
			doGet(request, response);
		} else if (this.path.equals("/logout.path")) {
			//HttpSession session = request.getSession();
			session.invalidate();
			//request.setAttribute("message", this.message);
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			response.sendRedirect("/agendApp");
			//doGet(request, response);
		}
	}

}

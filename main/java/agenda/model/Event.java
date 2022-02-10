package agenda.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Event implements Serializable {
	private int idEvent;
	
	private String nom;
	
	private String dateHeureDebut;
	
	private String dateHeureFin;
	
	private String lieu;
	
	private int category;
	
	private int priorite;
	
	private boolean status;

	public Event() {
		super();
	}
	
	public Event(String n, String dhd, String dhf, String l, int c, int p) {
		super();
		this.nom = n;
		this.dateHeureDebut = dhd;
		this.dateHeureFin = dhf;
		this.lieu = l;
		this.category = c;
		this.priorite = p;
		this.status = false;
	}

	public Event(Event e) {
		super();
		this.nom = e.getNom();
		this.dateHeureDebut = e.getDateHeureDebut();
		this.dateHeureFin = e.getDateHeureFin();
		this.lieu = e.getLieu();
		this.category = e.getCategory();
		this.priorite = e.getPriorite();
		this.status = e.isStatus();
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int id) {
		this.idEvent = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(String date) {
		this.dateHeureDebut = date;
	}

	public String getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(String dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getMonthDebut() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureDebut.replace("T", " "));
		return d.getMonth();
	}
	
	public int getDayDebut() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureDebut.replace("T", " "));
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(d);
	    return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getHoursDebut() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureDebut.replace("T", " "));
		return d.getHours();
	}

	public int getMinutesDebut() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureDebut.replace("T", " "));
		return d.getMinutes();
	}
	public int getHoursFin() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureFin.replace("T", " "));
		return d.getHours();
	}

	public int getMinutesFin() throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.dateHeureFin.replace("T", " "));
		return d.getMinutes();
	}

}

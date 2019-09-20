package tk.mitsky.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@Column(name = "event_date")
	private Date eventDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Club club;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(long id, String name, Date eventDate) {
		super();
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
	}

	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}

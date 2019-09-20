package tk.mitsky.web.model;


import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Timestamp date;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
	private Club club;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(long id, Timestamp date, Club club, User user) {
		super();
		this.id = id;
		this.date = date;
		this.club = club;
		this.user = user;
	}

	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

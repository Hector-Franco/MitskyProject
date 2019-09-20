package tk.mitsky.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "club")
public class Club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String address;
	
	@Column(name = "kind_music")
	private String kindOfMusic;
	
	private double rate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "favorite_id")
	private Favorite favorite;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Booking booking;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
	private Event event;


	public Club() {
		
	}	
	
	public Club(long id, String name, String address, String kindOfMusic, double rate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.kindOfMusic = kindOfMusic;
	}
	
	public Club(long id, String name, String address, String kindOfMusic, double rate, Favorite favorite) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.kindOfMusic = kindOfMusic;
		this.rate = rate;
		this.favorite = favorite;
	}

	
	
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKindOfMusic() {
		return kindOfMusic;
	}

	public void setKindOfMusic(String kindOfMusic) {
		this.kindOfMusic = kindOfMusic;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}	

}

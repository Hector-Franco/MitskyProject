package tk.mitsky.web.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user", indexes = @Index(name = "user_index", columnList = "email", unique = true))

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String email;

	@Column
	private String name;

	@Column
	private String lastname;

	@Column
	private Date birthdate;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	@OrderBy("creationInstant DESC")
	private List<Password> passwords;

	@JsonIgnore
	@JoinColumn(name = "billing_id")
	@OneToOne(mappedBy = "user")
	private Billing billing;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
	private Favorite favorite;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Booking> bookings;
	

	public User() {

	}
	
	public User(long id, String email) {
		this.id = id;
		this.email = email;
	}

	public User(long id, String email, String name, String lastname, Date birthdate, List<Password> passwords) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.passwords = passwords;
	}

	public User(long id, String email, String name, String lastname, Date birthdate, List<Password> passwords,
			Billing billing) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.passwords = passwords;
		this.billing = billing;
	}

	
	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Password> getPasswords() {
		return passwords;
	}

	public void setPasswords(List<Password> passwords) {
		this.passwords = passwords;
	}

	public User(Long id, String name, String lastname) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}

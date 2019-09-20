package tk.mitsky.web.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tk.mitsky.web.model.User;

@Entity
@Table(name="password")
public class Password {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@Column
	private String password;
	
	@Column
	private String salt;
	
	@Column(name = "creation_instant")
	private Instant creationInstant;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Password() {
		
	}
	
	public Password(long id, String password) {
		this.id = id;
		this.password = password;
	}

	public Password(long id, String password, User user) {
		this.id = id;
		this.password = password;
		this.user = user;
	}
	

	public Password(long id, String password, String salt, Instant creationInstant, User user) {
		super();
		this.id = id;
		this.password = password;
		this.salt = salt;
		this.creationInstant = creationInstant;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreationInstant() {
		return creationInstant;
	}

	public void setCreationInstant(Instant creationInstant) {
		this.creationInstant = creationInstant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	
	
	
	
	
}

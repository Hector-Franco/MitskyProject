package tk.mitsky.web.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "favorite")
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "favorite", cascade = { CascadeType.ALL })
	private List<Club> clubs;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	public Favorite() {
	}
	
	public Favorite(long id, List<Club> clubs) {
		super();
		this.id = id;
		this.clubs = clubs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	
	
	
	
}

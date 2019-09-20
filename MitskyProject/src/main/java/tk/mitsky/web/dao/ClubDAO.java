package tk.mitsky.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.mitsky.web.model.Club;

@Repository
public interface ClubDAO extends JpaRepository<Club, Long> {

	Club findByid(long id); 
	Club findByName(String name);
}

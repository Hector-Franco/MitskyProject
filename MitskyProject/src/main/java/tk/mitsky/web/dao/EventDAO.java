package tk.mitsky.web.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.mitsky.web.model.Event;

@Repository
public interface EventDAO extends JpaRepository<Event, Long>{
	
	Event findByid(long id );
	Event findByEventDate(Date eventDate);
	Event findByName(String name);

}

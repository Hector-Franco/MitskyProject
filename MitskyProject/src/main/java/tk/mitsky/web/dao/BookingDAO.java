package tk.mitsky.web.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.mitsky.web.model.Booking;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {

	Booking findByid(long id); 
	Booking findByDate(Timestamp date);
}

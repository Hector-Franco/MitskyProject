package tk.mitsky.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.mitsky.web.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	User findByEmail(String email);

}

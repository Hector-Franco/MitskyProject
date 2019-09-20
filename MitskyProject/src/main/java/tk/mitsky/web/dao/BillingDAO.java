package tk.mitsky.web.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.mitsky.web.model.Billing;

@Repository
public interface BillingDAO extends JpaRepository<Billing, Long> {
	
	Billing findById(long id);
	Billing findByCurrentPeriod(Date currentPeriod);
}

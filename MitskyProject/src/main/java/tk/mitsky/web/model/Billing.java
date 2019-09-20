package tk.mitsky.web.model;

import java.sql.Date;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long billing_id;
	
	@Column(name = "invoice_date")
	private Instant invoiceDate;

	@Column(name = "current_period")
	private Date currentPeriod;
	
	@JsonIgnore
	@JoinColumn(name = "id_card")
	@OneToOne(mappedBy = "billing")
	private CreditCard creditCard;
	
	private double price;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public Billing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Billing(long billing_id, Instant invoiceDate, Date currentPeriod, CreditCard creditCard, double price,
			User user) {
		super();
		this.billing_id = billing_id;
		this.invoiceDate = invoiceDate;
		this.currentPeriod = currentPeriod;
		this.creditCard = creditCard;
		this.price = price;
		this.user = user;
	}

	public long getBilling_id() {
		return billing_id;
	}

	public void setBilling_id(long billing_id) {
		this.billing_id = billing_id;
	}

	public Instant getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Instant invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Date currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

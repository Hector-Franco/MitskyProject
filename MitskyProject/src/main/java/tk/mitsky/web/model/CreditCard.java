package tk.mitsky.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_card")
	private int id_card;

	@Column(name = "card_number")
	private String creditCardNumber;

	@Column(name = "code_cvv")
	private String codeCVV;

	private byte month;

	private byte year;

	private String name;

	@Column(name = "last_name")
	private String lastName;

	@OneToOne(fetch = FetchType.LAZY)
	private Billing billing;
	
	
	public CreditCard() {
		
	}

	public CreditCard(int id, String creditCardNumber, String codeCVV, byte month, byte year, String name, String lastName) {
		super();
		this.id_card = id;
		this.creditCardNumber = creditCardNumber;
		this.codeCVV = codeCVV;
		this.month = month;
		this.year = year;
		this.name = name;
		this.lastName = lastName;

	}

	public CreditCard(int id, String creditCardNumber, String codeCVV, byte month, byte year, String name, String lastName,
			Billing billing) {
		super();
		this.id_card = id;
		this.creditCardNumber = creditCardNumber;
		this.codeCVV = codeCVV;
		this.month = month;
		this.year = year;
		this.name = name;
		this.lastName = lastName;
		this.billing = billing;
	}



	public int getId_card() {
		return id_card;
	}

	public void setId_card(int id_card) {
		this.id_card = id_card;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public int getId() {
		return id_card;
	}

	public void setId(int id) {
		this.id_card = id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCodeCVV() {
		return codeCVV;
	}

	public void setCodeCVV(String codeCVV) {
		this.codeCVV = codeCVV;
	}

	public byte getMonth() {
		return month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	public byte getYear() {
		return year;
	}

	public void setYear(byte year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

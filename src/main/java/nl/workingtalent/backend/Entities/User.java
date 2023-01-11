package nl.workingtalent.backend.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@Column(length = 50, nullable = false)
	private String lastName;
	
	@Column(length = 100, nullable = false)
	private String emailAddress;
	
	@Column(length = 50, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private LocalDateTime dateAccountCreated;
	
	@Column(nullable = true)
	private LocalDateTime dateAccountDeleted;
	
	@Column(nullable = false)
	private boolean active = true;
	
	@Column(nullable = false)
	private boolean admin;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Loan> loans;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Reservation> reservations;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getDateAccountCreated() {
		return dateAccountCreated;
	}

	public void setDateAccountCreated(LocalDateTime dateAccountCreated) {
		this.dateAccountCreated = dateAccountCreated;
	}

	public LocalDateTime getDateAccountDeleted() {
		return dateAccountDeleted;
	}

	public void setDateAccountDeleted(LocalDateTime dateAccountDeleted) {
		this.dateAccountDeleted = dateAccountDeleted;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	
}

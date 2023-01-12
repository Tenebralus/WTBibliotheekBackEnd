package nl.workingtalent.backend.Entities;

import java.time.LocalDateTime;
import java.util.List;

//Deze class bevat alléén de gegevens die je nodig hebt voor je programma
public class ReservationDTO {
	Long id;
	
	String bookTitle;
	
	Long bookId;
	
	
	String firstName;
	
	String lastName;
	
	LocalDateTime reservationDate;
	
	List<BookCopy> bookcopies;
	
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<BookCopy> getBookcopies() {
		return bookcopies;
	}

	public void setBookcopies(List<BookCopy> bookcopies) {
		this.bookcopies = bookcopies;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	
}

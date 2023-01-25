package nl.workingtalent.backend.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.BookCopy;

public class ReservationUserDTO {
	
	private Long id;
	
	private String bookTitle;
	
	private Long bookId;
	
	private List<Author> authors;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDateTime reservationDate;
	
	private List<BookCopy> bookcopies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
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

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<BookCopy> getBookcopies() {
		return bookcopies;
	}

	public void setBookcopies(List<BookCopy> bookcopies) {
		this.bookcopies = bookcopies;
	}
}

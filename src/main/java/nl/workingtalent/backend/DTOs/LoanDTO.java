package nl.workingtalent.backend.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import nl.workingtalent.backend.Entities.Author;

//Deze class bevat alléén de gegevens die je nodig hebt voor je programma
public class LoanDTO {
	Long id;
	
	Long bookId;
	
	String bookTitle;
	
	int bookCopyNr;
	
	String bookIsbn;
	
	List<Author> authors;
	
	List<String> authorFirstNames;
	
	List<String> authorLastNames;
	
	String bookCopyStatus;
	
	String userFirstName;
	
	String userLastName;
	
	LocalDateTime dateLoaned;
	
	LocalDateTime dateReturned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getBookCopyNr() {
		return bookCopyNr;
	}

	public void setBookCopyNr(int bookCopyNr) {
		this.bookCopyNr = bookCopyNr;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<String> getAuthorFirstNames() {
		return authorFirstNames;
	}

	public void setAuthorFirstNames(List<String> authorFirstNames) {
		this.authorFirstNames = authorFirstNames;
	}

	public List<String> getAuthorLastNames() {
		return authorLastNames;
	}

	public void setAuthorLastNames(List<String> authorLastNames) {
		this.authorLastNames = authorLastNames;
	}

	public String getBookCopyStatus() {
		return bookCopyStatus;
	}

	public void setBookCopyStatus(String bookCopyStatus) {
		this.bookCopyStatus = bookCopyStatus;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public LocalDateTime getDateLoaned() {
		return dateLoaned;
	}

	public void setDateLoaned(LocalDateTime dateLoaned) {
		this.dateLoaned = dateLoaned;
	}

	public LocalDateTime getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDateTime dateReturned) {
		this.dateReturned = dateReturned;
	}
	
	
}

package nl.workingtalent.backend.DTOs;

import java.util.List;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;

public class BookDetailsDTO {
	
	List<BookCopy> bookcopies;
	
	String username;

	public List<BookCopy> getBookcopies() {
		return bookcopies;
	}

	public void setBookcopies(List<BookCopy> bookcopies) {
		this.bookcopies = bookcopies;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

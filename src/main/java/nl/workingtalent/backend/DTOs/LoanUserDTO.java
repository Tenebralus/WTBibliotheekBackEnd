package nl.workingtalent.backend.DTOs;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.User;

public class LoanUserDTO {
	private BookCopy bookcopy;
	
	private User user;
	
	private long bookCopyId;
	
	private String firstName;
	
	private String lastName;

	public BookCopy getBookcopy() {
		return bookcopy;
	}

	public void setBookcopy(BookCopy bookcopy) {
		this.bookcopy = bookcopy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(long bookCopyId) {
		this.bookCopyId = bookCopyId;
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


	
	
	
	
}

package nl.workingtalent.backend.DTOs;

import java.util.List;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Entities.User;

public class BookCopyDetailsDTO {
	private Long id;//=bookcopyid
	
	private String bookTitle;
	
	private Long bookId;
	
	private String bookIsbn; // for getting the image=> no new one
	
	private List<Tag> bookTags; // for showing tags by exemplaren
	
	private List<Author> bookAuthors; //for showing authors by exemplaardetails
	
	private String urlImage;//showing images bij exemplaren via books
	
	private int bookCopyNr;
	
	private String status;
	
	private Loan currentLoan;
	
	private int timesLoaned;
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Author> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(List<Author> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public List<Tag> getBookTags() {
		return bookTags;
	}

	public void setBookTags(List<Tag> bookTag) {
		this.bookTags = bookTag;
	}

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

	public int getBookCopyNr() {
		return bookCopyNr;
	}

	public void setBookCopyNr(int bookCopyNr) {
		this.bookCopyNr = bookCopyNr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Loan getCurrentLoan() {
		return currentLoan;
	}

	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}

	public int getTimesLoaned() {
		return timesLoaned;
	}

	public void setTimesLoaned(int timesLoaned) {
		this.timesLoaned = timesLoaned;
	}
	
	

}

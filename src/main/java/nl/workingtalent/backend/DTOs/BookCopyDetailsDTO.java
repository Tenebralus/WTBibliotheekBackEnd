package nl.workingtalent.backend.DTOs;

import nl.workingtalent.backend.Entities.Loan;

public class BookCopyDetailsDTO {
	private Long id;
	
	private String bookTitle;
	
	private Long bookId;
	
	private int bookCopyNr;
	
	private String status;
	
	private Loan currentLoan;
	
	private int timesLoaned;

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

package nl.workingtalent.backend.DTOs;

public class BookCopyCreateRequestDTO {
	
	private Long id;
	
	private int bookCopyNumber;

	public int getBookCopyNumber() {
		return bookCopyNumber;
	}

	public void setBookCopyNumber(int bookCopyNumber) {
		this.bookCopyNumber = bookCopyNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

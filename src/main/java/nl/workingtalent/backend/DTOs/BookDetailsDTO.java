package nl.workingtalent.backend.DTOs;

import java.util.List;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;

public class BookDetailsDTO {
	
	String bookTitle;
	
	List<Author> bookAuthors;
	
	String bookIsbn;
	
	List<BookCopyDetailsDTO> bookCopyDetailsDTOs;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
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

	public List<BookCopyDetailsDTO> getBookCopyDetailsDTOs() {
		return bookCopyDetailsDTOs;
	}

	public void setBookCopyDetailsDTOs(List<BookCopyDetailsDTO> bookCopyDetailsDTOs) {
		this.bookCopyDetailsDTOs = bookCopyDetailsDTOs;
	}
	
	
}

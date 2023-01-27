package nl.workingtalent.backend.DTOs;

import java.util.List;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.Tag;

public class BookDetailsDTO {
	
	private String bookTitle;
	
	private List<Author> bookAuthors;
	
	private List<Tag> bookTag; // for showing tags by exemplaren en boeken
	
	private String urlImage; //for images
	
	private String bookIsbn;
	
	private List<BookCopyDetailsDTO> bookCopyDetailsDTOs;

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

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

	public List<Tag> getBookTag() {
		return bookTag;
	}

	public void setBookTag(List<Tag> bookTag) {
		this.bookTag = bookTag;
	}
	
	
}

package nl.workingtalent.backend.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Entities.User;

public class BookCopyDTO {
	
	private Long id;
	
	private Long bookCopyId;
	
	private String bookTitle;
	
	private int bookCopyNr;
	
	private String bookIsbn;
	
	private String urlImage;
	
	private List<Tag> bookTags; // for showing tags by exemplaren
	
	private List<Author> bookAuthors; //for showing authors by exemplaardetails
	
	private String bookCopyStatus;
	
	private User user;
	
	private String userFirstName;
	
	private String userLastName;
	
	private LocalDateTime dateLoaned;
	
	private LocalDateTime dateReturned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(Long bookCopyId) {
		this.bookCopyId = bookCopyId;
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

	public List<Tag> getBookTags() {
		return bookTags;
	}

	public void setBookTags(List<Tag> bookTag) {
		this.bookTags = bookTag;
	}

	public List<Author> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(List<Author> bookAuthors) {
		this.bookAuthors = bookAuthors;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}

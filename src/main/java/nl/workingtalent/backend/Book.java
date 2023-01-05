package nl.workingtalent.backend;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=50, nullable = false)
	private String title;
	
	@Column(length=50, nullable = false)
	private String isbn;

	@ManyToMany
	private List<Tag> tags;
	
	@OneToMany(mappedBy="book")
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy="book")
	private List<BookCopy> bookcopies;

	@ManyToMany
	private List<Author> authors;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	

}

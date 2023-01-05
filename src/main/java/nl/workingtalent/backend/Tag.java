package wtbiblio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(length=50, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy="tags") 
	private List<Book> books;
	//zodat niet hoeft book_tags class


}

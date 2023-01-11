package nl.workingtalent.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IBookRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookController {

	@Autowired
	private IBookRepository repo;
	
	@RequestMapping(value = "book/all")
	public List<Book> findAllBooks()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "book/id/{id}")
	public Book findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "book/title/{title}")
	public List<Book> findByTitle(@PathVariable String title)
	{
		return repo.findByTitle(title);
	}
	
	@RequestMapping(value = "book/isbn/{isbn}")
	public List<Book> findByIsbn(@PathVariable String isbn)
	{
		return repo.findByIsbn(isbn);
	}
	
	@RequestMapping(value = "book/tags/{tags}")
	public List<Tag> findByTagsIn(@PathVariable List<Tag> tags)
	{
		return repo.findByTagsIn(tags);
	}
	
	@RequestMapping(value = "book/reservations/{reservations}")
	public List<Reservation> findByReservationsIn(@PathVariable List<Reservation> reservations)
	{
		return repo.findByReservationsIn(reservations);
	}
	
	@RequestMapping(value = "book/bookcopies/{bookcopies}")
	public List<BookCopy> findByBookcopiesIn(@PathVariable List<BookCopy> bookcopies)
	{
		return repo.findByBookcopiesIn(bookcopies);
	}
	
	@RequestMapping(value = "book/authors/{authors}")
	public List<Author> findByAuthorsIn(@PathVariable List<Author> authors)
	{
		return repo.findByAuthorsIn(authors);
	}
	
	@PostMapping(value = "book/create")
	public void createBook(@RequestBody Book book)
	{
		repo.save(book);
	}
	
	@PutMapping(value = "book/update/{id}")
	public void updateBook(@PathVariable long id, @RequestBody Book book)
	{
		Book foundBook = findById(id);
		foundBook.setTitle(book.getTitle());
		foundBook.setIsbn(book.getIsbn());
		foundBook.setTags(book.getTags());
		foundBook.setReservations(book.getReservations());
		foundBook.setBookcopies(book.getBookcopies());
		foundBook.setAuthors(book.getAuthors());
		repo.save(foundBook);
	}
	
	//not want to delete books, just edit bookcopies into archived

}

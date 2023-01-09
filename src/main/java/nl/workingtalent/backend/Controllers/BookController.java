package nl.workingtalent.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.Book;
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
	
	/*@RequestMapping(value = "book/tags/{tags}")
	public List<Tag> findByTags(@PathVariable List<Tag> tags)
	{
		return repo.findByTags(tags);
	}
	
	@RequestMapping(value = "book/reservations/{reservations}")
	public List<Reservation> findByReservations(@PathVariable List<Reservation> reservations)
	{
		return repo.findByReservations(reservations);
	}
	
	@RequestMapping(value = "book/bookcopies/{bookcopies}")
	public List<BookCopy> findByBookCopies(@PathVariable List<BookCopy> bookcopies)
	{
		return repo.findByBookCopies(bookcopies);
	}
	
	@RequestMapping(value = "book/authors/{authors}")
	public List<Authors> findByAuthors(@PathVariable List<Author> authors)
	{
		return repo.findByAuthors(authors);
	}*/
	
	@RequestMapping(value = "book/create", method = RequestMethod.POST)
	public void createBook(@RequestBody Book book)
	{
		repo.save(book);
	}
	
	@RequestMapping(value = "book/update/{id}", method = RequestMethod.PUT)
	public void updateBook(@PathVariable long id, @RequestBody Book book )
	{
		Book foundBook = findById(id);
		foundBook.setTitle(book.getTitle());
		foundBook.setIsbn(book.getIsbn());
		foundBook.setTags(book.getTags());
		foundBook.setReservations(book.getReservations());
		foundBook.setBookcopies(book.getBookcopies());
		foundBook.setAuthors(book.getAuthors());
		repo.save(book);
	}
	
	@RequestMapping(value = "book/delete/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

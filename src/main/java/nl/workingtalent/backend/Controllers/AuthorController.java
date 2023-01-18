package nl.workingtalent.backend.Controllers;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToMany;

import nl.workingtalent.backend.Repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Repositories.iAuthorRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class AuthorController {

	@Autowired
	private iAuthorRepository repo;

	@Autowired
	private IBookRepository bookRepo;

	@RequestMapping(value = "author/all")
	public List<Author> findAllAuthors()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "author/id/{id}")
	public Author findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "author/firstname/{firstname}")
	public List<Author> findByfirstName(@PathVariable String firstname)
	{
		return repo.findByfirstName(firstname);
	}
	
	@RequestMapping(value = "author/lastname/{lastname}")
	public List<Author> findBylastName(@PathVariable String lastname)
	{
		return repo.findBylastName(lastname);
	}
	
	@PostMapping(value = "author/create")
	public void createBook(@RequestBody Author author)
	{
		repo.save(author);
	}
	
	@PutMapping(value = "author/update/{id}")
	public void updateAuthor(@PathVariable long id, @RequestBody Author author)
	{
		Author foundAuthor = findById(id);
		foundAuthor.setFirstName(author.getFirstName());
		foundAuthor.setLastName(author.getLastName());
		foundAuthor.setBooks(author.getBooks());
		repo.save(foundAuthor);
	}

//	@PutMapping(value = "author/update/book/{bookId}")
//	public void updateAuthorByBook(@PathVariable long bookId, @RequestBody List<Author> newAuthors)
//	{
//		Book book = bookRepo.findById(bookId).get();
//		List<Author> authors = book.getAuthors();
//		System.out.println(newAuthors.get(1));
////		System.out.println(authors.get(1));
////		Author foundAuthor = findById(id);
////		foundAuthor.setFirstName(author.getFirstName());
////		foundAuthor.setLastName(author.getLastName());
////		foundAuthor.setBooks(author.getBooks());
////		repo.save(foundAuthor);
//	}
	
	@DeleteMapping(value = "author/delete/{id}")
	public void deleteAuthor(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

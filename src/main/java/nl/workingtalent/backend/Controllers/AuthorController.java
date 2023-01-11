package nl.workingtalent.backend.Controllers;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "author/create", method = RequestMethod.POST)
	public void createBook(@RequestBody Author author)
	{
		repo.save(author);
	}
	
	@RequestMapping(value = "author/update/{id}", method = RequestMethod.PUT)
	public void updateAuthor(@PathVariable long id, @RequestBody Author author)
	{
		Author foundAuthor = findById(id);
		foundAuthor.setFirstName(author.getFirstName());
		foundAuthor.setLastName(author.getLastName());
		foundAuthor.setBooks(author.getBooks());
	}
	
	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

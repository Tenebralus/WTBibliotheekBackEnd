package nl.workingtalent.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

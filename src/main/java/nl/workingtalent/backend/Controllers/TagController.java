package nl.workingtalent.backend.Controllers;

import java.util.List;
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
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Repositories.ITagRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class TagController {
	
	@Autowired
	ITagRepository repo;

	
	@RequestMapping(value = "tag/all")
	public List<Tag> findAllTags()
	{
		return repo.findAll();
	}

	@RequestMapping(value = "tag/id/{id}")
	public Tag findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "tag/name/{name}")
	public List<Tag> findByName(@PathVariable String name)
	{
		return repo.findByName(name);
	}
	

	@RequestMapping(value = "tag/books/{books}")
	public List<Tag> findByBooksIn(@PathVariable List<Book> books)
	{
		return repo.findByBooksIn(books);
	}
	
	@PostMapping(value = "tag/create")
	public void createTag(@RequestBody Tag tag)
	{
		repo.save(tag);
	}
	
	@PutMapping(value = "tag/update/{id}")
	public void updateTag(@PathVariable long id, @RequestBody Tag tag)
	{
		Tag foundTag = findById(id);
		foundTag.setName(tag.getName());
		foundTag.setBooks(tag.getBooks());
		repo.save(foundTag);
	}
	
	@DeleteMapping(value = "tag/delete/{id}")
	public void deleteTag(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

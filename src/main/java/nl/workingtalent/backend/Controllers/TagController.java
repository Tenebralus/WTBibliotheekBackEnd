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
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.ITagRespository;
import nl.workingtalent.backend.Repositories.IUserRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class TagController {
	
	@Autowired
	ITagRespository repo;
	
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
	public Tag findByName(@PathVariable String name)
	//or List<Tag>
	{
		return repo.findByName(name);
	}
	
	@RequestMapping(value = "tag/name/{name}")
	public List<Tag> findByBook(@PathVariable Book book)
	{
		return repo.findByBook(book);
	}
	
	@RequestMapping(value = "tag/create", method = RequestMethod.POST)
	public void createTag(@RequestBody Tag tag)
	{
		repo.save(tag);
	}
	
	@RequestMapping(value = "tag/update/{id}", method = RequestMethod.PUT)
	public void updateTag(@PathVariable long id, @RequestBody Tag tag )
	{
		Tag foundTag = findById(id);
		foundTag.setName(tag.getName());
		foundTag.setBooks(tag.getBooks());
		repo.save(tag);
	}
	
	@RequestMapping(value = "tag/delete/{id}", method = RequestMethod.DELETE)
	public void deleteTag(@PathVariable long id)
	{
		repo.deleteById(id);
	}
	
}

package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;

public class UserController {
	
	IUserRepository repo;
	
	@RequestMapping(value = "user/all")
	public List<User> findAllUsers()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "user/id/{id}")
	public User findUserById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "user/firstname/{firstName}")
	public List<User> findByFirstName(@PathVariable String firstName)
	{
		return repo.findByFirstName(firstName);
	}
	
	@RequestMapping(value = "user/lastname/{lastName}")
	public List<User> findByLastName(String lastName)
	{
		return repo.findByLastName(lastName);
	}
	
	@RequestMapping(value = "user/emailaddress/{emailAddress}")
	public List<User> findByEmailAddress(String emailAddress)
	{
		return repo.findByEmailAddress(emailAddress);
	}
	
	@RequestMapping(value = "user/dateaccountcreated/{dateAccountCreated}")
	public List<User> findByDateAccountCreated(LocalDateTime dateAccountCreated)
	{
		return repo.findByDateAccountCreated(dateAccountCreated);
				
	}
	
	@RequestMapping(value = "user/active/{active}")
	public List<User> findByActive(boolean active)
	{
		return repo.findByActive(active);
	}
	
	// add create, update and delete and link with frontend
	// being able to login
}

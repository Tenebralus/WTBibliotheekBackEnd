package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {
	
	@Autowired
	IUserRepository repo;
	
	@RequestMapping(value = "user/all")
	public List<User> findAllUsers()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "user/id/{id}")
	public User findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "user/firstname/{firstName}")
	public List<User> findByFirstName(@PathVariable String firstName)
	{
		return repo.findByFirstName(firstName);
	}
	
	@RequestMapping(value = "user/lastname/{lastName}")
	public List<User> findByLastName(@PathVariable String lastName)
	{
		return repo.findByLastName(lastName);
	}
	
	@RequestMapping(value = "user/emailaddress/{emailAddress}")
	public List<User> findByEmailAddress(@PathVariable String emailAddress)
	{
		return repo.findByEmailAddress(emailAddress);
	}
	
	@RequestMapping(value = "user/password/{password}")
	//We do not concern with privacy right now
	//we need this to compare email with password for login
	//if wrong; frontend give alert
	public List<User> findByPassword(@PathVariable String password)
	{
		return repo.findByPassword(password);
	}
	
	@RequestMapping(value = "user/userpass/{emailAddress}:{password}")
	public User findByEmailAddressAndPassword(@PathVariable String emailAddress, @PathVariable String password)
	{
		return repo.findByEmailAddressAndPassword(emailAddress, password);
		//gives user or null if false
		//not yet specified if user exists if password is false etc
		//later: check without logout new browser
	}
	
	@RequestMapping(value = "user/dateaccountcreated/{dateAccountCreated}")
	public List<User> findByDateAccountCreated(@PathVariable LocalDateTime dateAccountCreated)
	{
		return repo.findByDateAccountCreated(dateAccountCreated);
				
	}
	
	@RequestMapping(value = "user/dateaccountdeleted/{dateAccountDeleted}")
	public List<User> findByDateAccountDeleted(@PathVariable LocalDateTime dateAccountDeleted)
	{
		return repo.findByDateAccountCreated(dateAccountDeleted);
				
	}
	
	@RequestMapping(value = "user/active/{active}")
	public List<User> findByActive(@PathVariable boolean active)
	{
		return repo.findByActive(active);
	}
	
	@RequestMapping(value = "user/create", method = RequestMethod.POST)
	public void createUser(@RequestBody User user)
	{
		repo.save(user);
	}
	
	@RequestMapping(value = "user/update/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable long id, @RequestBody User user )
	{
		User foundUser = findById(id);
		foundUser.setFirstName(user.getFirstName());
		foundUser.setLastName(user.getLastName());
		foundUser.setEmailAddress(user.getEmailAddress());
		foundUser.setPassword(user.getPassword());
		//foundUser.setDateAccountCreated(user.getDateAccountCreated());
		//you probably do not want to edit the creation date
		foundUser.setDateAccountDeleted(user.getDateAccountDeleted());
		foundUser.setActive(user.isActive());
		foundUser.setLoans(user.getLoans());
		foundUser.setAdmin(user.isAdmin());
		repo.save(foundUser);
	}
	
	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

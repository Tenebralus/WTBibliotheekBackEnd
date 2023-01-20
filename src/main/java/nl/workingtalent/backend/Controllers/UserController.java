package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.DTOs.LoginRequestDto;
import nl.workingtalent.backend.DTOs.LoginResponseDto;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {
	
	@Autowired
	IUserRepository repo;
	
	/*@RequestMapping(value = "user/all")
	public List<User> findAllUsers()
	{
		return repo.findAll();
	}*/
	
	@RequestMapping(value = "user/search/")
	public List<User> searchAllUsers() {
		return repo.findAll();
	}
	@RequestMapping(value = "user/search/{keyword}")
	public List<User> searchAllUsers(@PathVariable String keyword) {
		return repo.search(keyword);
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
		return repo.findByEmailAddressAndPassword(emailAddress, password).orElse(null);
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
	
	@PostMapping(value = "user/create")
	public void createUser(@RequestBody User user)
	{
		repo.save(user);
	}
	
	@PutMapping(value = "user/update/{id}")
	public void updateUser(@PathVariable long id, @RequestBody User user)
	{
		User foundUser = findById(id);
		System.out.println(user.getFirstName());
		if(user.getFirstName() != null && user.getFirstName().length() != 0) {foundUser.setFirstName(user.getFirstName());}
		if(user.getLastName() != null && user.getLastName().length() != 0) {foundUser.setLastName(user.getLastName());}
		if(user.getEmailAddress() != null && user.getEmailAddress().length() != 0) {foundUser.setEmailAddress(user.getEmailAddress());}
		if(user.getPassword() != null && user.getPassword().length() != 0) {foundUser.setPassword(user.getPassword());}
		//foundUser.setLastName(user.getLastName());
		//foundUser.setEmailAddress(user.getEmailAddress());
		//foundUser.setPassword(user.getPassword());
		
		//foundUser.setDateAccountCreated(user.getDateAccountCreated());
		//you probably do not want to edit the creation date
		
		//foundUser.setDateAccountDeleted(user.getDateAccountDeleted());
		//foundUser.setActive(user.isActive());
		//foundUser.setLoans(user.getLoans());
		//foundUser.setAdmin(user.isAdmin());
		
		repo.save(foundUser);
	}
	
	@DeleteMapping(value = "user/anonymize/{id}")
	public void anonymizeUser(@PathVariable long id, @RequestBody User user)
	{
		User foundUser = findById(id);
		foundUser.setFirstName("anon");
		foundUser.setLastName("anon");
		foundUser.setEmailAddress("anon@wt.nl");
		foundUser.setDateAccountDeleted(LocalDateTime.now());
		foundUser.setActive(false);
		repo.save(foundUser);
	}
	
	@PostMapping("user/login")
	public LoginResponseDto userLogin(@RequestBody LoginRequestDto dto) {
		// Check de user gegevens
		Optional<User> optional = repo.findByEmailAddressAndPassword(dto.getEmail(), dto.getPassword());
		
		// Is de kartonen doos leeg
		if (optional.isEmpty())
			return new LoginResponseDto(false, null);
		
		// Get opent de kartonnen doos
		User user = optional.get();
		
		// Maak een token en bewaar die
		user.setToken(RandomStringUtils.random(80, true, true));
		repo.save(user);
		
		// Geef de token terug
		return new LoginResponseDto(true, user);
	}
	
	@GetMapping("user/all")
	public List<User> getAllUsers(@RequestHeader("Authentication") String token) {
		// Find user by token
		User user = repo.findByToken(token);
		
		// Check if admin
		if (user.isAdmin()) {
			// Return users list
			return repo.findAll();
		}
		
		
		
		return null;
	}
	
	
}

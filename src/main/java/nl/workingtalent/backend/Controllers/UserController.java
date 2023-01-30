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

import nl.workingtalent.backend.EmailService;
import nl.workingtalent.backend.DTOs.ChangePasswordRequestDto;
import nl.workingtalent.backend.DTOs.ChangePasswordResponseDTO;
import nl.workingtalent.backend.DTOs.LoginRequestDto;
import nl.workingtalent.backend.DTOs.LoginResponseDto;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {
	
	@Autowired
	IUserRepository repo;
	
	@Autowired
	private EmailService emailService;
	
	/*@RequestMapping(value = "user/all")
	public List<User> findAllUsers()
	{
		return repo.findAll();
	}*/
	
	@GetMapping("user/all")
	public List<User> getAllUsers(@RequestHeader("Authentication") String token) {
		// Find user by token
		User user = repo.findByToken(token);
		
		// Check if admin
		if (user.isAdmin()) {
			// Return users list
			return repo.findAll();
		}
		
		else return null;
		
	}
  
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
	public Optional<User> findByEmailAddress(@PathVariable String emailAddress)
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
	public boolean createUser(@RequestBody User user)
	{
		Optional<User> optional = repo.findByEmailAddress(user.getEmailAddress());
		// als die niet leeg is, betaat de email al, dus niet user aanmaken
		if (!optional.isEmpty()) {
			return false;
		}
		String password = String.valueOf((int)Math.round(Math.random() * 100000) +10000) + "WT";
		String text = "Dit is je eerste login code: "+password+".";
		this.emailService.sendSimpleMessage("bibliotheekwt@hotmail.com", user.getEmailAddress(), "Eerste login credentials", text);
		String pwHash = BCrypt.withDefaults().hashToString(6, password.toCharArray());
		user.setPassword(pwHash);
		repo.save(user);
		return true;
	}
	
	@PutMapping(value = "user/update/{id}")
	public void updateUser(@PathVariable long id, @RequestBody User user)
	{
		User foundUser = findById(id);
		
		if(user.getFirstName() != null && user.getFirstName().length() != 0) {foundUser.setFirstName(user.getFirstName());}
		if(user.getLastName() != null && user.getLastName().length() != 0) {foundUser.setLastName(user.getLastName());}
		if(user.getEmailAddress() != null && user.getEmailAddress().length() != 0) {foundUser.setEmailAddress(user.getEmailAddress());}
		if(user.getPassword() != null && user.getPassword().length() != 0) {foundUser.setPassword(user.getPassword());}
		foundUser.setAdmin(user.isAdmin());
		repo.save(foundUser);
	}
	
	@PutMapping(value = "user/anonymize/{id}")
	public void anonymizeUser(@PathVariable long id)
	{
		User foundUser = findById(id);
		foundUser.setFirstName("anon");
		foundUser.setLastName("anon");
		foundUser.setEmailAddress(null);
		foundUser.setPassword("password");
		foundUser.setDateAccountDeleted(LocalDateTime.now());
		foundUser.setActive(false);
		foundUser.setAdmin(false);
		repo.save(foundUser);
	}
	
	@PostMapping("user/login")
	public LoginResponseDto userLogin(@RequestBody LoginRequestDto dto) {
		// Check de user gegevens
		Optional<User> optional = repo.findByEmailAddress(dto.getEmail());
		
		// Is de kartonen doos leeg
		if (optional.isEmpty())
			return new LoginResponseDto(false, null);
		
		// Get opent de kartonnen doos
		User user = optional.get();
		
		// Verifieer het wachtwoord
		BCrypt.Result result = BCrypt.verifyer().verify(dto.getPassword().toCharArray(), user.getPassword());
		
		if (!result.verified)
			return new LoginResponseDto(false, null);
		
		// Kijk of het de eerste inlog is
		if (user.getToken() == null)
			return new LoginResponseDto(true, user);
		
		// Maak een token en bewaar die
		user.setToken(RandomStringUtils.random(80, true, true));
		repo.save(user);
		
		// Geef de token terug
		return new LoginResponseDto(true, user);
	}
	
	@PostMapping("user/firstlogin")
	public LoginResponseDto firstUserLogin(@RequestBody LoginRequestDto dto) {
		// Check de user gegevens
		Optional<User> optional = repo.findByEmailAddress(dto.getEmail());
		
		// Is de kartonen doos leeg
		if (optional.isEmpty())
			return new LoginResponseDto(false, null);
		
		// Get opent de kartonnen doos
		User user = optional.get();
		
		// Set nieuw wachtwoord
		String newPass = dto.getPassword();
		String pwHash = BCrypt.withDefaults().hashToString(6, newPass.toCharArray());
		user.setPassword(pwHash);
		repo.save(user);
		
		// Maak een token en bewaar die
		user.setToken(RandomStringUtils.random(80, true, true));
		repo.save(user);
		
		// Geef de token terug
		return new LoginResponseDto(true, user);
 }
	
	@PutMapping("user/changepassword")
	public ChangePasswordResponseDTO changePassword(@RequestHeader("Authentication") String token, @RequestBody ChangePasswordRequestDto dto) {
		User user = repo.findByToken(token);
		
		String currentPassword = dto.getCurrentPassword();
		String newPassword = dto.getNewPassword();
		
		// Controleer of het oude wachtwoord klopt
		BCrypt.Result result = BCrypt.verifyer().verify(currentPassword.toCharArray(), user.getPassword());
		
		if (!result.verified)
			return new ChangePasswordResponseDTO(false, "Het oude wachtwoord is fout ingevuld");
		
		//controleer of nieuw wachtwoord hetzelfde
		BCrypt.Result resultNewPw = BCrypt.verifyer().verify(newPassword.toCharArray(), user.getPassword());
		if (resultNewPw.verified)
			return new ChangePasswordResponseDTO(false, "Het nieuwe wachtwoord komt overeen met het oude wachtwoord");
		

		
		// Encrypt het nieuwe password
		String pwHash = BCrypt.withDefaults().hashToString(6, newPassword.toCharArray());
		user.setPassword(pwHash);
		repo.save(user);
		
		return new ChangePasswordResponseDTO(true, "");	
	}
	
	@GetMapping("users/loans/{id}")
	public boolean receiveLoans(@PathVariable long id) {
		User user = findById(id);
		List<Loan> loans = user.getLoans();
		if(loans==null) {
			return false;
		} else {
			for (Loan loan : loans) {
				if (loan.getDateReturned()==null) {
					return true;
				}
			}
			return false;
		}
	}
	
	/** Returns user type: <br>
	 *  0 = regular user <br>
	 *  1 = admin <br>
	 *  -1 = user does not exist */
	@PostMapping(value = "user/usertype")
	public int findUserTypeOfUser(@RequestHeader("Authentication") String token)
	{
		User user = repo.findByToken(token);
		
		if(user != null)
		{
			if (!user.isAdmin())
			{
				return 0;
			}
			else if(user.isAdmin())
			{
				return 1;
			}
		}
		return -1;
	}

}

package nl.workingtalent.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.Role;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IRoleRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class RoleController {
	
	@Autowired
	IRoleRepository repo;
	
	@RequestMapping(value = "role/all")
	public List<Role> findAllRoles()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "role/id/{id}")
	public Role findRoleById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "role/admin/{admin}")
	public List<Role> findByAdmin(@PathVariable boolean admin)
	{
		return repo.findByAdmin(admin);
	}
	
	/*
	@RequestMapping(value = "role/user/{user}")
	public List<Role> findByUser(@PathVariable User user)
	{
		return repo.findByUser(user);
	}
	 */
	
	@RequestMapping(value = "role/create", method = RequestMethod.POST)
	public void createRole(@RequestBody Role role)
	{
		repo.save(role);
	}
	
	//we willen nog een echte admin toevoegen en zien in de lijst na de pauze

}

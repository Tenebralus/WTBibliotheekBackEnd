package nl.workingtalent.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.workingtalent.backend.Entities.User;

public interface IUserRepository extends JpaRepository<User, Long>{ 
	List<User> findByFirstName(String firstName);
	List<User> findByLastName(String lastName);
	Optional<User> findByEmailAddress(String emailAddress);
	List<User> findByDateAccountCreated(LocalDateTime dateAccountCreated);
	List<User> findByDateAccountDeleted(LocalDateTime dateAccountDeleted);
	List<User> findByActive(boolean active);
	List<User> findByAdmin(boolean admin);
	List<User> findByPassword(String password);
	Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);
	User findByToken(String token);
	
	@Query("SELECT DISTINCT u FROM User u "
			+ "WHERE CONCAT(u.firstName, ' ', u.lastName)  LIKE %?1% "
			+ "OR u.emailAddress LIKE %?1% ")
	/*@Query("SELECT DISTINCT u FROM User u "
            + "WHERE CONCAT(u.firstName, ' ', u.lastName, ' ', u.emailAddress) LIKE %?1%  "
            +  "IF(%?1% == 'Verwijderd', u.active = 0, IF (%?1% == 'Active', u.active = 1, u.active IS NOT NULL))"
        )*/
	public List<User> search(String keyword);
}

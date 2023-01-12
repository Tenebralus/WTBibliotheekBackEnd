package nl.workingtalent.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.User;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long>{ 
	List<User> findByFirstName(String firstName);
	List<User> findByLastName(String lastName);
//	@Query("SELECT u FROM user u WHERE u.email = ?1")
	User findByEmailAddress(String emailAddress);
//	List<User> findByEmailAddress(String emailAddress);

	List<User> findByDateAccountCreated(LocalDateTime dateAccountCreated);
	List<User> findByDateAccountDeleted(LocalDateTime dateAccountDeleted);
	List<User> findByActive(boolean active);
	List<User> findByAdmin(boolean admin);
	List<User> findByPassword(String password);
	User findByEmailAddressAndPassword(String emailAddress, String password);
}

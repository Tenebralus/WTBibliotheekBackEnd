package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Role;
import nl.workingtalent.backend.Entities.User;

public interface IRoleRepository  extends JpaRepository<Role, Long>{
	List<Role> findByAdmin(boolean admin);
	//List<Role> findByUser(User user);
}

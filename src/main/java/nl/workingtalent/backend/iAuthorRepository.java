package nl.workingtalent.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iAuthorRepository extends JpaRepository<Author, Long>{
	
	List<Author> findByfirstName(String firstName);
	List<Author> findBylastName(String lastName);
}

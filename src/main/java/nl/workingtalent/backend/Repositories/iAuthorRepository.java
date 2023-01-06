package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Author;

public interface iAuthorRepository extends JpaRepository<Author, Long>{
	
	List<Author> findByfirstName(String firstName);
	List<Author> findBylastName(String lastName);
}

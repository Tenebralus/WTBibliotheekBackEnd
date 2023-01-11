package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Tag;

public interface ITagRepository extends JpaRepository<Tag, Long>{
	List<Tag> findByName(String name);
	
	List<Tag> findByBooksIn(List<Book> book);
}

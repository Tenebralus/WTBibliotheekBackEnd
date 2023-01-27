package nl.workingtalent.backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Tag;

public interface ITagRepository extends JpaRepository<Tag, Long>{
	Optional<Tag> findByName(String name);
	List<Tag> findByBooksIn(List<Book> book);
}

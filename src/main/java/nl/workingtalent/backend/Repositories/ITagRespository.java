package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Tag;

public interface ITagRespository extends JpaRepository<Tag, Long> {
	//List<Tag> findByName(String name);
	Tag findByName(String name);
	List<Tag> findByBook(Book book);
}

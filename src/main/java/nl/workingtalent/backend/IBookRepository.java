package nl.workingtalent.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long>{
	List<Book> findByTitle(String title);
	List<Book> findByIsbn(String isbn);
}

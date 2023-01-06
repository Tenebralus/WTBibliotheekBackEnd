package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Book;

public interface IBookRepository extends JpaRepository<Book, Long>{
	List<Book> findByTitle(String title);
	List<Book> findByIsbn(String isbn);
}

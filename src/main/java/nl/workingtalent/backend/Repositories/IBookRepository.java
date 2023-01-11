package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Entities.Tag;

public interface IBookRepository extends JpaRepository<Book, Long>{
	List<Book> findByTitle(String title);
	List<Book> findByIsbn(String isbn);
	List<Tag> findByTagsIn(List<Tag> tags);
	List<Reservation> findByReservationsIn(List<Reservation> reservations);
	List<BookCopy> findByBookcopiesIn(List<BookCopy> bookcopies);
	List<Author> findByAuthorsIn(List<Author> authors);
}

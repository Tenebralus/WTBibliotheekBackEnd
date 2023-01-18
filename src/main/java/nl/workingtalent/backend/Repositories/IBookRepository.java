package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	//De CONCATs zijn niet noodzakelijk, maar gewoon een kortere manier van schrijven.
	//Je kan ook voor elke individuele property LIKE doen. Als je alles samen concat, levert dat problemen op
	//wanneer een property niet bestaat, zoals bij een boek zonder tags.
	//Left join ipv join voorkomt dat je een leeg resultaat terugkrijgt als een property niet bestaat, zoals een boek zonder tags
	@Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.authors a LEFT JOIN b.tags t "
			+ "WHERE CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR t.name LIKE %?1%")
	public List<Book> search(String keyword);
}

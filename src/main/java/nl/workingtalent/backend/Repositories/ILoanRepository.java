package nl.workingtalent.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import nl.workingtalent.backend.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.User;



public interface ILoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByDateLoaned(LocalDateTime dateloaned);
	List<Loan> findByDateReturned(LocalDateTime datereturned);
	List<Loan> findByDateReturnedNotNull();
	List<Loan> findByBookCopy(@PathVariable BookCopy bookcopy);
	List<Loan> findByUser(@PathVariable User user);
	
	@Query("SELECT DISTINCT l FROM Loan l LEFT JOIN l.bookCopy bc LEFT JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%"
			+ "OR bc.status LIKE %?1%")
	public List<Loan> search(String keyword);
	
	@Query("SELECT DISTINCT l FROM Loan l LEFT JOIN l.bookCopy bc LEFT JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE bc.id IS ?2 "
			+ "AND (CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%)")
	public List<Loan> searchBookCopyByUser(String keyword, Long bookCopyId);
	
	@Query("SELECT DISTINCT l FROM Loan l JOIN l.bookCopy bc JOIN  bc.book b LEFT JOIN l.user u "
			+ "WHERE b.id IS ?2 "
			+ "AND (CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%)")
	public List<Loan> searchBookDetailsByUser(String keyword, Long bookId);

	@Query("SELECT DISTINCT l FROM Loan l LEFT JOIN l.bookCopy bc JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE u.firstName = ?2 "
			+ "AND (CONCAT(a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR b.title LIKE %?1%) "
			+ "OR b.isbn LIKE %?1%")
	public List<Loan> searchIndividual(String keyword, String keyword2);
	
	@Query("SELECT DISTINCT l FROM Loan l LEFT JOIN l.bookCopy bc LEFT JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE (CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%) "
			+ "AND l.dateReturned IS NULL"
			)
	public List<Loan> searchAllCurrent(String keyword);
	
	@Query("SELECT DISTINCT l FROM Loan l LEFT JOIN l.bookCopy bc LEFT JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE (CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%) "
			+ "AND l.dateReturned IS NOT NULL"
			)
	public List<Loan> searchAllOld(String keyword);
}

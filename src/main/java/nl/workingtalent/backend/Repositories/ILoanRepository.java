package nl.workingtalent.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.User;



public interface ILoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByDateLoaned(LocalDateTime dateloaned);
	List<Loan> findByDateReturned(LocalDateTime datereturned);
	List<Loan> findByBookCopy(@PathVariable BookCopy bookcopy);
	List<Loan> findByUser(@PathVariable User user);
	
	@Query("SELECT l FROM Loan l LEFT JOIN l.bookCopy bc LEFT JOIN l.user u LEFT JOIN bc.book b LEFT JOIN b.authors a "
			+ "WHERE CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%")
	public List<Loan> search(String keyword);
}

package nl.workingtalent.backend.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.User;



public interface ILoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByDateLoaned(LocalDateTime dateloaned);
	List<Loan> findByDateReturned(LocalDateTime datereturned);
	List<Loan> findByBookCopy(@PathVariable BookCopy bookcopy);
	List<Loan> findByUser(@PathVariable User user);
}

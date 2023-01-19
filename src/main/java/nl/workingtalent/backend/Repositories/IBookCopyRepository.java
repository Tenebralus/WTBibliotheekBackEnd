package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;


public interface IBookCopyRepository extends JpaRepository<BookCopy, Long>{
	BookCopy findByBookCopyNr(int bookCopyNr);
	BookCopy findByStatus(String status);
	List<BookCopy> findByBook(Book book);
	BookCopy findByLoans(Loan loans);
	BookCopy findByLoansIn(List<Loan> loans);
	
	
	@Query("SELECT DISTINCT b FROM BookCopy b LEFT JOIN b.authors a LEFT JOIN b.tags t "
			+ "WHERE CONCAT (b.title, ' ', b.isbn) LIKE %?1% "
			+ "OR CONCAT (a.firstName, ' ', a.lastName) LIKE %?1% "
			+ "OR t.name LIKE %?1%")
	public List<BookCopy> search(String keyword);
	
}

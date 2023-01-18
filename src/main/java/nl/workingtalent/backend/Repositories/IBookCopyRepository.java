package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;


public interface IBookCopyRepository extends JpaRepository<BookCopy, Long>{
	BookCopy findByBookCopyNr(int bookCopyNr);
	BookCopy findByStatus(String status);
	List<BookCopy> findByBook(Book book);
	BookCopy findByLoans(Loan loans);
	BookCopy findByLoansIn(List<Loan> loans);
	
}

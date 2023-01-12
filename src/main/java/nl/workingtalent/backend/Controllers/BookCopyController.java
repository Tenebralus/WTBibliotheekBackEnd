package nl.workingtalent.backend.Controllers;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.DTOs.BookCopyUpdateReturnDTO;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Repositories.IBookCopyRepository;
import nl.workingtalent.backend.Repositories.ILoanRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookCopyController {
	
	@Autowired
	IBookCopyRepository repo;
	
	@Autowired
	ILoanRepository loanRepo;
	
	@RequestMapping(value = "bookcopy/all")
	public List<BookCopy> findAllBookCopies()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "bookcopy/id/{id}")
	public BookCopy findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "bookcopy/bookcopynr/{bookcopynr}")
	public BookCopy findByBookCopyNr(@PathVariable int bookCopyNr)
	{
		return repo.findByBookCopyNr(bookCopyNr);
	}
	
	@RequestMapping(value = "bookcopy/status/{status}")
	public BookCopy findByStatus(@PathVariable String status)
	{
		return repo.findByStatus(status);
	}
	
	@RequestMapping(value = "bookcopy/book/{book}")
	public BookCopy findByBook(@PathVariable Book book)
	{
		return repo.findByBook(book);
	}
	
	@RequestMapping(value = "bookcopy/loans/{loans}")
	public BookCopy findByLoans(@PathVariable Loan loans)
	{
		return repo.findByLoans(loans);
	}
	
	/*@RequestMapping(value = "bookcopy/loans/{loans}")
	public BookCopy findByLoansIn(@PathVariable List<Loan> loans)
	{
		return repo.findByLoansIn(loans);
	}*/
	
	@PostMapping(value = "bookcopy/create")
	public void createBookCopy(@RequestBody BookCopy bookcopy)
	{
		repo.save(bookcopy);
	}
	
	@PutMapping(value = "bookcopy/update/{id}")
	public void updateBookCopy(@PathVariable long id, @RequestBody BookCopy bookcopy )
	{
		BookCopy foundBookCopy = findById(id);
		foundBookCopy.setBookCopyNr(bookcopy.getBookCopyNr());
		foundBookCopy.setStatus(bookcopy.getStatus());
		foundBookCopy.setBook(bookcopy.getBook());
		foundBookCopy.setLoans(bookcopy.getLoans());
		repo.save(foundBookCopy);
	}
	
	@PutMapping(value = "bookcopy/update/returnbookcopy/{bookCopyId}/{loanId}")
	public void updateBookCopyStatus(@PathVariable long bookCopyId, @PathVariable long loanId, @RequestBody String status)
	{
		BookCopy foundBookCopy = findById(bookCopyId);
		foundBookCopy.setStatus(status);
		
		List<Loan> loans = foundBookCopy.getLoans();
		Loan loan = new Loan();
		for(Loan tempLoan : loans)
		{
			if(tempLoan.getId() == loanId);
			{
				loan = tempLoan;
				loan.setDateReturned(LocalDateTime.now());
				break;
			}
		}
			
		repo.save(foundBookCopy);
		loanRepo.save(loan);
	}
	
	//not want to delete bookcopies, just edit them into archived
	@PutMapping(value = "bookcopy/archive/{id}")
	public void archiveBookCopy(@PathVariable long id)
	{
		BookCopy foundBookCopy = findById(id);
		foundBookCopy.setStatus("archived");
		repo.save(foundBookCopy);
	}

}

package nl.workingtalent.backend.Controllers;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Repositories.IBookCopyRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookCopyController {
	
	@Autowired
	IBookCopyRepository repo;
	
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
	
	@RequestMapping(value = "bookcopy/create", method = RequestMethod.POST)
	public void createBookCopy(@RequestBody BookCopy bookcopy)
	{
		repo.save(bookcopy);
	}
	
	@RequestMapping(value = "bookcopy/update/{id}", method = RequestMethod.PUT)
	public void updateBookCopy(@PathVariable long id, @RequestBody BookCopy bookcopy )
	{
		BookCopy foundBookCopy = findById(id);
		foundBookCopy.setBookCopyNr(bookcopy.getBookCopyNr());
		foundBookCopy.setStatus(bookcopy.getStatus());
		foundBookCopy.setBook(bookcopy.getBook());
		foundBookCopy.setLoans(bookcopy.getLoans());
		repo.save(bookcopy);
	}
	
	@RequestMapping(value = "bookopy/delete/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable long id)
	{
		repo.deleteById(id);
	}

}

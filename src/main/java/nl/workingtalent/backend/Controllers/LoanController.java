package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.ILoanRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class LoanController {
	
	@Autowired
	ILoanRepository repo;
	
	@RequestMapping(value = "loan/all")
	public List<Loan> findAllLoans()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "loan/id/{id}")
	public Loan findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@RequestMapping(value = "loan/dateloaned/{dateloaned}")
	public List<Loan> findByDateLoaned(@PathVariable LocalDateTime dateloaned)
	{
		return repo.findByDateLoaned(dateloaned);
	}
	
	@RequestMapping(value = "loan/datereturned/{datereturned}")
	public List<Loan> findByDateReturned(@PathVariable LocalDateTime datereturned)
	{
		return repo.findByDateLoaned(datereturned);
	}
	
	@RequestMapping(value = "loan/bookcopy/{bookcopy}")
	public List<Loan> findByBookCopy(@PathVariable BookCopy bookcopy)
	{
		return repo.findByBookCopy(bookcopy);
	}
	
	@RequestMapping(value = "loan/user/{user}")
	public List<Loan> findByUser(@PathVariable User user)
	{
		return repo.findByUser(user);
	}
	
	@RequestMapping(value = "loan/create", method = RequestMethod.POST)
	public void createLoan(@RequestBody Loan loan)
	{
		repo.save(loan);
	}
	
	@RequestMapping(value = "loan/update/{id}", method = RequestMethod.PUT)
	public void updateLoan(@PathVariable long id, @RequestBody Loan loan )
	{
		Loan foundLoan = findById(id);
		foundLoan.setDateLoaned(loan.getDateLoaned());
		foundLoan.setDateReturned(loan.getDateReturned());
		foundLoan.setBookCopy(loan.getBookCopy());
		foundLoan.setUser(loan.getUser());
		repo.save(loan);
	}
	
	@RequestMapping(value = "loan/delete/{id}", method = RequestMethod.DELETE)
	public void deleteLoan(@PathVariable long id)
	{
		repo.deleteById(id);
	}
}

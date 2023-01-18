package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.DTOs.LoanDTO;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.Reservation;

import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IBookCopyRepository;
import nl.workingtalent.backend.Repositories.IBookRepository;
import nl.workingtalent.backend.Repositories.ILoanRepository;
import nl.workingtalent.backend.Repositories.IReservationRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class LoanController {
	
	@Autowired
	ILoanRepository repo;
	
	@Autowired
	IReservationRepository reservationRepo;
	
	@Autowired
	IBookRepository bookRepo;
	
	@Autowired
	IBookCopyRepository bookCopyRepo;
	
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
	
	@PostMapping(value = "loan/create")
	public void createLoan(@RequestBody Loan loan)
	{
		repo.save(loan);
	}
	
	@PutMapping(value = "loan/update/{id}")
	public void updateLoan(@PathVariable long id, @RequestBody Loan loan)
	{
		Loan foundLoan = findById(id);
		foundLoan.setDateLoaned(loan.getDateLoaned());
		foundLoan.setDateReturned(loan.getDateReturned());
		foundLoan.setBookCopy(loan.getBookCopy());
		foundLoan.setUser(loan.getUser());
		repo.save(foundLoan);
	}
	
	@DeleteMapping(value = "loan/delete/{id}")
	public void deleteLoan(@PathVariable long id)
	{
		repo.deleteById(id);
	}
	
	//DTO voorbeeld
	@RequestMapping(value= "loan/dto")
	public List<LoanDTO> findAllLoanDTOs() {
		//Modelmapper is een package die DTOs makkelijker maakt
		ModelMapper modelMapper = new ModelMapper();

		//Modelmapper probeert zelf uit te vinden welke gegevens van de 'echte' class in de DTO horen, maar als ie het niet snapt kan je handmatig 
		//relaties aangeven met typeMap
		modelMapper.typeMap(Loan.class, LoanDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), 
					LoanDTO::setId);
			mapper.map(src -> src.getBookCopy().getId(), 
					LoanDTO::setBookCopyId);
			mapper.map(src -> src.getBookCopy().getBook().getTitle(), 
					LoanDTO::setBookTitle);
			mapper.map(src -> src.getBookCopy().getBookCopyNr(), 
					LoanDTO::setBookCopyNr);
			mapper.map(src -> src.getBookCopy().getBook().getIsbn(), 
					LoanDTO::setBookIsbn);
			mapper.map(src -> src.getBookCopy().getBook().getAuthors(), 
					LoanDTO::setAuthors);
			mapper.map(src -> src.getBookCopy().getStatus(), 
					LoanDTO::setBookCopyStatus);
			mapper.map(src -> src.getUser().getFirstName(), 
					LoanDTO::setUserFirstName);
			mapper.map(src -> src.getUser().getLastName(), 
					LoanDTO::setUserLastName);
			mapper.map(src -> src.getDateLoaned(),
					LoanDTO::setDateLoaned);
		});

		//Dit is nodig om lijsten van objecten te DTOen
		List<LoanDTO> loans = repo.findAll()
				.stream()
				.map(loan -> modelMapper.map(loan, LoanDTO.class))
				.collect(Collectors.toList());

		return loans;
	}
	
	@RequestMapping(value="loan/dto/book/{bookId}")
	public List<LoanDTO> findLoanDTOsByBookId(@PathVariable Long bookId) {
		ModelMapper modelMapper = new ModelMapper();
		
		Book book = bookRepo.findById(bookId).get();
		List<LoanDTO> loansDtos = new ArrayList<LoanDTO>();
		for(BookCopy bookCopy : book.getBookcopies())
		{
			List<Loan> allLoans = repo.findByBookCopy(bookCopy);
			
			int lastIndex = loansDtos.size()-1;
			if(lastIndex < 0)
			{
				lastIndex = 0;
			}
			loansDtos.addAll(lastIndex, allLoans
					.stream()
					.map(loan -> modelMapper.map(loan, LoanDTO.class))
					.collect(Collectors.toList()));
					
		}
		
		return loansDtos;
	}
	
	@RequestMapping(value="loan/dto/bookcopy/{bookCopyId}")
	public List<LoanDTO> findLoanDTOsByBookCopyId(@PathVariable Long bookCopyId) {
		ModelMapper modelMapper = new ModelMapper();
		
		BookCopy bookCopy = bookCopyRepo.findById(bookCopyId).get();
		List<Loan> allLoans = repo.findByBookCopy(bookCopy);
		
		List<LoanDTO> loans = allLoans
				.stream()
				.map(loan -> modelMapper.map(loan, LoanDTO.class))
				.collect(Collectors.toList());
		
		return loans;
	}

	@PostMapping(value = "loan/{reservationId}/{copyId}/create")
	public void createLoanViaBookcopy(@PathVariable long reservationId, @PathVariable long copyId) {
		Loan loan = new Loan();
		BookCopy bookCopy = bookCopyRepo.findById(copyId).get();
		Reservation reservation = reservationRepo.findById(reservationId).get();
		
		loan.setBookCopy(bookCopy);
		loan.setDateLoaned(LocalDateTime.now());
		loan.setUser(reservation.getUser());
		
		repo.save(loan);
		reservationRepo.deleteById(reservation.getId());
		bookCopy.setStatus("loaned");
		bookCopyRepo.save(bookCopy);
	}
}

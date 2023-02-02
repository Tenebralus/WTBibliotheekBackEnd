package nl.workingtalent.backend.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Repositories.IBookRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnore;

import nl.workingtalent.backend.DTOs.BookCopyCreateRequestDTO;
import nl.workingtalent.backend.DTOs.BookCopyDTO;
import nl.workingtalent.backend.DTOs.BookCopyDetailsDTO;
import nl.workingtalent.backend.DTOs.BookCopyUpdateReturnDTO;
import nl.workingtalent.backend.DTOs.LoanDTO;
import nl.workingtalent.backend.DTOs.ReservationDTO;
import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Repositories.IBookCopyRepository;
import nl.workingtalent.backend.Repositories.ILoanRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookCopyController {
	
	@Autowired
	IBookCopyRepository repo;
	
	@Autowired
	ILoanRepository loanRepo;

	@Autowired
	IBookRepository bookRepo;
	
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
	public List<BookCopy> findByBook(@PathVariable Book book)
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

	@PostMapping(value = "book/{bookId}/createbookcopy")
	public void createBookCopyViaBook(@PathVariable(value = "bookId") Long bookId)
	{
		BookCopy bookCopy = new BookCopy();
		Book book = bookRepo.findById(bookId).get();
		List<BookCopy> copies = book.getBookcopies();
		int size = copies.size();
		// Voeg een exemplaar toe op basis van de bestaande exemplaren
		System.out.println(size);
		bookCopy.setBookCopyNr(size + 1);
		bookCopy.setBook(book);
		bookCopy.setStatus("available");
		repo.save(bookCopy);
	}
	
	@PostMapping(value = "book/createbookcopy")
	public void createBookCopyViaBook2(@RequestBody BookCopyCreateRequestDTO dto)
	{
		Book book = bookRepo.findById(dto.getId()).get();
		// Voeg een exemplaar toe op basis van de bestaande exemplaren
		for (int i=1; i<=dto.getBookCopyNumber(); i++) {
			BookCopy bookCopy = new BookCopy();
			List<BookCopy> copies = book.getBookcopies();
			int size = copies.size();
			System.out.println(size);
			bookCopy.setBookCopyNr(size + i + 1);
			bookCopy.setBook(book);
			bookCopy.setStatus("available");
			//copies.add(bookCopy);
			repo.save(bookCopy);
		}
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
		
		Loan loan = loanRepo.findById(loanId).get();
		
		loan.setDateReturned(LocalDateTime.now());
			
		repo.save(foundBookCopy);
		loanRepo.save(loan);
	}
	
	//not want to delete bookcopies, just edit them into archived
	@PutMapping(value = "bookcopy/archive/{id}")
	public void archiveBookCopy(@PathVariable long id)
	{
		BookCopy foundBookCopy = findById(id);
		if (foundBookCopy.getStatus().equals("archived")) {
			foundBookCopy.setStatus("available");
		} else {
			foundBookCopy.setStatus("archived");
		}
		repo.save(foundBookCopy);
	}
	
	@RequestMapping(value="bookcopy/details/all")
	List <BookCopyDTO> findAllBookCopyDTOs(){
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(BookCopy.class,BookCopyDTO.class).addMappings(mapper->{
			mapper.map(src->src.getBook().getAuthors(), BookCopyDTO::setBookAuthors);
			//mapper.map(src->src.getBook().getUrlImage(), BookCopyDTO::setUrlImage);
			//mapper.map(src->src.getLoans().getUser(), BookCopyDTO::setAuthors);
			//users autors en datums
		});
		
		List<BookCopyDTO> bookDTOs = repo.findAll()
				.stream()
				.map(copy -> modelMapper.map(copy, BookCopyDTO.class))
				.collect(Collectors.toList());
		return bookDTOs;
	}

	@RequestMapping(value = "bookcopy/details/{id}")
	public BookCopyDetailsDTO findBookCopyDetailsDTOById(@PathVariable long id) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(BookCopy.class, BookCopyDetailsDTO.class);
		
		BookCopy bookCopy = repo.findById(id).get();
		
		List<Loan> allLoans = loanRepo.findByBookCopy(bookCopy);
		int loanCount = allLoans.size();
		
		BookCopyDetailsDTO bookCopyDTO = modelMapper.map(bookCopy, BookCopyDetailsDTO.class);
		bookCopyDTO.setTimesLoaned(loanCount);
		for (Loan element : allLoans) {
			if (element.getDateReturned() == null) {
				bookCopyDTO.setCurrentLoan(element);
			}
		}
		bookCopyDTO.setBookTags(bookCopy.getBook().getTags());//opgelost, kannu tags meegeven
		bookCopyDTO.setBookAuthors(bookCopy.getBook().getAuthors());//opgelost, kannu tags meegeven
		//bookCopyDTO.setUrlImage(bookCopy.getBook().getUrlImage());//image via dto
		return bookCopyDTO;
		
	}
	
	@RequestMapping(value = "bookcopy/search/")
    public List<BookCopy> searchAllBookCopies() {
        return repo.findAll();
    }
    @RequestMapping(value = "bookcopy/search/{keyword}")
    public List<BookCopyDTO> searchAllBookCopies(@PathVariable String keyword) {
    	ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(BookCopy.class,BookCopyDTO.class).addMappings(mapper->{
			mapper.map(src->src.getBook().getAuthors(), BookCopyDTO::setBookAuthors);
			//mapper.map(src->src.getBook().getUrlImage(), BookCopyDTO::setUrlImage);
			//mapper.map(src->src.getLoans().get, BookCopyDTO::setUser);
			//users autors en datums
		});
		
		List<BookCopyDTO> bookDTOs = repo.search(keyword)
				.stream()
				.map(copy -> modelMapper.map(copy, BookCopyDTO.class))
				.collect(Collectors.toList());
		return bookDTOs;

    }
	


}

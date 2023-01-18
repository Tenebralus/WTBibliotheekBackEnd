package nl.workingtalent.backend.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import nl.workingtalent.backend.DTOs.BookCopyDetailsDTO;
import nl.workingtalent.backend.DTOs.BookDetailsDTO;
import nl.workingtalent.backend.DTOs.LoanDTO;
import nl.workingtalent.backend.Entities.Author;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.BookCopy;
import nl.workingtalent.backend.Entities.Loan;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Entities.Tag;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IBookCopyRepository;
import nl.workingtalent.backend.Repositories.IBookRepository;
import nl.workingtalent.backend.Repositories.ILoanRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class BookController {

	@Autowired
	private IBookRepository repo;
	
	@Autowired
	private ILoanRepository loanRepo;
	
	@Autowired
	private IBookCopyRepository bookcopyRepo;
	
	@RequestMapping(value = "book/all")
	public List<Book> findAllBooks()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "book/id/{id}")
	public Book findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}

	@RequestMapping(value = "book/search/")
    public List<Book> searchAllBooks() {
        return repo.findAll();
    }
    @RequestMapping(value = "book/search/{keyword}")
    public List<Book> searchAllBooks(@PathVariable String keyword) {
    		return repo.search(keyword);
    }
	
	@RequestMapping(value = "book/title/{title}")
	public List<Book> findByTitle(@PathVariable String title)
	{
		return repo.findByTitle(title);
	}
	
	@RequestMapping(value = "book/isbn/{isbn}")
	public List<Book> findByIsbn(@PathVariable String isbn)
	{
		return repo.findByIsbn(isbn);
	}
	
	@RequestMapping(value = "book/tags/{tags}")
	public List<Tag> findByTagsIn(@PathVariable List<Tag> tags)
	{
		return repo.findByTagsIn(tags);
	}
	
	@RequestMapping(value = "book/reservations/{reservations}")
	public List<Reservation> findByReservationsIn(@PathVariable List<Reservation> reservations)
	{
		return repo.findByReservationsIn(reservations);
	}
	
	@RequestMapping(value = "book/bookcopies/{bookcopies}")
	public List<BookCopy> findByBookcopiesIn(@PathVariable List<BookCopy> bookcopies)
	{
		return repo.findByBookcopiesIn(bookcopies);
	}
	
	@RequestMapping(value = "book/authors/{authors}")
	public List<Author> findByAuthorsIn(@PathVariable List<Author> authors)
	{
		return repo.findByAuthorsIn(authors);
	}
	
	@PostMapping(value = "book/create")
	public void createBook(@RequestBody Book book)
	{
		repo.save(book);
	}
	
	@PutMapping(value = "book/update/{id}")
	public void updateBook(@PathVariable long id, @RequestBody Book book)
	{
		Book foundBook = findById(id);
		foundBook.setTitle(book.getTitle());
		foundBook.setIsbn(book.getIsbn());
		foundBook.setTags(book.getTags());
		foundBook.setReservations(book.getReservations());
		foundBook.setBookcopies(book.getBookcopies());
		foundBook.setAuthors(book.getAuthors());
		repo.save(foundBook);
	}
	
	
	@RequestMapping(value= "book/details/{id}")
	public BookDetailsDTO findBookDetailsDTOById(@PathVariable long id) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Book.class, BookDetailsDTO.class);
		
		Book book = repo.findById(id).get();
		List<BookCopy> bookCopies = book.getBookcopies();	
		List<BookCopyDetailsDTO> bookCopyDTOs = new ArrayList<BookCopyDetailsDTO>();
		
		for(BookCopy bookCopy : bookCopies)
		{
			ModelMapper bookCopyModelMapper = new ModelMapper();
			
			bookCopyModelMapper.typeMap(BookCopy.class, BookCopyDetailsDTO.class);
					
			BookCopyDetailsDTO bookCopyDTO = bookCopyModelMapper.map(bookCopy, BookCopyDetailsDTO.class);
			List<Loan> allLoans = loanRepo.findByBookCopy(bookCopy);
			for (Loan loan : allLoans)
			{
				if (loan.getDateReturned() == null)
				{
					bookCopyDTO.setCurrentLoan(loan);
				}
			}
			bookCopyDTOs.add(bookCopyDTO);
		}
		
		BookDetailsDTO bookDTO = modelMapper.map(book, BookDetailsDTO.class);
		bookDTO.setBookAuthors(book.getAuthors());
		bookDTO.setBookCopyDetailsDTOs(bookCopyDTOs);
		
		return bookDTO;
	}
}

package nl.workingtalent.backend.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import nl.workingtalent.backend.Repositories.ITagRepository;
import nl.workingtalent.backend.Repositories.iAuthorRepository;
import org.hibernate.annotations.Cascade;
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
import nl.workingtalent.backend.DTOs.BookCreateRequestDTO;
import nl.workingtalent.backend.DTOs.BookCreateResponseDTO;
import nl.workingtalent.backend.DTOs.BookDetailsDTO;
import nl.workingtalent.backend.DTOs.LoanDTO;
import nl.workingtalent.backend.DTOs.LoginRequestDto;
import nl.workingtalent.backend.DTOs.LoginResponseDto;
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
    private iAuthorRepository authorRepo;

    @Autowired
    private ITagRepository tagRepo;

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
	public BookCreateResponseDTO createBook(@RequestBody BookCreateRequestDTO dto /*@RequestBody Book book*/){	
			
		Book book = new Book();
		book.setTitle(dto.getTitle());
		book.setIsbn(dto.getIsbn());
		
		if(repo.findByTitle(book.getTitle()).size() != 0) {
			return new BookCreateResponseDTO(false, "A book with this title already exist");
		}else if (repo.findByIsbn(book.getIsbn()).size() != 0) {
			return new BookCreateResponseDTO(false, "A book with this isbn number already exists");
		}
		
		List<Author> authors = new ArrayList<Author>();
		
		for (String author : dto.getAuthors()) {
			String AuthorFirstName = "";
			String AuthorLastName = "";
			
			int i = 0;
			for (String name : author.split(" ", 0)) {
				if(i == 0) {
					AuthorFirstName = name;
				}else if(i > 1){
					AuthorLastName = AuthorLastName + " " + name;
				}else {
					AuthorLastName = AuthorLastName + name;
				}
				i++;
			}
			
			System.out.println(AuthorFirstName + " " + AuthorLastName);
			Optional<Author> optionaAuthor = authorRepo.findByFirstNameAndLastName(AuthorFirstName, AuthorLastName);
			
			Author newAuthor;
			if (optionaAuthor.isEmpty()) {
				newAuthor = new Author();
				newAuthor.setFirstName(AuthorFirstName);
				newAuthor.setLastName(AuthorLastName);
				authorRepo.save(newAuthor);
				authors.add(newAuthor);
			}else {
				newAuthor = optionaAuthor.get();
				if(!authors.contains(newAuthor)) {
					authors.add(newAuthor);
				}
			}
		}
		
		book.setAuthors(authors);

		List<Tag> tags = new ArrayList<Tag>();
		
		for (String tag : dto.getTags()) {		
			List<Tag> foundTags = tagRepo.findByName(tag);
			
			Tag newTag;
			if(foundTags.size() == 0) {
				newTag = new Tag();
				newTag.setName(tag);
				tagRepo.save(newTag);
				tags.add(newTag);
			}else {
				newTag = foundTags.get(0);
				if(!tags.contains(newTag)) {
					tags.add(newTag);
				}
			}
		}
			
		book.setTags(tags);
			
		repo.save(book);
		
		List<BookCopy> copies = new ArrayList<BookCopy>();
		for(int i = 0; i < dto.getBookCopyNumber(); i++) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(book);
			
			//int size = copies.size();
			copies.add(bookCopy);
			//book.setBookcopies(copies);
			
			// Voeg een exemplaar toe op basis van de bestaande exemplaren
			bookCopy.setBookCopyNr(i + 1);
			bookCopy.setBook(book);
			bookCopy.setStatus("available");
			
			bookcopyRepo.save(bookCopy);
		}
		book.setBookcopies(copies);
		
		repo.save(book);
		
		return new BookCreateResponseDTO(true, "");
	}

	@PutMapping(value = "book/update/{id}")
	public void updateBook(@PathVariable long id, @RequestBody Book book) {
		Book foundBook = findById(id);
		List<Author> authors = foundBook.getAuthors();
		System.out.println(authors);
		foundBook.setTitle(book.getTitle());
		foundBook.setIsbn(book.getIsbn());
//        foundBook.setTags(book.getTags());
//        foundBook.setReservations(book.getReservations());
//        foundBook.setBookcopies(book.getBookcopies());
//        foundBook.setAuthors(book.getAuthors());
		repo.save(foundBook);
	}

	@PutMapping(value = "book/delete/author/{bookId}/{authorId}")
	public void deleteAuthorFromBook(@PathVariable long bookId, @PathVariable long authorId) {
		Book foundBook = findById(bookId);
		List<Author> authors = foundBook.getAuthors();
		Author author = authorRepo.findById(authorId).get();
		authors.remove(author);
		foundBook.setAuthors(authors);
		repo.save(foundBook);
	}

	@PutMapping(value = "book/delete/tag/{bookId}/{tagId}")
	public void deleteTagFromBook(@PathVariable long bookId, @PathVariable long tagId) {
		Book foundBook = findById(bookId);
		List<Tag> tags = foundBook.getTags();
		Tag tag = tagRepo.findById(tagId).get();
		tags.remove(tag);
		foundBook.setTags(tags);
		repo.save(foundBook);
	}

	@PostMapping(value = "book/add/tag/{bookId}")
	public void addTagToBook(@PathVariable long bookId, @RequestBody Tag tag) {
		Book foundBook = findById(bookId);
		List<Tag> tags = foundBook.getTags();
		List<Tag> newTag = tagRepo.findByName(tag.getName());
		//Optional<Tag> optionalTag = tagRepo.findByName(tag.getName());
		
		Tag newTag2 = new Tag();
		
		if(newTag.size() == 0) {
			newTag2 = new Tag();
			newTag2.setName(tag.getName());
			tagRepo.save(newTag2);
		}else {
			newTag2 = newTag.get(0);
		}
		
		/*Tag newTag = new Tag();
		
		if (optionalTag.isEmpty()) {
			newTag = new Tag();
			newTag.setName(tag.getName());
			tagRepo.save(newTag);
			//return;
		}else {
			newTag = optionalTag.get();
		}
		*/
		
		
		if(!tags.contains(newTag2)) {
			tags.add(newTag2);
		}
		
		foundBook.setTags(tags);
		repo.save(foundBook);
	}

	@PostMapping(value = "book/add/author/{bookId}")
	public void addAuthorToBook(@PathVariable long bookId, @RequestBody Author author) {
		Book foundBook = findById(bookId);
		List<Author> authors = foundBook.getAuthors();
		//Author newAuthor = authorRepo.findBylastName(author.getLastName()).get(0);
		Optional<Author> optionalAuthor = authorRepo.findByFirstNameAndLastName(author.getFirstName(),author.getLastName());
		
		Author newAuthor = new Author();
		
		if (optionalAuthor.isEmpty()) {
			newAuthor = new Author();
			newAuthor.setFirstName(author.getFirstName());
			newAuthor.setLastName(author.getLastName());
			authorRepo.save(newAuthor);
			//return;
		}else {
			newAuthor = optionalAuthor.get();
		}
		
		if(!authors.contains(newAuthor)){
			authors.add(newAuthor);
		}
		
		foundBook.setAuthors(authors);
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
		bookDTO.setBookTag(book.getTags());//opgelost, kannu tags meegeven
		return bookDTO;
	}
}

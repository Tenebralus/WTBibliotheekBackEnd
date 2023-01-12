package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Entities.User;
import nl.workingtalent.backend.Repositories.IReservationRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class ReservationController {
	
	@Autowired
	private IReservationRepository repo;
	
	/*@Autowired
	private IBookRepository bookRepo;
	
	@Autowired
	private IUserRepository userRepo;*/
	
	@RequestMapping(value = "reservation/all")
	public List<Reservation> findAllReservations()
	{
		return repo.findAll();
	}
	
	@RequestMapping(value = "reservation/id/{id}")
	public Reservation findById(@PathVariable long id)
	{
		return repo.findById(id).get();
	}
	
	@GetMapping("/reservation/{bookId}")
	public List<Reservation> findByBookId(@PathVariable(value="bookId") Long bookId) {
		return repo.findByBookId(bookId);
	}
	
	@GetMapping("/user/{userId}/reservations")

	public List<Reservation> findByUserId(@PathVariable(value= "userId") Long userId) {
		return repo.findByUserId(userId);
	}
	
	//variations to create
	@PostMapping("book/{bookId}/createreservation")
	public void createReservationViaBook(@PathVariable(value = "bookId") Long bookId) {
		Reservation reservation = new Reservation();
		Book book = new Book();
		book.setId(bookId);
		reservation.setBook(book);
		reservation.setDateReserved(LocalDateTime.now());
		repo.save(reservation);
	}
	
	//standard create update & delete
	@PostMapping("reservation/create")
	public void createReservation(@PathVariable Reservation reservation)
	{
		repo.save(reservation);
	}
	

	@PutMapping(value = "reservation/update/{id}")
	public void updateReservation(@PathVariable long id, @RequestBody Reservation reservation) 
	{
		Reservation foundReservation = findById(id);
		//foundReservation.setDateReserved(reservation.getDateReserved());
		//probably you do not want to edit times in the past
		foundReservation.setBook(reservation.getBook());
		foundReservation.setUser(reservation.getUser());
		repo.save(foundReservation);
	}
	
	@DeleteMapping(value = "reservation/delete/{id}")
	public void deleteReservation(@PathVariable long id)
	{
		repo.deleteById(id);
	}

	
}

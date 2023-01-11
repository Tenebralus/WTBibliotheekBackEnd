package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.Entities.Book;
import nl.workingtalent.backend.Entities.Reservation;
import nl.workingtalent.backend.Repositories.IBookRepository;
import nl.workingtalent.backend.Repositories.IReservationRepository;
import nl.workingtalent.backend.Repositories.IUserRepository;

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
	
	@GetMapping("/user/{userId}/reservations")
	public List<Reservation> getReservationsByUserId(@PathVariable(value= "userId") Long userId) {
		return repo.findByUserId(userId);
	}
	
	@PostMapping("book/{bookId}/createreservation")
	public Reservation createReservation(@PathVariable(value = "bookId") Long bookId) {
		Reservation reservation = new Reservation();
		Book book = new Book();
		book.setId(bookId);
		reservation.setBook(book);
		reservation.setDateReserved(LocalDateTime.now());
		
		return repo.save(reservation);
	}
	
	//update & delete
	
}

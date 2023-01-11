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
	private IReservationRepository reservationRepo;
	
	@Autowired
	private IBookRepository bookRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@GetMapping("/user/{userId}/reservations")
	public List<Reservation> getReservationsByUserId(@PathVariable Long userId) {
		return reservationRepo.findByUserId(userId);
	}
	
	
	@PostMapping("book/{bookId}/createreservation")
	public Reservation createReservation(@PathVariable Long bookId) {
		Reservation reservation = new Reservation();
		Book book = new Book();
		book.setId(bookId);
		reservation.setBook(book);
		reservation.setDateReserved(LocalDateTime.now());
		
		return reservationRepo.save(reservation);
	}
	
	@RequestMapping("reservation/all")
	public List<Reservation> findAllReservations() {
		return reservationRepo.findAll();
	}
	
	@GetMapping("/reservation/{bookId}")
	public List<Reservation> findByBookId(@PathVariable Long bookId) {
		return reservationRepo.findByBookId(bookId);
	}
	
}

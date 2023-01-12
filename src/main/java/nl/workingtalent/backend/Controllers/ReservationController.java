package nl.workingtalent.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import nl.workingtalent.backend.Entities.ReservationDTO;
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
	
	//DTO voorbeeld
	@RequestMapping(value= "reservation/dto")
	public List<ReservationDTO> findAllReservationDTOs() {
		//Modelmapper is een package die DTOs makkelijker maakt
		ModelMapper modelMapper = new ModelMapper();
		
		//Modelmapper probeert zelf uit te vinden welke gegevens van de 'echte' class in de DTO horen, maar als ie het niet snapt kan je handmatig 
		//relaties aangeven met typeMap
		modelMapper.typeMap(Reservation.class, ReservationDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getUser().getFirstName(), 
					ReservationDTO::setFirstName);
			mapper.map(src -> src.getUser().getLastName(), 
					ReservationDTO::setLastName);
			mapper.map(src -> src.getBook().getBookcopies(), 
					ReservationDTO::setBookcopies);
		});
		
		//Dit is nodig om lijsten van objecten te DTOen
		List<ReservationDTO> reservations = repo.findAll()
				.stream()
				.map(reserv -> modelMapper.map(reserv, ReservationDTO.class))
				.collect(Collectors.toList());
		
		 return reservations;
	}

	
}

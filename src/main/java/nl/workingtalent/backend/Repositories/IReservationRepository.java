package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nl.workingtalent.backend.DTOs.ReservationUserDTO;
import nl.workingtalent.backend.Entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long>{
	List<Reservation> findByUserId(Long userId);
	List<Reservation> findByBookId(Long bookId);
	
	@Query("SELECT DISTINCT r FROM Reservation r JOIN r.book b LEFT JOIN r.user u "
			+ "WHERE b.title LIKE %?1% "
			+ "OR CONCAT(u.firstName, ' ', u.lastName) LIKE %?1%")
	public List<Reservation> search(String keyword);
	
	
	@Query("SELECT DISTINCT r FROM Reservation r JOIN r.book b LEFT JOIN b.authors a "
			+ "WHERE b.title LIKE %?1% "
			+ "OR CONCAT(a.firstName, ' ', a.lastName)  LIKE %?1%" )
	public List<Reservation> searchPerUser(String keyword);
 }

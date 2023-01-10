package nl.workingtalent.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.workingtalent.backend.Entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long>{
	List<Reservation> findByUserId(Long userId);
	List<Reservation> findByBookId(Long bookId);
 }

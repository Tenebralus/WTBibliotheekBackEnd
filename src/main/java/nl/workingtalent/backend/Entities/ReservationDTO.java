package nl.workingtalent.backend.Entities;

import java.time.LocalDateTime;

public class ReservationDTO {
	String bookTitle;
	
	String userName;
	
	LocalDateTime reservationDate;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	
}

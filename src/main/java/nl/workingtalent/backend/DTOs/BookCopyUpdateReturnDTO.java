package nl.workingtalent.backend.DTOs;

import java.time.LocalDateTime;

public class BookCopyUpdateReturnDTO {
	
	private String status;
	
	private Long loanId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
}

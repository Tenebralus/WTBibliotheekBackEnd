package nl.workingtalent.backend.DTOs;

public class ReservationResponseDTO {
	private boolean duplicate;
	
	private boolean success;
	
	public ReservationResponseDTO(boolean duplicate, boolean success) {
		super();
		this.duplicate = duplicate;
		this.success = success;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
}

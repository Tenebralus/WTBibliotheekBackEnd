package nl.workingtalent.backend.DTOs;

public class ChangePasswordResponseDTO {
	boolean succes;
	
	String errorMessage;

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ChangePasswordResponseDTO(boolean succes, String errorMessage) {
		super();
		this.succes = succes;
		this.errorMessage = errorMessage;
	}
	
	
}

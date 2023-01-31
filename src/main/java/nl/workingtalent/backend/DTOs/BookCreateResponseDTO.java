package nl.workingtalent.backend.DTOs;

public class BookCreateResponseDTO {
 boolean succes;
 
 String err;

	public boolean isSucces() {
		return succes;
	}
	
	public void setSucces(boolean succes) {
		this.succes = succes;
	}
	
	public String getErr() {
		return err;
	}
	
	public void setErr(String err) {
		this.err = err;
	}

	public BookCreateResponseDTO(boolean succes, String err) {
		super();
		this.succes = succes;
		this.err = err;
	}
	 
	
}

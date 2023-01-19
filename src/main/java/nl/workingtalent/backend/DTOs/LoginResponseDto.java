package nl.workingtalent.backend.DTOs;

import nl.workingtalent.backend.Entities.User;

public class LoginResponseDto {

	private boolean success;
	
	private String token;
	
	private boolean admin;
	
	public LoginResponseDto(boolean success, User user) {
		super();
		this.success = success;
		this.token = user.getToken();
		this.admin = user.isAdmin();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}

package com.ats.manoharweb.models;

public class LoginResponse {
	MUser user;
	ErrorMessage errorMessage;
	public MUser getUser() {
		return user;
	}
	public void setUser(MUser user) {
		this.user = user;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "LoginResponse [user=" + user + ", errorMessage=" + errorMessage + "]";
	}

}

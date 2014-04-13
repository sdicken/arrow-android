package com.arrowfoodcouriers.arrowfood.Models;

public class Response 
{
	private String error;
	private boolean success;
	private static final String USERNAME_TAKEN = "Username already in use";
	private static final String EMAIL_TAKEN = "Email already in use";
	
	public Response(String error, boolean success)
	{
		this.error = error;
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public boolean isUsernameTaken(String message)
	{
		return message.equals(USERNAME_TAKEN);
	}
	
	public boolean isEmailTaken(String message)
	{
		return message.equals(EMAIL_TAKEN);
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

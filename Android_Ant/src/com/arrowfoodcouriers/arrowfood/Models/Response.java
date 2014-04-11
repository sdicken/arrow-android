package com.arrowfoodcouriers.arrowfood.Models;

public class Response 
{
	private String error;
	private static final String USERNAME_TAKEN = "Username already in use";
	private static final String EMAIL_TAKEN = "Email already in use";
	
	public Response(String error)
	{
		this.error = error;
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
}

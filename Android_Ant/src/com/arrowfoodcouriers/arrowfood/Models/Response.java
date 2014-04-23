package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Response 
{
	private String error;
	private boolean success;
	private Date updated;
	private static final String USERNAME_TAKEN = "Username already in use";
	private static final String EMAIL_TAKEN = "Email already in use";
	
	public Response(String error, boolean success, Date updated)
	{
		this.error = error;
		this.success = success;
		this.updated = updated;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}

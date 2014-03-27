package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class PasswordReset 
{
	private final String username;
	private final String token;
	private final Date expiration;
	private final Date createdOn;
	
	public PasswordReset(String username, String token, Date expiration, Date createdOn)
	{
		this.username = username;
		this.token = token;
		this.expiration = expiration;
		this.createdOn = createdOn;
	}
}

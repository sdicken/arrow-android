package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class PasswordReset 
{
	private String username;
	private String token;
	private Date expiration;
	private Date created;
	
	public PasswordReset(String username, String token, Date expiration, Date createdOn)
	{
		this.username = username;
		this.token = token;
		this.expiration = expiration;
		this.created = createdOn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

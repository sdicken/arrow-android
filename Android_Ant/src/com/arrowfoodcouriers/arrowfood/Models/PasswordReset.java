package com.arrowfoodcouriers.arrowfood.Models;


public class PasswordReset 
{
	private String username;
	private String token;
	private Long expiration;
	private Long created;
	
	public PasswordReset(String username, String token, Long expiration, Long created)
	{
		this.username = username;
		this.token = token;
		this.expiration = expiration;
		this.created = created;
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

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	
}

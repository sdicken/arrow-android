package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;


public class OrderStatus 
{
	private String authority;
	private String state;
	private Date created;
	
	public OrderStatus(String authority, String state, Date created)
	{
		this.authority = authority;
		this.state = state;
		this.created = created;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}

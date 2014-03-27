package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class OrderStatus 
{
	private final String authority;
	private final String state;
	private final Date createdOn;
	
	public OrderStatus(String authority, String state, Date createdOn)
	{
		this.authority = authority;
		this.state = state;
		this.createdOn = createdOn;
	}
}

package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Cart 
{
	private final String username;
	private final Date createdOn;
	private final Date lastUpdate;
	private final CartItem[] items;
	
	public Cart(String username,
			Date createdOn,
			Date lastUpdate,
			CartItem[] items)
	{
		this.username = username;
		this.createdOn = createdOn;
		this.lastUpdate = lastUpdate;
		this.items = items;
	}
}

package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Cart 
{
	private String username;
	private Date created;
	private Date lastUpdate;
	private CartItem[] items;
	
	public Cart(String username,
			Date created,
			Date lastUpdate,
			CartItem[] items)
	{
		this.username = username;
		this.created = created;
		this.lastUpdate = lastUpdate;
		this.items = items;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public CartItem[] getItems() {
		return items;
	}

	public void setItems(CartItem[] items) {
		this.items = items;
	}
}

package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Order 
{
	private final String username;
	private final Date createdOn;
	private final Address billing;
	private final Address shipping;
	private final OrderStatus status;
	private final Cart cart;
	
	public Order(String username, Date createdOn, Address billing, Address shipping, OrderStatus status, Cart cart)
	{
		this.username = username;
		this.createdOn = createdOn;
		this.billing = billing;
		this.shipping = shipping;
		this.status = status;
		this.cart = cart;
	}
}

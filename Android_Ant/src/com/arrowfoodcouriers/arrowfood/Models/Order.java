package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Order 
{
	private String username;
	private Date created;
	private Address billing;
	private Address shipping;
	private OrderStatus status;
	private Cart cart;
	
	public Order(String username, Date created, Address billing, Address shipping, OrderStatus status, Cart cart)
	{
		this.username = username;
		this.created = created;
		this.billing = billing;
		this.shipping = shipping;
		this.status = status;
		this.cart = cart;
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

	public Address getBilling() {
		return billing;
	}

	public void setBilling(Address billing) {
		this.billing = billing;
	}

	public Address getShipping() {
		return shipping;
	}

	public void setShipping(Address shipping) {
		this.shipping = shipping;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}

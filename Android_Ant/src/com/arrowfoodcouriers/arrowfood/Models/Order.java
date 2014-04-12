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
	private Double rating;
	private String comment;
	
	public Order(String username, 
			Date created, 
			Address billing, 
			Address shipping, 
			OrderStatus status, 
			Cart cart,
			Double rating,
			String comment)
	{
		this.username = username;
		this.created = created;
		this.billing = billing;
		this.shipping = shipping;
		this.status = status;
		this.cart = cart;
		this.rating = rating;
		this.comment = comment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

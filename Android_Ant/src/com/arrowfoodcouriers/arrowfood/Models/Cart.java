package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Cart 
{
	private String username;
	private Date created;
	private Date updated;
	private CartItem[] items;
	private Coupon[] coupons;
	private Double total;
	
	public Cart(String username,
			Date created,
			Date updated,
			CartItem[] items,
			Coupon[] coupons,
			Double total)
	{
		this.username = username;
		this.created = created;
		this.updated = updated;
		this.items = items;
		this.coupons = coupons;
		this.total = total;
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

	public CartItem[] getItems() {
		return items;
	}

	public void setItems(CartItem[] items) {
		this.items = items;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Coupon[] getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupon[] coupons) {
		this.coupons = coupons;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}

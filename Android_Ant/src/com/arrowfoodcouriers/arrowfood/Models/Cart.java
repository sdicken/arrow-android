package com.arrowfoodcouriers.arrowfood.Models;


public class Cart 
{
	private String username;
	private Long created;
	private Long updated;
	private CartItem[] items;
	private Coupon[] coupons;
	private Double total;
	
	public Cart(String username,
			Long created,
			Long updated,
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

	public CartItem[] getItems() {
		return items;
	}

	public void setItems(CartItem[] items) {
		this.items = items;
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

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}
}

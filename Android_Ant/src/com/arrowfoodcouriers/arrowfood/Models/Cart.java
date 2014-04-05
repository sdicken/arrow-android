package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;


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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coupons);
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + Arrays.hashCode(items);
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cart)) {
			return false;
		}
		Cart other = (Cart) obj;
		if (!Arrays.equals(coupons, other.coupons)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (!Arrays.equals(items, other.items)) {
			return false;
		}
		if (total == null) {
			if (other.total != null) {
				return false;
			}
		} else if (!total.equals(other.total)) {
			return false;
		}
		if (updated == null) {
			if (other.updated != null) {
				return false;
			}
		} else if (!updated.equals(other.updated)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
}

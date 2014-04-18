package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;


public class CartItem 
{
	private String menu;
	private String restaurant;
	private String item;
	private CartItemOption[] itemOptions;
	private Integer quantity;
	private Double total;
	private Date created;
	private Date updated;
	
	public CartItem(String menu, 
			String restaurant,
			String item, 
			CartItemOption[] itemOptions,
			Integer quantity, 
			Double total,
			Date created,
			Date updated)
	{
		this.menu = menu;
		this.restaurant = restaurant;
		this.item = item;
		this.itemOptions = itemOptions;
		this.quantity = quantity;
		this.total = total;
		this.created = created;
		this.updated = updated;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
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
		if (!(obj instanceof CartItem)) {
			return false;
		}
		CartItem other = (CartItem) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (item == null) {
			if (other.item != null) {
				return false;
			}
		} else if (!item.equals(other.item)) {
			return false;
		}
		if (menu == null) {
			if (other.menu != null) {
				return false;
			}
		} else if (!menu.equals(other.menu)) {
			return false;
		}
		if (quantity == null) {
			if (other.quantity != null) {
				return false;
			}
		} else if (!quantity.equals(other.quantity)) {
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
		return true;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public CartItemOption[] getItemOptions() {
		return itemOptions;
	}

	public void setItemOptions(CartItemOption[] itemOptions) {
		this.itemOptions = itemOptions;
	}
}

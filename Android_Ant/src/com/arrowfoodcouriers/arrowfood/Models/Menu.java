package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;
import java.util.Date;

import com.arrowfoodcouriers.arrowfood.Interfaces.IUpdateable;


public class Menu implements IUpdateable
{
	private String name;
	private String restaurant;
	private byte[] image;
	private byte[] icon;
	private MenuItem[] items;
	private String[] tags;
	private Date updated;
	private Date created;
	private Integer orders;
	
	public Menu(String name, 
			String restaurant, 
			byte[] image,
			byte[] icon,
			MenuItem[] items,
			String[] tags, 
			Date updated, 
			Date created, 
			Integer orders)
	{
		this.name = name;
		this.restaurant = restaurant;
		this.image = image;
		this.icon = icon;
		this.items = items;
		this.tags = tags;
		this.updated = updated;
		this.created = created;
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public MenuItem[] getItems() {
		return items;
	}

	public void setItems(MenuItem[] items) {
		this.items = items;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + Arrays.hashCode(icon);
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Arrays.hashCode(items);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + Arrays.hashCode(tags);
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
		if (!(obj instanceof Menu)) {
			return false;
		}
		Menu other = (Menu) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (!Arrays.equals(icon, other.icon)) {
			return false;
		}
		if (!Arrays.equals(image, other.image)) {
			return false;
		}
		if (!Arrays.equals(items, other.items)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (orders == null) {
			if (other.orders != null) {
				return false;
			}
		} else if (!orders.equals(other.orders)) {
			return false;
		}
		if (restaurant == null) {
			if (other.restaurant != null) {
				return false;
			}
		} else if (!restaurant.equals(other.restaurant)) {
			return false;
		}
		if (!Arrays.equals(tags, other.tags)) {
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
}

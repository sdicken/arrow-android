package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Arrays;
import java.util.Date;


public class MenuItem 
{
	private String name;
	private Double price;
	private byte[] image;
	private byte[] icon;
	private String[] tags;
	private MenuItemOption[] itemOptions;
	private String description;
	private Date created;
	private Integer orders;
	
	public MenuItem(String name,
			Double price,
			byte[] image,
			byte[] icon,
			String[] tags,
			MenuItemOption[] itemOptions,
			String description,
			Date created,
			Integer orders)
	{
		this.name = name;
		this.price = price;
		this.image = image;
		this.icon = icon;
		this.tags = tags;
		this.itemOptions = itemOptions;
		this.description = description;
		this.created = created;
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public MenuItemOption[] getItemOptions() {
		return itemOptions;
	}

	public void setItemOptions(MenuItemOption[] itemOptions) {
		this.itemOptions = itemOptions;
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
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(icon);
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Arrays.hashCode(itemOptions);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + Arrays.hashCode(tags);
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
		if (!(obj instanceof MenuItem)) {
			return false;
		}
		MenuItem other = (MenuItem) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (!Arrays.equals(icon, other.icon)) {
			return false;
		}
		if (!Arrays.equals(image, other.image)) {
			return false;
		}
		if (!Arrays.equals(itemOptions, other.itemOptions)) {
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
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (!Arrays.equals(tags, other.tags)) {
			return false;
		}
		return true;
	}
}

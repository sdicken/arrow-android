package com.arrowfoodcouriers.arrowfood.Models;


public class Menu 
{
	private String name;
	private String restaurant;
	private byte[] image;
	private byte[] icon;
	private MenuItem[] items;
	private String[] tags;
	private Long updated;
	private Long created;
	private Integer orders;
	
	public Menu(String name, 
			String restaurant, 
			byte[] image,
			byte[] icon,
			MenuItem[] items,
			String[] tags, 
			Long updated, 
			Long created, 
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

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
}

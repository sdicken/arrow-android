package com.arrowfoodcouriers.arrowfood.Models;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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
}

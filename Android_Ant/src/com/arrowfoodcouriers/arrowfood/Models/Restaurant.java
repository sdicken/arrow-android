package com.arrowfoodcouriers.arrowfood.Models;


public class Restaurant 
{
	private String name;
	private byte[] image;
	private byte[] icon;
	private String description;
	private String[] tags;
	private Email[] emails;
	private Phone[] phones;
	private Address[] addresses;
	private Long created;
	private Long updated;
	private Integer orders;
	
	public Restaurant(String name,
			byte[] image,
			byte[] icon,
			String description,
			String[] tags,
			Email[] emails,
			Phone[] phones,
			Address[] addresses,
			Long created,
			Long updated,
			Integer orders)
	{
		this.name = name;
		this.image = image;
		this.icon = icon;
		this.description = description;
		this.tags = tags;
		this.emails = emails;
		this.phones = phones;
		this.addresses = addresses;
		this.created = created;
		this.updated = updated;
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Email[] getEmails() {
		return emails;
	}

	public void setEmails(Email[] emails) {
		this.emails = emails;
	}

	public Phone[] getPhones() {
		return phones;
	}

	public void setPhones(Phone[] phones) {
		this.phones = phones;
	}

	public Address[] getAddresses() {
		return addresses;
	}

	public void setAddresses(Address[] addresses) {
		this.addresses = addresses;
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

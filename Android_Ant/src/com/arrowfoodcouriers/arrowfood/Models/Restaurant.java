package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Restaurant 
{
	private String name;
	private String description;
	private String[] tags;
	private Email[] emails;
	private Phone[] phones;
	private Address[] addresses;
	private Date created;
	private Date updated;
	private Integer orders;
	
	public Restaurant(String name,
			String description,
			String[] tags,
			Email[] emails,
			Phone[] phones,
			Address[] addresses,
			Date created,
			Date updated,
			Integer orders)
	{
		this.name = name;
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

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
}

package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Email 
{
	private String name;
	private String address;
	private Date created;
	
	public Email(String name, String address, Date created)
	{
		this.name = name;
		this.address = address;
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

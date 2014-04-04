package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Phone 
{
	private String name;
	private String number;
	private Date created;
	
	public Phone(String name, String number, Date created)
	{
		this.name = name;
		this.number = number;
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

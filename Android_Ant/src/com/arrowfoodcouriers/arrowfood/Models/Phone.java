package com.arrowfoodcouriers.arrowfood.Models;


public class Phone 
{
	private String name;
	private String number;
	private Long created;
	
	public Phone(String name, String number, Long created)
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

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
}

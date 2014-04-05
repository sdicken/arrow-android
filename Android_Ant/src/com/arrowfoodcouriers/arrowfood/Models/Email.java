package com.arrowfoodcouriers.arrowfood.Models;


public class Email 
{
	private String name;
	private String address;
	private Long created;
	
	public Email(String name, String address, Long created)
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

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	
}

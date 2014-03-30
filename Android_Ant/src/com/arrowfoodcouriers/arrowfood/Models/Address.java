package com.arrowfoodcouriers.arrowfood.Models;


public class Address
{
	private final String address1;
	private final String address2;
	private final String city;
	private final String state;
	private final String zip;
	
	public Address(String address1,
			String address2,
			String city,
			String state,
			String zip)
	{
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
}

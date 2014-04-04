package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class User {
	private String userName;
	private String role;
	private String email;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private Date created;
	
	public User(String userName, String role, String email, String name,
			String address1, String address2, String city, String state,
			String zip, Date created ) {
		
		this.userName = userName;
		this.role = role;
		this.email = email;
		this.name = name;
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCreatedOn(created);
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getCreatedOn() {
		return created;
	}

	public void setCreatedOn(Date createdOn) {
		this.created = createdOn;
	}
}

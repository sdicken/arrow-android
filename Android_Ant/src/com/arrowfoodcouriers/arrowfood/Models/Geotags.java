package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Geotags 
{
	private String username;
	private Double latitude;
	private Double longitude;
	private Date created;
	
	public Geotags(String username, Double latitude, Double longitude, Date created)
	{
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
		this.created = created;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}

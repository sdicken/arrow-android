package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Geotags 
{
	private final String username;
	private final Double latitude;
	private final Double longitude;
	private final Date createdOn;
	
	public Geotags(String username, Double latitude, Double longitude, Date createdOn)
	{
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createdOn = createdOn;
	}
}

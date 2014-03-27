package com.arrowfoodcouriers.arrowfood;

/**
 * Created by Ryan on 2/18/14.
 */
public class RestaurantListObject 
{
    private final String name;
    private final String subtitle;
    private final String details;
    private int image;

    public RestaurantListObject(String name, String subtitle, String details, int image) 
    {
        this.name = name;
        this.subtitle = subtitle;
        this.details = details;
        this.image = image;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public String getSubtitle()
    {
    	return subtitle;
    }
    
    public String getDetails()
    {
    	return details;
    }
    
    public int getImage()
    {
    	return image;
    }
}

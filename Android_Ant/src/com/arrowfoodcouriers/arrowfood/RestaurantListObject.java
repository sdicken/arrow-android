package com.arrowfoodcouriers.arrowfood;

/**
 * Created by Ryan on 2/18/14.
 */
public class RestaurantListObject 
{
    private String name;
    private String subtitle;
    private int image;

    public RestaurantListObject(String name, String subtitle, int image) 
    {
        this.name = name;
        this.subtitle = subtitle;
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
    
    public int getImage()
    {
    	return image;
    }
}

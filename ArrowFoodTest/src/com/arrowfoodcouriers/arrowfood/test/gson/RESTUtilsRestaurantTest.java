package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsRestaurantTest 
{
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
	}
	
	@Test
	public void testGetRestaurants()
	{
		ResponseEntity<String> response = Utils.getMenus();
		Restaurant[] restaurants = Utils.convertResponseEntityToModel(response, Restaurant[].class);
		assertNotNull(restaurants);
	}
}

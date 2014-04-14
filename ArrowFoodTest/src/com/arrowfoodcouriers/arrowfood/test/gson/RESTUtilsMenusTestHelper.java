package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsMenusTestHelper 
{
	private final RESTUtils utils;
	
	public RESTUtilsMenusTestHelper(RESTUtils utils)
	{
		this.utils = utils;
	}
	
	public void testGetMenus()
	{
		ResponseEntity<String> response = utils.getMenus();
		Menu[] menus = RESTUtils.convertResponseEntityToModel(response, Menu[].class);
		assertNotNull(menus);
	}
}

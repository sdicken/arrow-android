package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsMenusTestHelper 
{
	public void testGetMenus()
	{
		Menu[] menus = RESTUtils.getMenus();
		assertNotNull(menus);
	}
}

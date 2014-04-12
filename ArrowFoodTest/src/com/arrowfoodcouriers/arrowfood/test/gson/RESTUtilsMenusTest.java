package com.arrowfoodcouriers.arrowfood.test.gson;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.springframework.http.ResponseEntity;

import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Models.Menu;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsMenusTest 
{
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
	}
	
	@Test
	public void testGetMenus()
	{
		ResponseEntity<String> response = Utils.getMenus();
		Menu[] menus = Utils.convertResponseEntityToModel(response, Menu[].class);
		assertNotNull(menus);
	}
}

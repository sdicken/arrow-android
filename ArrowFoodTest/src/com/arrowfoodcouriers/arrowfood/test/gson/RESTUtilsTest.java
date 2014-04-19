package com.arrowfoodcouriers.arrowfood.test.gson;

import java.net.CookieHandler;
import java.net.CookieManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsTest 
{
	private RESTUtilsUserTestHelper userTestHelper;
	private RESTUtilsMenusTestHelper menuTestHelper;
	private RESTUtilsCartTestHelper cartTestHelper;
	
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
		
		CookieHandler.setDefault(new CookieManager());
		
		userTestHelper = new RESTUtilsUserTestHelper();
		menuTestHelper = new RESTUtilsMenusTestHelper();
		cartTestHelper = new RESTUtilsCartTestHelper();
	}
	
	@Test
	public void testHappyPath()
	{
		userTestHelper.testUserRegistrationSucceeds();
		userTestHelper.testLoginSuccess();
		menuTestHelper.testGetMenus();
		cartTestHelper.testPlaceOrderSuccess();
	}
}

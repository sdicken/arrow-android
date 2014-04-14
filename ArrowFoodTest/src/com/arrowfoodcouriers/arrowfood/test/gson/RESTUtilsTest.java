package com.arrowfoodcouriers.arrowfood.test.gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

import com.arrowfoodcouriers.arrowfood.RESTUtils;

@RunWith(RobolectricTestRunner.class)
public class RESTUtilsTest 
{
	private RESTUtils utils;
	private RESTUtilsUserTestHelper userTestHelper;
	
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;
		
		utils = new RESTUtils();
		userTestHelper = new RESTUtilsUserTestHelper(utils);
	}
	
	@Test
	public void testHappyPath()
	{
		userTestHelper.testUserRegistrationSucceeds();
		userTestHelper.testLoginSuccess();
	}
}

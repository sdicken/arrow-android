package com.arrowfoodcouriers.arrowfood.test.Interfaces;

import static org.junit.Assert.assertTrue;
import mocks.MockNavigationDrawerCallback;

import org.junit.Before;
import org.junit.Test;

public class NavigationDrawerCallbackTest 
{
	MockNavigationDrawerCallback _navDrawerCallback;
	
	@Before
	public void setUp()
	{
		_navDrawerCallback = new MockNavigationDrawerCallback();
	}
	
	@Test
	public void navigationDrawerValues_ChangeAfterLogin()
	{
		_navDrawerCallback.onNavigationDrawerUpdated();
		assertTrue(_navDrawerCallback.updateWasCalled);
	}
}

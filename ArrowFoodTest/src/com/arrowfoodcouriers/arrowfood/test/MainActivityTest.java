package com.arrowfoodcouriers.arrowfood.test;

import static org.junit.Assert.assertTrue;
import mocks.MockLoginCallback;
import mocks.MockNavigationDrawerCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest 
{
	MainActivity _activity;
	OpenCartSession _session;
	MockLoginCallback _loginCallback;
	MockNavigationDrawerCallback _navDrawerCallback;
	
	@Before
	public void setUp()
	{
		_activity = Robolectric.buildActivity(MainActivity.class).create().get();
		_session = _activity.getOpenCartSession();
		_loginCallback = new MockLoginCallback();
		_navDrawerCallback = new MockNavigationDrawerCallback();
		_session.AttachLoginDialogCallback(_loginCallback);
		_session.AttachNavigationDrawerCallback(_navDrawerCallback);
		_activity.setOpenCartSession(_session);
	}
	
	@Test
	public void navigationDrawerValues_ChangeAfterLogin()
	{
		_navDrawerCallback.onNavigationDrawerUpdated();
		assertTrue(_navDrawerCallback.updateWasCalled);
	}
}

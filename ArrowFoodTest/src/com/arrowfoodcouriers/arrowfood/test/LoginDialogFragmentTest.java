package com.arrowfoodcouriers.arrowfood.test;

import static org.junit.Assert.assertTrue;
import mocks.MockLoginCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;

@RunWith(RobolectricTestRunner.class)
public class LoginDialogFragmentTest 
{
	private MainActivity _activity;
	private ISession _session;
	private MockLoginCallback _loginCallback;
	
	@Before
	public void setUp()
	{
		_activity = Robolectric.buildActivity(MainActivity.class).create().get();
		_session = _activity.getSession();
		_loginCallback = new MockLoginCallback();
	}
	
	@Test
	public void submitLogin_authFailure()
	{
		_loginCallback.onFailure();
		assertTrue(_loginCallback.failureWasCalled);
	}
	
	@Test
	public void submitLogin_authSuccess()
	{
		_loginCallback.onSuccess();
		assertTrue(_loginCallback.successWasCalled);
	}
}

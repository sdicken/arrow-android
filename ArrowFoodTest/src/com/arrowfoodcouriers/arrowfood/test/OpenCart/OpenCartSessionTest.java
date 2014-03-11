package com.arrowfoodcouriers.arrowfood.test.OpenCart;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import mocks.MockLoginCallback;
import mocks.MockNavigationDrawerCallback;
import mocks.MockRESTCall;
import mocks.MockRESTCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartItem;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;
import com.jayway.awaitility.Awaitility;
@RunWith(RobolectricTestRunner.class)
public class OpenCartSessionTest 
{
	private OpenCartSession _session;
	private MockRESTCallback _restCallback;
	private IRESTCall _postCall;
	private IRESTCall _getCall;
	
	@Before
	public void setUp()
	{
		ShadowLog.stream = System.out;	// use Robolectric to redirect Android's Log output to console
		_session = new OpenCartSession();
		_restCallback = new MockRESTCallback();
		_postCall = new MockRESTCall();
		_getCall = new MockRESTCall();
		_session.AttachLoginDialogCallback(new MockLoginCallback());
		_session.AttachNavigationDrawerCallback(new MockNavigationDrawerCallback());
		_session.AttachRESTCallback(_restCallback);
		_session.AttachPOSTCall(_postCall);
		_session.AttachGETCall(_getCall);
	}
	
	@Test
	public void doPOSTShouldTriggerRESTCallback()
	{
		_session.Login("test@test.test", "test");
		Awaitility.await().until(taskIsCompleted());
		assertTrue(_restCallback.completeWasCalled);
	}
	
	@Test
	public void doPOSTWithURLEncodeShouldTriggerRESTCallback()
	{
		_session.AddToCart(new OpenCartItem());
		Awaitility.await().until(taskIsCompleted());
		assertTrue(_restCallback.completeWasCalled);
	}
	
	@Test
	public void doGETShouldTriggerRESTCallback()
	{
		_session = new OpenCartSession(_restCallback, _postCall, _getCall); // triggers a GET call (details in OpenCartSession)
		Awaitility.await().until(taskIsCompleted());
		assertTrue(_restCallback.completeWasCalled);
	}
	
	private Callable<Boolean> taskIsCompleted() 
	{
		return new Callable<Boolean>()
		{
			@Override
			public Boolean call() throws Exception 
			{
				return (_restCallback.completeWasCalled != null && _restCallback.completeWasCalled);
			}
	
		};
	}
}

package com.arrowfoodcouriers.arrowfood.OpenCart;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.Interfaces.SessionFactory;
import com.arrowfoodcouriers.arrowfood.RoboGuice.GETCall;
import com.arrowfoodcouriers.arrowfood.RoboGuice.POSTCall;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class RealSessionFactory implements SessionFactory 
{
	private final Provider<IRESTCall> _postCallProvider;	// injected by RoboGuice, see RoboGuice package for details
	private final Provider<IRESTCall> _getCallProvider;		// injected by RoboGuice, see RoboGuice package for details
	
	@Inject
	public RealSessionFactory(@POSTCall Provider<IRESTCall> postCallProvider,
			@GETCall Provider<IRESTCall> getCallProvider)
	{
		this._getCallProvider = getCallProvider;
		this._postCallProvider = postCallProvider;
	}
	
	public ISession create(IRESTCallback restCallback,
			INavigationDrawerCallback navigationDrawerCallback,
			ILoginDialogCallback loginDialogCallback,
			IRegistrationDialogCallback registrationDialogCallback) 
	{
		return new OpenCartSession(
				restCallback, 
				_postCallProvider.get(), 
				_getCallProvider.get(), 
				navigationDrawerCallback, 
				loginDialogCallback, 
				registrationDialogCallback);
	}
	
	public ISession create(ISession session,
			IRESTCallback restCallback,
			INavigationDrawerCallback navigationDrawerCallback,
			ILoginDialogCallback loginDialogCallback,
			IRegistrationDialogCallback registrationDialogCallback)
	{
		return new OpenCartSession(session,
				restCallback, 
				_postCallProvider.get(), 
				_getCallProvider.get(), 
				navigationDrawerCallback, 
				loginDialogCallback, 
				registrationDialogCallback);
	}

}

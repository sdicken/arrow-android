package com.arrowfoodcouriers.arrowfood.Interfaces;

import com.arrowfoodcouriers.arrowfood.Callbacks.RESTCallback;


public interface SessionFactory 
{
	ISession create(RESTCallback restCallback,
			INavigationDrawerCallback navigationDrawerCallback,
			ILoginDialogCallback loginDialogCallback, 
			IRegistrationDialogCallback registrationDialogCallback);
}

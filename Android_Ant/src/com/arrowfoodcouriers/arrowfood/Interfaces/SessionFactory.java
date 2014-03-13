package com.arrowfoodcouriers.arrowfood.Interfaces;



public interface SessionFactory 
{
	ISession create(IRESTCallback restCallback,
			INavigationDrawerCallback navigationDrawerCallback,
			ILoginDialogCallback loginDialogCallback, 
			IRegistrationDialogCallback registrationDialogCallback);
}

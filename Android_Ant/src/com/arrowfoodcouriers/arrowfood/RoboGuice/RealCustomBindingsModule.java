package com.arrowfoodcouriers.arrowfood.RoboGuice;

import com.arrowfoodcouriers.arrowfood.Callbacks.NavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Callbacks.RESTCallback;
import com.arrowfoodcouriers.arrowfood.Fragments.LoginDialogFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RegistrationDialogFragment;
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.Interfaces.SessionFactory;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealGETCall;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealPOSTCall;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealSessionFactory;
import com.google.inject.Binder;
import com.google.inject.Module;

public class RealCustomBindingsModule implements Module 
{

	public void configure(Binder binder) 
	{
		binder.bind(IRESTCallback.class).to(RESTCallback.class);
		binder.bind(ILoginDialogCallback.class).to(LoginDialogFragment.class);					// gets overridden by SessionFactory, but needed for injecting dummy code until real instance supplied by SessionFactory
		binder.bind(IRegistrationDialogCallback.class).to(RegistrationDialogFragment.class);	// gets overridden by SessionFactory, but needed for injecting dummy code until real instance supplied by SessionFactory
		binder.bind(INavigationDrawerCallback.class).to(NavigationDrawerCallback.class);		// gets overridden by SessionFactory, but needed for injecting dummy code until real instance supplied by SessionFactory
		binder.bind(IRESTCall.class).annotatedWith(POSTCall.class).to(RealPOSTCall.class);
		binder.bind(IRESTCall.class).annotatedWith(GETCall.class).to(RealGETCall.class);
		binder.bind(ISession.class).to(OpenCartSession.class);
		binder.bind(SessionFactory.class).to(RealSessionFactory.class);
	}
}

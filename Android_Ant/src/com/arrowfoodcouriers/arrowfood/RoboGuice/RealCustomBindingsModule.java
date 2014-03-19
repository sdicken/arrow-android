package com.arrowfoodcouriers.arrowfood.RoboGuice;

import com.arrowfoodcouriers.arrowfood.Interfaces.ICookieManager;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.SessionFactory;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealGETCall;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealPOSTCall;
import com.arrowfoodcouriers.arrowfood.OpenCart.RealSessionFactory;
import com.arrowfoodcouriers.arrowfood.OpenCart.ThisitaCookieManager;
import com.google.inject.Binder;
import com.google.inject.Module;

public class RealCustomBindingsModule implements Module 
{

	public void configure(Binder binder) 
	{
		binder.bind(IRESTCall.class).annotatedWith(POSTCall.class).to(RealPOSTCall.class);
		binder.bind(IRESTCall.class).annotatedWith(GETCall.class).to(RealGETCall.class);
		binder.bind(SessionFactory.class).to(RealSessionFactory.class);
		binder.bind(ICookieManager.class).to(ThisitaCookieManager.class);
	}
}

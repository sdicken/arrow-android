package com.arrowfoodcouriers.arrowfood;

import com.arrowfoodcouriers.arrowfood.RoboGuice.RealCustomBindingsModule;

import roboguice.RoboGuice;
import android.app.Application;

public class App extends Application 
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		RoboGuice.setBaseApplicationInjector(this, 
				RoboGuice.DEFAULT_STAGE, 
				RoboGuice.newDefaultRoboModule(this), 
				new RealCustomBindingsModule());
	}
}

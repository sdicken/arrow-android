package com.arrowfoodcouriers.arrowfood.Async;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;

public class RegistrationPOSTTask extends POSTTask 
{
	private IRegistrationDialogCallback _registrationDialogCallback;
	
	public RegistrationPOSTTask(IRESTCall postCall, IRegistrationDialogCallback registrationDialogCallback)
	{
		super(postCall);
		this._registrationDialogCallback = registrationDialogCallback;
	}
	
	@Override
	protected void onPreExecute() 
	{
		_registrationDialogCallback.onTaskStart();
	}
	
}

package com.arrowfoodcouriers.arrowfood.Async;

import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCall;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartTask;

public class RegistrationPOSTTask extends POSTTask 
{
	private IRegistrationDialogCallback _registrationDialogCallback;
	
	public RegistrationPOSTTask(IRESTCallback restCallback, IRESTCall postCall, IRegistrationDialogCallback registrationDialogCallback, ISession session)
	{
		super(OpenCartTask.REGISTER, restCallback, postCall, session);
		this._registrationDialogCallback = registrationDialogCallback;
	}
	
	@Override
	protected void onPreExecute() 
	{
		_registrationDialogCallback.onTaskStart();
	}
	
}

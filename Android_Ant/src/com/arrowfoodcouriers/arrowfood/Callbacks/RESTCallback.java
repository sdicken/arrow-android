package com.arrowfoodcouriers.arrowfood.Callbacks;

import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRESTCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartTask;
import com.google.inject.Inject;

public class RESTCallback implements IRESTCallback 
{
	private final INavigationDrawerCallback _navigationDrawerCallback;
	private final ILoginDialogCallback _loginDialogCallback;
	private final IRegistrationDialogCallback _registrationDialogCallback;
	
	@Inject
	public RESTCallback(INavigationDrawerCallback navigationDrawerCallback,
			ILoginDialogCallback loginDialogCallback,
			IRegistrationDialogCallback registrationDialogCallback)
	{
		_navigationDrawerCallback = navigationDrawerCallback;
		_loginDialogCallback = loginDialogCallback;
		_registrationDialogCallback = registrationDialogCallback;
	}

	public void onTaskCompleted(OpenCartTask task, ISession session, String response) {
        switch(task)
        {
            case ORDER:
            {
                // nothing to do
                break;
            }
            case LOGOUT:
            {
                session.deauthenticate();
                _navigationDrawerCallback.onNavigationDrawerUpdated();
                break;
            }
            case REGISTER:
            {
            	_registrationDialogCallback.onTaskCompleted();
            	if(session.IsAuthenticated())
            	{
            		_registrationDialogCallback.onSuccess();
            		_navigationDrawerCallback.onNavigationDrawerUpdated();
            	}
            	else { _registrationDialogCallback.onFailure(); }
                break;
            }
            case LOGIN:
            {
                session.authenticate();
                _loginDialogCallback.onTaskCompleted(); // TODO: actually determine if login succeeded/failed
                if(session.IsAuthenticated()) 
                { 
                	_loginDialogCallback.onSuccess(); 
                	_navigationDrawerCallback.onNavigationDrawerUpdated();
                }
                else { _loginDialogCallback.onFailure(); }
                break;
            }
            case USER_DATA_LOADED:
            {
                session.ParseEditHTML(response);
                break;
            }
            case CONSTRUCTOR:
            {
                // no action necessary
                break;
            }
            case ADD_TO_CART:
            {
                break;
            }
            case APPLY_VOUCHER:
            {
                break;
            }
            case APPLY_COUPON:
            {
                break;
            }
            case COUNTRY:
			{
				break;
			}
		default:
			break;
        }
    }
}

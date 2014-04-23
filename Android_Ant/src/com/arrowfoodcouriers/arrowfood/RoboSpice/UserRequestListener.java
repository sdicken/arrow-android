package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Fragments.CartFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.LoginDialogFragment;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class UserRequestListener implements RequestListener<User>
{
	private final Context context;
	
	public UserRequestListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(User user) 
	{
		if(user == null)
		{
			DialogFragment fragment = new LoginDialogFragment();
			fragment.show(((Activity) context).getFragmentManager(), MainActivity.FRAGMENT_TAG_LOGIN);
		}
		else
		{
			CartRequest request = new CartRequest();
        	MainActivity.spiceManager.execute(request, new CartRequestListener(context));
        	Utils.loadFragment(((Activity) context).getFragmentManager(), new CartFragment());
		}
	}

}

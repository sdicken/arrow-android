package com.arrowfoodcouriers.arrowfood.RoboSpice;

import android.content.Context;
import android.widget.Toast;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartAddRequestListener implements RequestListener<Response>
{
	private Context context;
	
	public CartAddRequestListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(Response response) 
	{
		Toast.makeText(context, context.getResources().getString(R.string.toast_menu_to_cart), Toast.LENGTH_SHORT).show();			
	}
}
package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartAddRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartAddRequestListener;

public class MenuItemOptionsDialog extends DialogFragment 
{
	private Dialog alertDialog = null;
	private String restaurantName;
	private String menuName;
	private String itemName;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		Bundle args = getArguments();
		restaurantName = args.getString(MenuFragment.BUNDLE_TAG_RESTAURANT_NAME);
		menuName = args.getString(MenuFragment.BUNDLE_TAG_MENU_NAME);
		itemName = args.getString(MenuFragment.BUNDLE_TAG_ITEM_NAME);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_item_options, null);
		
		alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_item_options_title)
                .setPositiveButton(R.string.dialog_continue, new PositiveClickListener())
                .setNegativeButton(R.string.dialog_cancel, new NegativeClickListener())
                .create();
		
		return alertDialog;
	}
	
	private class PositiveClickListener implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) 
		{
			// TODO Auto-generated method stub
			CartAddRequest request = new CartAddRequest(restaurantName, menuName, itemName, Integer.valueOf(1), new CartItemOption[1]); // TODO: add item options
			MainActivity.spiceManager.execute(request, new CartAddRequestListener(getActivity()));
		}
	}
	
	private class NegativeClickListener implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) 
		{
			// TODO Auto-generated method stub
		}
	}
}

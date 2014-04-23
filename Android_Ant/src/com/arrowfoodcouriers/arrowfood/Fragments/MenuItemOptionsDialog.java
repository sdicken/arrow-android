package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.arrowfoodcouriers.arrowfood.FilterUtils;
import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.MenuItemOption;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartAddRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartAddRequestListener;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenusRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenuItemOptionsDialog extends DialogFragment 
{
	private Dialog alertDialog = null;
	private String restaurantName;
	private String menuName;
	private String itemName;
	private LinearLayout linearLayout;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		Bundle args = getArguments();
		restaurantName = args.getString(MenuFragment.BUNDLE_TAG_RESTAURANT_NAME);
		menuName = args.getString(MenuFragment.BUNDLE_TAG_MENU_NAME);
		itemName = args.getString(MenuFragment.BUNDLE_TAG_ITEM_NAME);
		
		MenusRequest request = new MenusRequest();
		MainActivity.spiceManager.getFromCache(Menu[].class, request.createCacheKey(), DurationInMillis.ALWAYS_RETURNED, new MenuItemRequestListener(getActivity()));
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_item_options, null);
		
		linearLayout = (LinearLayout) dialogView.findViewById(R.id.dialog_item_options_layout);
		
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
	
	private class MenuItemRequestListener implements RequestListener<Menu[]>
	{
		private final Context context;
		
		public MenuItemRequestListener(Context context)
		{
			this.context = context;
		}
		
		@Override
		public void onRequestFailure(SpiceException e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRequestSuccess(Menu[] menus) 
		{
			MenuItem menuItem = FilterUtils.getMenuItem(menus, restaurantName, menuName, itemName);
			MenuItemOption[] menuItemOptions = menuItem.getItemOptions();
			CheckBox checkBox = new CheckBox(context);
			linearLayout.addView(checkBox);
		}
	}
}

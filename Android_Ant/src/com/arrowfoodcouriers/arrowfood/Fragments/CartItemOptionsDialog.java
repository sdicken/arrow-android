package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartDeleteItemQuantityRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartDeleteItemQuantityRequestListener;

public class CartItemOptionsDialog extends DialogFragment
{
	private Dialog alertDialog = null;
	private String restaurantName;
	private String menuName;
	private String itemName;
	private EditText quantityEditText;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		Bundle args = getArguments();
		restaurantName = args.getString(CartFragment.BUNDLE_RESTAURANT_NAME);
		menuName = args.getString(CartFragment.BUNDLE_MENU_NAME);
		itemName = args.getString(CartFragment.BUNDLE_ITEM_NAME);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_cart_options, null);
		
		quantityEditText = (EditText) dialogView.findViewById(R.id.cart_options_quantity_value);
		
		alertDialog = builder.setView(dialogView)
				.setTitle(R.string.dialog_cart_options_title)
				.setPositiveButton(R.string.dialog_continue, new PositiveClickListener())
				.create();
		
		return alertDialog;
	}
	
	private class PositiveClickListener implements DialogInterface.OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			String quantityAsString = quantityEditText.getText().toString();
			try
			{
				Integer quantity = Integer.valueOf(quantityAsString);
				CartDeleteItemQuantityRequest request = new CartDeleteItemQuantityRequest(restaurantName, menuName, itemName, quantity);
				MainActivity.spiceManager.execute(request, new CartDeleteItemQuantityRequestListener(getActivity()));
			}
			catch(NumberFormatException e)
			{
				return;
			}
		}
		
	}
}

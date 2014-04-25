package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.arrowfoodcouriers.arrowfood.R;

public class CartItemOptionsDialog extends DialogFragment 
{
	public static final String TAG = "cartItemOptionsDialog";
	private AlertDialog alertDialog = null;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_item_options, null);
		
		alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_item_options_title)
                .setPositiveButton(android.R.string.ok, new PositiveClickListener())
                .setNegativeButton(R.string.dialog_cancel, new NegativeClickListener())
                .create();
		
		return alertDialog;
	}
	
	private class PositiveClickListener implements DialogInterface.OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class NegativeClickListener implements DialogInterface.OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

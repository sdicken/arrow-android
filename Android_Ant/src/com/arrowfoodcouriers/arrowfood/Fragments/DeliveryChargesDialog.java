package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.arrowfoodcouriers.arrowfood.R;

public class DeliveryChargesDialog extends DialogFragment 
{
	private Dialog alertDialog = null;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_delivery_charges, null);
		
		alertDialog = builder.setView(dialogView)
                .setTitle(R.string.dialog_delivery_charges_title)
                .create();
		
		return alertDialog;
	}
}

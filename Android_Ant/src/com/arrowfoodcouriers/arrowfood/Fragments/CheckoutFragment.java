package com.arrowfoodcouriers.arrowfood.Fragments;

import java.math.BigDecimal;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.arrowfoodcouriers.arrowfood.R;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PaymentActivity;

public class CheckoutFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_checkout, container, false);
		
		Button checkoutButton = (Button) view.findViewById(R.id.checkout_button);
		checkoutButton.setOnClickListener(new CheckoutButtonListener());
		
		return view;
	}
	
	private class CheckoutButtonListener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			Double price = Double.valueOf(7.35);
			PayPalPayment payment = new PayPalPayment(new BigDecimal(price), "US", "Arrow Food Couriers", PayPalPayment.PAYMENT_INTENT_SALE);
			Intent intent = new Intent(getActivity(), PaymentActivity.class);
			intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
			startActivityForResult(intent, 0);
		}
	}
}

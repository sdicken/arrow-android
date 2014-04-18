package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;

public class CartFragment extends ListFragment 
{
	BaseAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		
		TextView subtotalText = (TextView) view.findViewById(R.id.cart_subtotal_value);
//		subtotalText.setText("$" + String.valueOf(Utils.getCart().getTotal()));
		
		Button checkoutButton = (Button) view.findViewById(R.id.cart_checkout_button);
		checkoutButton.setOnClickListener(new CartCheckoutButtonListener());
		
		mAdapter = new CartAdapter();
		setListAdapter(mAdapter);
		
		return view;
	}
	
	private class CartCheckoutButtonListener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			Utils.loadFragment(getFragmentManager(), new CheckoutFragment());			
		}
	}
}

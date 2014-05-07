package com.arrowfoodcouriers.arrowfood.RoboSpice;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartPriceRequestListener implements RequestListener<Cart>
{
	private final Context context;
	private TextView subtotalTextView;
	private Button checkoutButton;
	private Double subtotal;
	
	public CartPriceRequestListener(Context context)
	{
		this.context = context;
	}
	
	@Override
	public void onRequestFailure(SpiceException e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRequestSuccess(Cart cart) 
	{
		subtotalTextView = (TextView) ((Activity) context).findViewById(R.id.cart_subtotal_label);
		checkoutButton = (Button) ((Activity) context).findViewById(R.id.cart_checkout_button);
		
		subtotal = cart.getTotal();
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		String subtotalTextWithoutPrice = context.getResources().getString(R.string.cart_subtotal_label);
		StringBuilder sb = new StringBuilder();
		sb.append(subtotalTextWithoutPrice);
		sb.append(": ");
		sb.append(format.format(subtotal));
		subtotalTextView.setText(sb.toString());	// add price amount after label string
		
		checkoutButton.setEnabled(true);	// allow checkout button to be pressed after price set
		
//		ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
//		CartAdapter adapter = (CartAdapter) listView.getAdapter();
//		adapter.clear();
//		adapter.addAll(cart.getItems());	// populate ListView with all items in cart
	}
}

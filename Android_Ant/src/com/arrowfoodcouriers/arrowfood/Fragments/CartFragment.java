package com.arrowfoodcouriers.arrowfood.Fragments;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartPriceRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartFragment extends ListFragment 
{
	CartAdapter mAdapter;
	private TextView subtotalText;
	private Button checkoutButton;
	private Double subtotal;
	public static final String SUBTOTAL = "subtotal";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		CartPriceRequest request = new CartPriceRequest();
		MainActivity.spiceManager.execute(request, new CartPriceListener(getActivity()));
		
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		
		subtotalText = (TextView) view.findViewById(R.id.cart_subtotal_value);
		
		checkoutButton = (Button) view.findViewById(R.id.cart_checkout_button);
		checkoutButton.setOnClickListener(new CartCheckoutButtonListener());
		
		List<CartItem> cartItems = new ArrayList<CartItem>();
		mAdapter = new CartAdapter(getActivity(), cartItems);
		setListAdapter(mAdapter);
		
		return view;
	}
	
	private class CartCheckoutButtonListener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			Fragment fragment = new CheckoutFragment();
			Bundle args = new Bundle();
			args.putDouble(SUBTOTAL, subtotal);
			fragment.setArguments(args);
			Utils.loadFragment(getFragmentManager(), fragment);			
		}
	}
	
	private class CartPriceListener implements RequestListener<Cart>
	{
		private final Context context;
		
		public CartPriceListener(Context context)
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
			subtotal = cart.getTotal();
			
			NumberFormat format = NumberFormat.getCurrencyInstance();
			subtotalText.setText(format.format(subtotal));
			checkoutButton.setEnabled(true);
			
			ListView listView = (ListView) ((Activity) context).findViewById(android.R.id.list);
			CartAdapter adapter = (CartAdapter) listView.getAdapter();
			adapter.clear();
			adapter.addAll(cart.getItems());
		}
		
	}
}

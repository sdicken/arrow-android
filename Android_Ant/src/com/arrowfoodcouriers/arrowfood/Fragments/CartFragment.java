package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.CartAdapter;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.Response;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartDeleteItemQuantityRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartDeleteItemRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartRequestListener;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class CartFragment extends ListFragment 
{
	CartAdapter mAdapter;
	private TextView subtotalTextView;
	private Button deliveryChargesLinkButton;
	private Button checkoutButton;
	private Double subtotal;
	private static final String TAG_DELIVERY_CHARGE_DIALOG = "deliveryCharges";
	private static final String TAG_CART_OPTIONS_DIALOG = "cartOptions";
	public static final String SUBTOTAL = "subtotal";
	public static final String BUNDLE_RESTAURANT_NAME = "restaurantName";
	public static final String BUNDLE_MENU_NAME = "menuName";
	public static final String BUNDLE_ITEM_NAME = "itemName";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
//		CartPriceRequest request = new CartPriceRequest();
//		MainActivity.spiceManager.execute(request, new CartPriceListener(getActivity()));
		
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		
//		subtotalTextView = (TextView) view.findViewById(R.id.cart_subtotal_label);
		
		deliveryChargesLinkButton = (Button) view.findViewById(R.id.cart_delivery_charge_link);
		deliveryChargesLinkButton.setOnClickListener(new DeliveryChargesLinkButtonListener());
		
		checkoutButton = (Button) view.findViewById(R.id.cart_checkout_button);
		checkoutButton.setOnClickListener(new CartCheckoutButtonListener());
		
		List<CartItem> cartItems = new ArrayList<CartItem>();
		mAdapter = new CartAdapter(getActivity(), cartItems);
		setListAdapter(mAdapter);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{	
		super.onActivityCreated(savedInstanceState);
		registerForContextMenu(this.getListView());
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) 
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuInflater inflater = this.getActivity().getMenuInflater();
		inflater.inflate(R.menu.cart_item, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId())
		{
			case R.id.menu_cart_single_item_delete:
			{
				CartItem cartItem = mAdapter.getItem(info.position);
				CartDeleteItemRequest request = new CartDeleteItemRequest(cartItem.getRestaurant(), cartItem.getMenu(), cartItem.getItem());
				MainActivity.spiceManager.execute(request, new CartDeleteItemRequestListener());
				return true;
			}
			case R.id.menu_cart_multiple_item_delete:
			{
				CartItem cartItem = mAdapter.getItem(info.position);
				CartItemOptionsDialog dialog = new CartItemOptionsDialog();
				Bundle args = new Bundle();
				args.putString(BUNDLE_RESTAURANT_NAME, cartItem.getRestaurant());
				args.putString(BUNDLE_MENU_NAME, cartItem.getMenu());
				args.putString(BUNDLE_ITEM_NAME, cartItem.getItem());
				dialog.setArguments(args);
				dialog.show(getFragmentManager(), TAG_CART_OPTIONS_DIALOG);
				
				return true;
			}
			default:
			{
				return super.onContextItemSelected(item);
			}
		}
	}
	
	private class DeliveryChargesLinkButtonListener implements OnClickListener
	{

		@Override
		public void onClick(View v) 
		{
			new DeliveryChargesDialog().show(getFragmentManager(), TAG_DELIVERY_CHARGE_DIALOG);
		}
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
	
	private class CartDeleteItemRequestListener implements RequestListener<Response>
	{

		@Override
		public void onRequestFailure(SpiceException e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRequestSuccess(Response response)
		{
			CartRequest request = new CartRequest();
			MainActivity.spiceManager.execute(request, new CartRequestListener(getActivity()));
		}
		
	}
}

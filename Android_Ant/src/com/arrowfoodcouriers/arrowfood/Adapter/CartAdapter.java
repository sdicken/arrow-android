package com.arrowfoodcouriers.arrowfood.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.RESTUtils;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;

public class CartAdapter extends BaseAdapter 
{
	private static final CartItem[] cart = new RESTUtils().getCartItems();

	@Override
	public int getCount() 
	{
		return cart.length;
	}

	@Override
	public Object getItem(int position) 
	{
		return cart[position];
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
		if (view == null) 
        {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.cart_list_item,
                    parent, false);
        }
		Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");
		
		// set up views
		TextView titleView = (TextView) view.findViewById(R.id.cart_list_title);
        titleView.setTypeface(rokkitt);
        TextView subtitleView = (TextView) view.findViewById(R.id.cart_list_subtitle);
        TextView priceView = (TextView) view.findViewById(R.id.cart_list_price);
        TextView quantityView = (TextView) view.findViewById(R.id.cart_list_quantity);
        ImageView quantityUpArrow = (ImageView) view.findViewById(R.id.cart_quantity_up_arrow);
        ImageView quantityDownArrow = (ImageView) view.findViewById(R.id.cart_quantity_down_arrow);
        
        // retrieve model
        CartItem cartItem = (CartItem)getItem(position);
        
        // fill out views with model's info
        titleView.setText(cartItem.getItemId());
        subtitleView.setText(cartItem.getMenuId());
        priceView.setText("$" + String.valueOf(cartItem.getTotal()));
		quantityView.setText(String.valueOf(cartItem.getQuantity()));
		
		// set click listeners for increasing and decreasing quantity of items in cart
		quantityUpArrow.setOnClickListener(new IncrementQuantityListener(cartItem));
		quantityDownArrow.setOnClickListener(new DecrementQuantityListener(cartItem));
		
		return view;
	}
	
	private class IncrementQuantityListener implements View.OnClickListener
	{
		private CartItem cartItem;
		
		public IncrementQuantityListener(CartItem cartItem)
		{
			this.cartItem = cartItem;
		}

		@Override
		public void onClick(View v) 
		{
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			notifyDataSetChanged();
		}
		
	}
	
	private class DecrementQuantityListener implements View.OnClickListener
	{
		private CartItem cartItem;
		
		public DecrementQuantityListener(CartItem cartItem)
		{
			this.cartItem = cartItem;
		}

		@Override
		public void onClick(View v) 
		{
			if(cartItem.getQuantity() > 0)
			{
				cartItem.setQuantity(cartItem.getQuantity() - 1);
				notifyDataSetChanged();
			}
		}
		
	}

}

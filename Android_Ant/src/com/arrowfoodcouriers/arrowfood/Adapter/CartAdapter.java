package com.arrowfoodcouriers.arrowfood.Adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;

/**
 * 
 * @author Sam
 * Ideas borrowed by Sam from http://www.christopherbiscardi.com/2014/01/28/android-listfragment-populated-by-robospice/
 */
public class CartAdapter extends ArrayAdapter<CartItem> 
{
	public CartAdapter(Context context, List<CartItem> items)
	{
		super(context, R.layout.cart_list_item, items);
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
		CartItem cartItem = (CartItem) getItem(position);
		
		ViewHolder viewHolder;
		Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");
		if (view == null) 
        {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(R.layout.cart_list_item, null);
			viewHolder.titleView = (TextView) view.findViewById(R.id.cart_list_title);
			viewHolder.titleView.setTypeface(rokkitt);
			viewHolder.subtitleView = (TextView) view.findViewById(R.id.cart_list_subtitle);
			viewHolder.priceView = (TextView) view.findViewById(R.id.cart_list_price);
			viewHolder.quantityView = (TextView) view.findViewById(R.id.cart_list_quantity);
			viewHolder.quantityUpArrow = (ImageView) view.findViewById(R.id.cart_quantity_up_arrow);
			viewHolder.quantityDownArrow = (ImageView) view.findViewById(R.id.cart_quantity_down_arrow);
			view.setTag(viewHolder);
        }
		else
		{
			viewHolder = (ViewHolder) view.getTag();
		}
        
        // fill out views with model's info
        viewHolder.titleView.setText(cartItem.getItem());
        viewHolder.subtitleView.setText(cartItem.getMenu());
        viewHolder.priceView.setText("$" + String.valueOf(cartItem.getTotal()));
        viewHolder.quantityView.setText(String.valueOf(cartItem.getQuantity()));
		
		// set click listeners for increasing and decreasing quantity of items in cart
        viewHolder.quantityUpArrow.setOnClickListener(new IncrementQuantityListener(cartItem));
        viewHolder.quantityDownArrow.setOnClickListener(new DecrementQuantityListener(cartItem));
		
		return view;
	}
	
	private static class ViewHolder
	{
		TextView titleView;
		TextView subtitleView;
		TextView priceView;
		TextView quantityView;
		ImageView quantityUpArrow;
		ImageView quantityDownArrow;
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

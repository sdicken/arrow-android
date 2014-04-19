package com.arrowfoodcouriers.arrowfood.Adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;

/**
 * 
 * @author Sam
 * Ideas borrowed by Sam from http://www.christopherbiscardi.com/2014/01/28/android-listfragment-populated-by-robospice/
 */
public class MenuAdapter extends ArrayAdapter<MenuItem>
{	
	public MenuAdapter(Context context, List<MenuItem> menuItems)
	{
		super(context, R.layout.menu_list_item, menuItems);
	}

	public View getView(int position, View view, ViewGroup parent) 
	{
		MenuItem menuItem = (MenuItem) getItem(position);
		
		ViewHolder viewHolder;
		Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");
		if (view == null) 
        {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
        	view = inflater.inflate(R.layout.menu_list_item, null);
        	viewHolder.titleView = (TextView) view.findViewById(R.id.menu_list_title);
        	viewHolder.titleView.setTypeface(rokkitt);
        	viewHolder.subtitleView = (TextView) view.findViewById(R.id.menu_list_subtitle);
        	viewHolder.priceView = (TextView) view.findViewById(R.id.menu_list_price);
        }
		else
		{
			viewHolder = (ViewHolder) view.getTag();
		}

        viewHolder.titleView.setText(menuItem.getName());
        viewHolder.subtitleView.setText(menuItem.getDescription());
        viewHolder.priceView.setText("$" + String.valueOf(menuItem.getPrice()));

        return view;
	}
	
	private static class ViewHolder
    {
    	TextView titleView;
    	TextView subtitleView;
    	TextView priceView;
    }

}

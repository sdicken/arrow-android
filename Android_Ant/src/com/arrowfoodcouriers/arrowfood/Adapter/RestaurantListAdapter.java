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
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;

/**
 * Created by Ryan on 2/18/14.
 * Ideas borrowed by Sam from http://www.christopherbiscardi.com/2014/01/28/android-listfragment-populated-by-robospice/
 */
public class RestaurantListAdapter extends ArrayAdapter<Restaurant> 
{
	public RestaurantListAdapter(Context context, List<Restaurant> data)
	{
		super(context, R.layout.restaurant_list_item, data);
	}

    public View getView(int position, View view, ViewGroup parent) 
    {
    	Restaurant restaurant = (Restaurant) getItem(position);
    	
    	ViewHolder viewHolder;
    	Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");
        if (view == null) 
        {
        	viewHolder = new ViewHolder();
        	LayoutInflater inflater = LayoutInflater.from(getContext());
        	view = inflater.inflate(R.layout.restaurant_list_item, null);
        	viewHolder.titleView = (TextView) view.findViewById(R.id.restaurant_list_title);
        	viewHolder.titleView.setTypeface(rokkitt);
        	viewHolder.subtitleView = (TextView) view.findViewById(R.id.restaurant_list_subtitle);
        	viewHolder.detailsView = (TextView) view.findViewById(R.id.restaurant_list_description);
        	viewHolder.imageView = (ImageView) view.findViewById(R.id.restaurant_list_logo);
        	view.setTag(viewHolder);
        }
        else
        {
        	viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.titleView.setText(restaurant.getName());
        viewHolder.subtitleView.setText(restaurant.getDescription());
//        viewHolder.detailsView.setText(restaurant.getPhones()[0].getNumber());
        viewHolder.imageView.setImageResource(R.drawable.ic_launcher);

        return view;
    }
    
    private static class ViewHolder
    {
    	TextView titleView;
    	TextView subtitleView;
    	TextView detailsView;
    	ImageView imageView;
    }
}

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
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;

/**
 * Created by Ryan on 2/18/14.
 */
public class RestaurantListAdapter extends BaseAdapter 
{

    private static final Restaurant[] restaurants = new RESTUtils().getRestaurants();

    public Object getItem(int position) 
    {
        return restaurants[position];
    }

    public long getItemId(int position) 
    {
        return position;
    }

    public int getCount() 
    {
        return restaurants.length;
    }

    public View getView(int position, View view, ViewGroup parent) 
    {

        if (view == null) 
        {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.restaurant_list_item,
                    parent, false);
        }
        
        Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");

        TextView titleView = (TextView) view.findViewById(R.id.restaurant_list_title);
        titleView.setTypeface(rokkitt);
        TextView subtitleView = (TextView) view.findViewById(R.id.restaurant_list_subtitle);
        TextView detailsView = (TextView) view.findViewById(R.id.restaurant_list_description);
        ImageView imageView = (ImageView) view.findViewById(R.id.restaurant_list_logo);
        Restaurant restListObject = (Restaurant) getItem(position);
        titleView.setText(restListObject.getName());
        subtitleView.setText(restListObject.getDescription());
        detailsView.setText(restListObject.getPhones()[0].getNumber());
        imageView.setImageResource(R.drawable.ic_launcher);

        return view;
    }
}

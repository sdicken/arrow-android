package com.arrowfoodcouriers.arrowfood.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.RestaurantListObject;
import com.arrowfoodcouriers.arrowfood.R;

/**
 * Created by Ryan on 2/18/14.
 */
public class RestaurantListAdapter extends BaseAdapter 
{

    private static final Object[] restaurants = 
    	{
            new RestaurantListObject("QDOBA", "Mexican Grill", "111 22nd Street | phone: (111) 123 2234", R.drawable.ic_launcher),
            new RestaurantListObject("QUILLS COFFEE", "Coffee Shop and Cafe", "", R.drawable.ic_launcher),
            new RestaurantListObject("NORTHEND CAFE", "Breakfast, Lunch, and Dinner", "", R.drawable.ic_launcher),
            new RestaurantListObject("Restaurant 4", "Yummy", "", R.drawable.ic_launcher),
            new RestaurantListObject("Restaurant 5", "Good Stuff", "", R.drawable.ic_launcher),
            new RestaurantListObject("Restaurant 6", "Order From Here", "", R.drawable.ic_launcher),
            new RestaurantListObject("Restaurant 7", "You Won't Regret It", "", R.drawable.ic_launcher)
    };

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

        TextView titleView = (TextView)view.findViewById(R.id.restaurant_list_title);
        titleView.setTypeface(rokkitt);
        TextView subtitleView = (TextView)view.findViewById(R.id.restaurant_list_subtitle);
        TextView detailsView = (TextView) view.findViewById(R.id.restaurant_list_description);
        ImageView imageView = (ImageView)view.findViewById(R.id.restaurant_list_logo);
        RestaurantListObject restListObject = (RestaurantListObject)getItem(position);
        titleView.setText(restListObject.getName());
        subtitleView.setText(restListObject.getSubtitle());
        detailsView.setText(restListObject.getDetails());
        imageView.setImageResource(restListObject.getImage());

        return view;
    }
}

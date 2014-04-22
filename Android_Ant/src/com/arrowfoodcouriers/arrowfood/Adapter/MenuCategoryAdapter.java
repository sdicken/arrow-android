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

public class MenuCategoryAdapter extends ArrayAdapter<String>
{
	public MenuCategoryAdapter(Context context, List<String> categories)
	{
		super(context, R.layout.restaurant_list_item, categories);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		String categoryName = (String) getItem(position);
    	
    	ViewHolder viewHolder;
    	Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");
        if (convertView == null) 
        {
        	viewHolder = new ViewHolder();
        	LayoutInflater inflater = LayoutInflater.from(getContext());
        	convertView = inflater.inflate(R.layout.restaurant_list_item, null);
        	viewHolder.titleView = (TextView) convertView.findViewById(R.id.restaurant_list_title);
        	viewHolder.titleView.setTypeface(rokkitt);
//        	viewHolder.subtitleView = (TextView) convertView.findViewById(R.id.restaurant_list_subtitle);
//        	viewHolder.detailsView = (TextView) convertView.findViewById(R.id.restaurant_list_description);
//        	viewHolder.imageView = (ImageView) convertView.findViewById(R.id.restaurant_list_logo);
        	convertView.setTag(viewHolder);
        }
        else
        {
        	viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleView.setText(categoryName);
//        viewHolder.subtitleView.setText(categoryName.getDescription());
//        viewHolder.detailsView.setText(restaurant.getPhones()[0].getNumber());
//        viewHolder.imageView.setImageResource(R.drawable.ic_launcher);

		return convertView;
	}
	
	private static class ViewHolder
    {
    	TextView titleView;
//    	TextView subtitleView;
//    	TextView detailsView;
//    	ImageView imageView;
    }
}

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
		super(context, R.layout.menu_category_list_item, categories);
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
        	convertView = inflater.inflate(R.layout.menu_category_list_item, null);
        	viewHolder.nameView = (TextView) convertView.findViewById(R.id.menu_category_name);
        	viewHolder.nameView.setTypeface(rokkitt);
        	convertView.setTag(viewHolder);
        }
        else
        {
        	viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameView.setText(categoryName);

		return convertView;
	}
	
	private static class ViewHolder
    {
    	TextView nameView;
    }
}

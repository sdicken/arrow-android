package com.arrowfoodcouriers.arrowfood.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MenuListObject;
import com.arrowfoodcouriers.arrowfood.R;

public class MenuAdapter extends BaseAdapter 
{
	private static final Object[] menus = 
    	{
            new MenuListObject("Burrito", "House special", 0.99)
    };
	
	public int getCount() 
	{
		return menus.length;
	}

	public Object getItem(int position) 
	{
		return menus[position];
	}

	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) 
	{
		if (view == null) 
        {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.menu_list_item,
                    parent, false);
        }
		
		Typeface rokkitt = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/Rokkitt-Regular.ttf");

        TextView titleView = (TextView)view.findViewById(R.id.menu_list_title);
        titleView.setTypeface(rokkitt);
        TextView subtitleView = (TextView)view.findViewById(R.id.menu_list_subtitle);
        TextView priceView = (TextView) view.findViewById(R.id.menu_list_price);
        MenuListObject menuListObject = (MenuListObject)getItem(position);
        titleView.setText(menuListObject.getName());
        subtitleView.setText(menuListObject.getDescription());
        priceView.setText("$" + String.valueOf(menuListObject.getPrice()));

        return view;
	}

}

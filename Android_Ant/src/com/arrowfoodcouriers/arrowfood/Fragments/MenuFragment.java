package com.arrowfoodcouriers.arrowfood.Fragments;

import android.app.ListFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuAdapter;

public class MenuFragment extends ListFragment
{
	BaseAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	View view = inflater.inflate(R.layout.fragment_menu, container, false);
    	
    	ImageView menuHeaderImage = (ImageView) view.findViewById(R.id.menu_header_image);
    	menuHeaderImage.setImageResource(R.drawable.qdoba_storefront);
    	
    	Typeface rokkitt = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rokkitt-Regular.ttf");
    	
    	TextView menuRestaurantName = (TextView) view.findViewById(R.id.menu_restaurant_name);
    	menuRestaurantName.setText("Qdoba Mexican Grill");
    	menuRestaurantName.setTypeface(rokkitt);
    	
        mAdapter = new MenuAdapter();
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}

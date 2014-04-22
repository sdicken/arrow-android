package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Utils;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuCategoryAdapter;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuCategoryListener;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuCategoryRequest;

public class RestaurantMenuCategoryFragment extends ListFragment
{
	MenuCategoryAdapter mAdapter;
	private String restaurantName;
	public static final String RESTAURANT_NAME = "restaurantName";
	public static final String MENU_NAME = "menuName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	Bundle args = getArguments();
    	this.restaurantName = args.getString(RestaurantFragment.RESTAURANT_NAME);
    	
    	MenuCategoryRequest request = new MenuCategoryRequest(restaurantName);
    	MainActivity.spiceManager.execute(request, new MenuCategoryListener(getActivity()));
    		
    	ArrayList<String> categories = new ArrayList<String>();
        mAdapter = new MenuCategoryAdapter(inflater.getContext(), categories);
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	TextView categoryNameView = (TextView) v.findViewById(R.id.restaurant_list_title);
    	String menuName = categoryNameView.getText().toString();
    	Fragment fragment = new MenuFragment();
    	Bundle args = new Bundle();
    	args.putString(RESTAURANT_NAME, restaurantName);
    	args.putString(MENU_NAME, menuName);
    	fragment.setArguments(args);
		Utils.loadFragment(getFragmentManager(), fragment);
    	super.onListItemClick(l, v, position, id);
    }
}

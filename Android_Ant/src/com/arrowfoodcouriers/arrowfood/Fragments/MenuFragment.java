package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuAdapter;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuItemRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MenuFragment extends ListFragment
{
	private final String restaurantName;
	private final String menuName;
	MenuAdapter mAdapter;

    public MenuFragment(String restaurantName, String menuName) 
    {
		this.restaurantName = restaurantName;
		this.menuName = menuName;
		MenuItemRequest request = new MenuItemRequest(restaurantName, menuName);
		MainActivity.spiceManager.execute(request, new MenuRequestListener());
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	View view = inflater.inflate(R.layout.fragment_menu, container, false);
    	
    	ImageView menuHeaderImage = (ImageView) view.findViewById(R.id.menu_header_image);
    	menuHeaderImage.setImageResource(R.drawable.qdoba_storefront);
    	
    	Typeface rokkitt = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rokkitt-Regular.ttf");
    	
    	TextView menuRestaurantName = (TextView) view.findViewById(R.id.menu_restaurant_name);
    	menuRestaurantName.setText(this.restaurantName);
    	menuRestaurantName.setTypeface(rokkitt);
    	
    	List<MenuItem> menuItems = new ArrayList<MenuItem>();
        mAdapter = new MenuAdapter(inflater.getContext(), menuItems);
        setListAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	CharSequence itemName = ((TextView) v.findViewById(R.id.menu_list_title)).getText();
    	Toast.makeText(getActivity(), itemName + " " + getResources().getString(R.string.toast_menu_to_cart), Toast.LENGTH_SHORT).show();;
    	super.onListItemClick(l, v, position, id);
    }
    
    private class MenuRequestListener implements RequestListener<List<MenuItem>>
    {

		@Override
		public void onRequestFailure(SpiceException e) 
		{
			Log.d("menu", "failed");
		}

		@Override
		public void onRequestSuccess(List<MenuItem> menuItems) 
		{
			ListView listView = (ListView) getActivity().findViewById(android.R.id.list);
			MenuAdapter adapter = (MenuAdapter) listView.getAdapter();
			adapter.clear();
			adapter.addAll(menuItems);
		}
    	
    }
}

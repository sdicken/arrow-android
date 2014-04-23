package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.MainActivity;
import com.arrowfoodcouriers.arrowfood.R;
import com.arrowfoodcouriers.arrowfood.Adapter.MenuAdapter;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuItemsRequestListener;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenusUpdatedRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenusUpdatedRequestListener;

public class MenuFragment extends ListFragment
{
	private String restaurantName;
	private String menuName;
	private static final String FRAGMENT_TAG_ITEM_OPTIONS = "itemOptions";
	public static final String BUNDLE_TAG_RESTAURANT_NAME = "restaurantName";
	public static final String BUNDLE_TAG_MENU_NAME = "menuName";
	public static final String BUNDLE_TAG_ITEM_NAME = "itemName";
	MenuAdapter mAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) 
    {
    	View view = inflater.inflate(R.layout.fragment_menu, container, false);
    	
    	Bundle bundle = getArguments();
    	restaurantName = bundle.getString(MenuCategoryFragment.RESTAURANT_NAME);
    	menuName = bundle.getString(MenuCategoryFragment.MENU_NAME);
    	
    	MenusUpdatedRequest request = new MenusUpdatedRequest();
    	MainActivity.spiceManager.execute(request, new MenusUpdatedRequestListener<>(new MenuItemsRequestListener(getActivity(), restaurantName, menuName)));
    	
    	ImageView menuHeaderImage = (ImageView) view.findViewById(R.id.menu_header_image);
    	menuHeaderImage.setImageResource(R.drawable.qdoba_storefront);
    	
    	Typeface rokkitt = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rokkitt-Regular.ttf");
    	
    	TextView menuRestaurantName = (TextView) view.findViewById(R.id.menu_restaurant_name);
    	menuRestaurantName.setText(restaurantName);
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
    	TextView itemNameView = (TextView) v.findViewById(R.id.menu_list_title);
    	String itemName = itemNameView.getText().toString();
    	
    	DialogFragment menuItemOptionsDialog = new MenuItemOptionsDialog();
    	Bundle args = new Bundle();
    	args.putString(BUNDLE_TAG_RESTAURANT_NAME, restaurantName);
    	args.putString(BUNDLE_TAG_MENU_NAME, menuName);
    	args.putString(BUNDLE_TAG_ITEM_NAME, itemName);
    	menuItemOptionsDialog.setArguments(args);
    	menuItemOptionsDialog.show(getFragmentManager(), FRAGMENT_TAG_ITEM_OPTIONS);
    }
}

package com.arrowfoodcouriers.arrowfood.Fragments;

import java.util.Arrays;
import java.util.List;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
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
import com.arrowfoodcouriers.arrowfood.Loaders.MenuLoader;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuRequest;
import com.arrowfoodcouriers.arrowfood.RoboSpice.MenuRequestListener;

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
    	restaurantName = bundle.getString(RestaurantMenuCategoryFragment.RESTAURANT_NAME);
    	menuName = bundle.getString(RestaurantMenuCategoryFragment.MENU_NAME);
    	
    	MenuRequest request = new MenuRequest();
		MainActivity.spiceManager.execute(request, new MenuRequestListener(getActivity()));
    	
    	ImageView menuHeaderImage = (ImageView) view.findViewById(R.id.menu_header_image);
    	menuHeaderImage.setImageResource(R.drawable.qdoba_storefront);
    	
    	Typeface rokkitt = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Rokkitt-Regular.ttf");
    	
    	TextView menuRestaurantName = (TextView) view.findViewById(R.id.menu_restaurant_name);
    	menuRestaurantName.setText(restaurantName);
    	menuRestaurantName.setTypeface(rokkitt);
    	
    	//List<MenuItem> menuItems = new ArrayList<MenuItem>();
        //mAdapter = new MenuAdapter(inflater.getContext(), menuItems);
        //setListAdapter(mAdapter);

        getLoaderManager().initLoader(3, getArguments(), new MenuLoaderCallback(getActivity()));
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
    
    private class MenuLoaderCallback implements LoaderManager.LoaderCallbacks<List<Menu>>
    {
    	private Context context;
    	private String restaurantName;
    	private String menuName;
    	
    	public MenuLoaderCallback(Context context)
    	{
    		this.context = context;
    	}
    	
    	@Override
    	public Loader<List<Menu>> onCreateLoader(int id, Bundle args) {
    		restaurantName = args.getString(RestaurantMenuCategoryFragment.RESTAURANT_NAME);
        	menuName = args.getString(RestaurantMenuCategoryFragment.MENU_NAME);
    		return new MenuLoader(context);
    	}

    	@Override
    	public void onLoadFinished(Loader<List<Menu>> loader, List<Menu> data) {
    		for(Menu menu : data) {
    			if (restaurantName.equals(menu.getRestaurant()) &&
    					menuName.equals(menu.getName())) {
    				setListAdapter(new MenuAdapter(this.context, Arrays.asList(menu.getItems())));
    			}
    		}
    	}

    	@Override
    	public void onLoaderReset(Loader<List<Menu>> loader) {
    		setListAdapter(null);
    	}    	
    	
    }
}

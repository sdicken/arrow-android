package com.arrowfoodcouriers.arrowfood;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.DrawerListAdapter;
import com.arrowfoodcouriers.arrowfood.Fragments.AboutFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.AreasMapFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.FavoriteOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.FoodSearchFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.LegalFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.PreviousOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.ProfileFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RestaurantFragment;


public class MainActivity extends Activity {
    private static final int HOME_NAV_DRAWER_POSITION = 0;
    private static final int RESTAURANTS_NAV_DRAWER_POSITION = 1;
    private static final int FOOD_SEARCH_NAV_DRAWER_POSITION = 2;
    private static final int PROFILE_NAV_DRAWER_POSITION = 4;
    private static final int PREV_ORDERS_NAV_DRAWER_POSITION = 5;
    private static final int FAVE_ORDERS_NAV_DRAWER_POSITION = 6;
    private static final int AREAS_MAP_NAV_DRAWER_POSITION = 8;
    private static final int ABOUT_NAV_DRAWER_POSITION = 9;
    private static final int LEGAL_NAV_DRAWER_POSITION = 10;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new DrawerListAdapter());
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        configureActionBar();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_opening,
                R.string.drawer_closing
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // Create fragment here
            Fragment fragment = new PlaceholderFragment();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem(int position) {
        Fragment fragment;
        switch (position) {
            case HOME_NAV_DRAWER_POSITION: {
                fragment = new PlaceholderFragment();
                break;
            }

            case RESTAURANTS_NAV_DRAWER_POSITION: {
                fragment = new RestaurantFragment();
                break;
            }

            case FOOD_SEARCH_NAV_DRAWER_POSITION: {
                fragment = new FoodSearchFragment();
                break;
            }

            case PROFILE_NAV_DRAWER_POSITION: {
                fragment = new ProfileFragment();
                break;
            }

            case PREV_ORDERS_NAV_DRAWER_POSITION: {
                fragment = new PreviousOrdersFragment();
                break;
            }

            case FAVE_ORDERS_NAV_DRAWER_POSITION: {
                fragment = new FavoriteOrdersFragment();
                break;
            }

            case AREAS_MAP_NAV_DRAWER_POSITION: {
                fragment = new AreasMapFragment();
                break;
            }

            case ABOUT_NAV_DRAWER_POSITION: {
                fragment = new AboutFragment();
                break;
            }

            case LEGAL_NAV_DRAWER_POSITION: {
                fragment = new LegalFragment();
                break;
            }

            default: {
                Log.d("DEBUG", "THE POSITION IS " + position);
                fragment = new PlaceholderFragment();
                break;
            }
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        mDrawerLayout.closeDrawers();
    }

    private void configureActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.action_bar);   // contains centered Arrow logo in white
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);    // display custom view instead of title
        actionBar.setHomeButtonEnabled(true);
        actionBar.setLogo(R.drawable.ic_drawer);        // use navigation drawer indicator as logo
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return  rootView;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}

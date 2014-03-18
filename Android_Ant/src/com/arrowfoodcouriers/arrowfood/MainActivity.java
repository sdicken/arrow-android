package com.arrowfoodcouriers.arrowfood;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.ActionBar;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.arrowfoodcouriers.arrowfood.Adapter.DrawerListAdapter;
import com.arrowfoodcouriers.arrowfood.Callbacks.RESTCallback;
import com.arrowfoodcouriers.arrowfood.Fragments.AboutFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.AreasMapFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.FavoriteOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.FoodSearchFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.LoginDialogFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.PreviousOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.ProfileFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RegistrationDialogFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RestaurantFragment;
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.ISession;
import com.arrowfoodcouriers.arrowfood.Interfaces.SessionFactory;
import com.arrowfoodcouriers.arrowfood.OpenCart.OpenCartSession;
import com.google.inject.Inject;

	
public class MainActivity extends RoboActivity implements INavigationDrawerCallback
{
    private static final int HOME_NAV_DRAWER_POSITION = 0;
    private static final int RESTAURANTS_NAV_DRAWER_POSITION = 1;
    private static final int FOOD_SEARCH_NAV_DRAWER_POSITION = 2;
    private static final int PROFILE_NAV_DRAWER_POSITION = 3;
    private static final int LOGIN_NAV_DRAWER_POSITION = 4;
    private static final int PREV_ORDERS_NAV_DRAWER_POSITION = 5;
    private static final int FAVE_ORDERS_NAV_DRAWER_POSITION = 6;
    private static final int AREAS_MAP_NAV_DRAWER_POSITION = 7;
    private static final int ABOUT_NAV_DRAWER_POSITION = 8;
    private static final int SIGN_OUT_NAV_DRAWER_POSITION = 9;

    private static final String BUNDLE_TAG_SESSION = "session";
    private static final String BUNDLE_TAG_LOGIN_FRAGMENT = "login_fragment";
    private static final String BUNDLE_TAG_REGISTER_FRAGMENT = "register_fragment";
    
    private static final String FRAGMENT_TAG_LOGIN = "login";
    public static final String FRAGMENT_TAG_REGISTER = "register";

    @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.left_drawer) ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    @Inject private SessionFactory _sessionFactory; // injected by RoboGuice
    private ILoginDialogCallback _loginDialogCallback;
    private IRegistrationDialogCallback _registrationDialogCallback;
    private ISession _session;
    
    public MainActivity()
    {
    	
    }

	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);  // prevent delay in loading ActionBar
        getActionBar().hide();
        setContentView(R.layout.activity_main);
        getActionBar().show();

        if(savedInstanceState != null)	// scenario where user changing between apps
        {
            _session = (OpenCartSession) savedInstanceState.get(BUNDLE_TAG_SESSION); // retrieve previous session state (cookies and auth state)
//            _registrationDialogCallback = new RegistrationDialogFragment();
            instantiateRegisterCallback(savedInstanceState);
            instantiateLoginCallback(savedInstanceState, _registrationDialogCallback);	// refresh callback in scenario where dialog was already open before state lost      
            
        	_session = _sessionFactory.create(_session,								// recreate session
        			new RESTCallback(this, _loginDialogCallback, _registrationDialogCallback), 
        			this, 
        			_loginDialogCallback, 
        			_registrationDialogCallback);
        }
        else
        {
        	_registrationDialogCallback = new RegistrationDialogFragment();
        	_loginDialogCallback = new LoginDialogFragment(_registrationDialogCallback);
        	_session = _sessionFactory.create(new RESTCallback(this, _loginDialogCallback, _registrationDialogCallback), 
        			this, 
        			_loginDialogCallback, 
        			_registrationDialogCallback);

            // Create fragment here
            Fragment fragment = new PlaceholderFragment();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }

        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new DrawerListAdapter(_session));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        configureActionBar();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_empty,
                R.string.drawer_opening,
                R.string.drawer_closing
        ) {
            public void onDrawerClosed(View view) 
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) 
            {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private boolean loginDialogWasShowingBeforeSave(Bundle savedInstanceState) 
    {
    	Fragment fragment = (LoginDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_LOGIN_FRAGMENT);
    	return (fragment != null);
	}
    
    private boolean registerDialogWasShowingBeforeSave(Bundle savedInstanceState) 
	{
    	Fragment fragment = (RegistrationDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_REGISTER_FRAGMENT);
    	return (fragment != null);
	}

    // show fragment and give it a tag for reference later
	private void displayLoginDialogFragment() 
    {
    	((LoginDialogFragment)_loginDialogCallback).show(getFragmentManager(), FRAGMENT_TAG_LOGIN);
	}

	// retrieve existing LoginDialogFragment instance (if available) or create new
	private void instantiateLoginCallback(Bundle savedInstanceState,
			IRegistrationDialogCallback registrationDialogCallback) 
	{
    	if(loginDialogWasShowingBeforeSave(savedInstanceState))
		{
    		_loginDialogCallback = (LoginDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_LOGIN_FRAGMENT);
    		_loginDialogCallback.attachRegistrationDialogCallback(registrationDialogCallback);
		}
        else
        {
        	_loginDialogCallback = new LoginDialogFragment(registrationDialogCallback);
        }		
	}
	
	// retrieve existing LoginDialogFragment instance (if available) or create new
	private void instantiateRegisterCallback(Bundle savedInstanceState) 
	{
    	if(registerDialogWasShowingBeforeSave(savedInstanceState))
		{
    		_registrationDialogCallback = (RegistrationDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_REGISTER_FRAGMENT);
		}
        else
        {
        	_registrationDialogCallback = new RegistrationDialogFragment();
        }		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) 
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // this gets called when user leaves app running and app gets killed because system needs memory
    @Override
    protected void onSaveInstanceState(Bundle outState) 
    {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_TAG_SESSION, _session);
        Fragment fragment = (LoginDialogFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_LOGIN);
        if(fragment != null && fragment.isAdded())
        	getFragmentManager().putFragment(outState, BUNDLE_TAG_LOGIN_FRAGMENT, (LoginDialogFragment)_loginDialogCallback);
        fragment = (RegistrationDialogFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_REGISTER);
        if(fragment != null && fragment.isAdded())
        	getFragmentManager().putFragment(outState, BUNDLE_TAG_REGISTER_FRAGMENT, (RegistrationDialogFragment)_registrationDialogCallback);
    }

    // this is currently not being called
    @Override
    protected void onPostCreate(Bundle savedInstanceState) 
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    // this gets called when the user changes device orientation or changes screen size
    // enabled via android:configChanges in AndroidManifest.xml
    @Override
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem(int position) 
    {
        Fragment fragment;
        switch (position) 
        {
            case HOME_NAV_DRAWER_POSITION: 
            {
                fragment = new PlaceholderFragment();
                break;
            }

            case RESTAURANTS_NAV_DRAWER_POSITION: 
            {
                fragment = new RestaurantFragment();
                break;
            }

            case FOOD_SEARCH_NAV_DRAWER_POSITION: 
            {
                fragment = new FoodSearchFragment();
                break;
            }

            case PROFILE_NAV_DRAWER_POSITION: 
            {
                fragment = new ProfileFragment();
                break;
            }

            case PREV_ORDERS_NAV_DRAWER_POSITION: 
            {
                fragment = new PreviousOrdersFragment();
                break;
            }

            case FAVE_ORDERS_NAV_DRAWER_POSITION: 
            {
                fragment = new FavoriteOrdersFragment();
                break;
            }

            case AREAS_MAP_NAV_DRAWER_POSITION: 
            {
                fragment = new AreasMapFragment();
                break;
            }

            case ABOUT_NAV_DRAWER_POSITION: 
            {
                fragment = new AboutFragment();
                break;
            }

            case SIGN_OUT_NAV_DRAWER_POSITION: 
            {
                fragment = new PlaceholderFragment();
                _session.Logout();
                break;
            }

            case LOGIN_NAV_DRAWER_POSITION: 
            {
            	displayLoginDialogFragment();
                fragment = new PlaceholderFragment();
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
    
    public ISession getSession()
    {
    	return _session;
    }

    private void configureActionBar() 
    {
        final ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.action_bar);   // contains centered Arrow logo in white
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);    // display custom view instead of title
        actionBar.setHomeButtonEnabled(true);
        actionBar.setLogo(R.drawable.white_drawer);        // use navigation drawer indicator as logo
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onNavigationDrawerUpdated() 
    {
        ((BaseAdapter)mDrawerList.getAdapter()).notifyDataSetChanged();
    }


    public static class PlaceholderFragment extends Fragment 
    {

        public PlaceholderFragment() 
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return  rootView;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        private ListView listView = (ListView)findViewById(R.id.left_drawer);

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DrawerListObject selectedFromList =
                    (DrawerListObject)listView.getItemAtPosition(position);

            selectItem(selectedFromList.position);
        }
    }
}

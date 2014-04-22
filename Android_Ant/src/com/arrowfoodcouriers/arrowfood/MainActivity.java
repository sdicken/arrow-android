package com.arrowfoodcouriers.arrowfood;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
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
import android.widget.ListView;
import android.widget.TextView;

import com.arrowfoodcouriers.arrowfood.Adapter.DrawerListAdapter;
import com.arrowfoodcouriers.arrowfood.Fragments.CartFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.FavoriteOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.LoginDialogFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.PreviousOrdersFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.ProfileFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RegistrationDialogFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.RestaurantFragment;
import com.arrowfoodcouriers.arrowfood.Fragments.TrackingFragment;
import com.arrowfoodcouriers.arrowfood.Loaders.DrawerValuesLoader;
import com.arrowfoodcouriers.arrowfood.Loaders.UserAccountLoader;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartContentsListener;
import com.arrowfoodcouriers.arrowfood.RoboSpice.CartContentsRequest;
import com.octo.android.robospice.GsonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;

	
public class MainActivity extends Activity
{
    private static final int HOME_NAV_DRAWER_POSITION = 0;
    private static final int RESTAURANTS_NAV_DRAWER_POSITION = 1;
    private static final int TRACK_NAV_DRAWER_POSITION = 2;
    private static final int PROFILE_NAV_DRAWER_POSITION = 3;
    private static final int LOGIN_NAV_DRAWER_POSITION = 4;
    private static final int PREV_ORDERS_NAV_DRAWER_POSITION = 5;
    private static final int FAVE_ORDERS_NAV_DRAWER_POSITION = 6;
    private static final int SIGN_OUT_NAV_DRAWER_POSITION = 7;
    
    private static final int USER_ACCOUNT_LOADER = 1;
    private static final int NAV_DRAWER_VALUE_LOADER = 2;

    private static final String BUNDLE_TAG_LOGIN_FRAGMENT = "login_fragment";
    private static final String BUNDLE_TAG_REGISTER_FRAGMENT = "register_fragment";
    
    private static final String CLIENT_ID = "ATE_yRBSgW-8LCAH7hcG0Y1EA6Zm-7zPg6zBtk4QC_l5BPMbX_URHCmN-nz_";
    
    private static final String FRAGMENT_TAG_LOGIN = "login";
    public static final String FRAGMENT_TAG_REGISTER = "register";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    private LoginDialogFragment loginDialogFragment;
    private RegistrationDialogFragment registrationDialogFragment;
    
    private static PayPalConfiguration config = new PayPalConfiguration()
    .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
    .clientId(CLIENT_ID)
    .defaultUserEmail("sd.nfltitan@gmail.com")
    .sandboxUserPassword("passpass");
    
    public static SpiceManager spiceManager = new SpiceManager(GsonSpringAndroidSpiceService.class);

	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);  // prevent delay in loading ActionBar
        getActionBar().hide();
        setContentView(R.layout.activity_main);
        getActionBar().show();
        
        CookieHandler.setDefault(new CookieManager());
        
        // PayPal setup
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        
        // TODO: Remove because this is for debugging only
//        GsonDataLoader<User> loader = new GsonDataLoader<User>(this, "user", User.class);
//        User testUser = new User("test", "customer", "test@test.test", "Tester Test", "123 Fake Address", "", "Louisville", "KY", "40208", new Date().getTime());
//        int size = 2;
//        User testUser = new User("test", "pass", "Customer", "test@test.test", "Tester Test", null, null, new String[size], new Phone[size], new Address[]{new Address("123 Fake Address", "", "Louisville", "KY", "40291")}, new Date().getTime(), new Date().getTime(), Integer.valueOf(2), Integer.valueOf(5));
//        loader.saveData(testUser);
        //-------------------------------------------------

        if(savedInstanceState != null)	// scenario where user changing between apps
        {
            instantiateRegisterCallback(savedInstanceState);
            instantiateLoginCallback(savedInstanceState, registrationDialogFragment);	// refresh callback in scenario where dialog was already open before state lost
        }
        else
        {
        	registrationDialogFragment = new RegistrationDialogFragment();
        	loginDialogFragment = new LoginDialogFragment();
            
            FragmentManager fragmentManager = getFragmentManager();
            if(fragmentManager.findFragmentById(android.R.id.content) == null)
            {
            	Fragment fragment = new RestaurantFragment();
            	fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            }
        }

        mTitle = mDrawerTitle = getTitle();
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.addHeaderView(View.inflate(this, R.layout.navdrawer_header, null), null, false);
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
        getLoaderManager().initLoader(USER_ACCOUNT_LOADER, null, new UserLoaderCallback(this));
        getLoaderManager().initLoader(NAV_DRAWER_VALUE_LOADER, null, new DrawerValuesLoaderCallback(this));
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
    	loginDialogFragment.show(getFragmentManager(), FRAGMENT_TAG_LOGIN);
	}

	// retrieve existing LoginDialogFragment instance (if available) or create new
	private void instantiateLoginCallback(Bundle savedInstanceState,
			RegistrationDialogFragment registrationDialogFragment) 
	{
    	if(loginDialogWasShowingBeforeSave(savedInstanceState))
		{
    		loginDialogFragment = (LoginDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_LOGIN_FRAGMENT);
		}
        else
        {
        	loginDialogFragment = new LoginDialogFragment();
        }		
	}
	
	// retrieve existing RegisterDialogFragment instance (if available) or create new
	private void instantiateRegisterCallback(Bundle savedInstanceState) 
	{
    	if(registerDialogWasShowingBeforeSave(savedInstanceState))
		{
    		registrationDialogFragment = (RegistrationDialogFragment) getFragmentManager().getFragment(savedInstanceState, BUNDLE_TAG_REGISTER_FRAGMENT);
		}
        else
        {
        	registrationDialogFragment = new RegistrationDialogFragment();
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
        else if(item.getTitle().equals(getResources().getString(R.string.actionbar_cart)))
        {
        	CartContentsRequest request = new CartContentsRequest();
        	spiceManager.execute(request, new CartContentsListener(this));
        	Utils.loadFragment(getFragmentManager(), new CartFragment());
        	return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onStart() 
    {
    	super.onStart();
    	spiceManager.start(this);
    }
    
    @Override
    protected void onStop() 
    {
    	spiceManager.shouldStop();
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() 
    {
    	stopService(new Intent(this, PayPalService.class));
    	super.onDestroy();
    }

    // this gets called when user leaves app running and app gets killed because system needs memory
    @Override
    protected void onSaveInstanceState(Bundle outState) 
    {
        super.onSaveInstanceState(outState);
        Fragment fragment = (LoginDialogFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_LOGIN);
        if(fragment != null && fragment.isAdded())
        	getFragmentManager().putFragment(outState, BUNDLE_TAG_LOGIN_FRAGMENT, loginDialogFragment);
        fragment = (RegistrationDialogFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_REGISTER);
        if(fragment != null && fragment.isAdded())
        	getFragmentManager().putFragment(outState, BUNDLE_TAG_REGISTER_FRAGMENT, registrationDialogFragment);
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
                fragment = new RestaurantFragment();
                break;
            }

            case RESTAURANTS_NAV_DRAWER_POSITION: 
            {
                fragment = new RestaurantFragment();
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

            case SIGN_OUT_NAV_DRAWER_POSITION: 
            {
                fragment = new RestaurantFragment();
                break;
            }

            case LOGIN_NAV_DRAWER_POSITION: 
            {
            	displayLoginDialogFragment();
                fragment = new RestaurantFragment();
                break;
            }
            
            case TRACK_NAV_DRAWER_POSITION:
            {
            	fragment = new TrackingFragment();
            	break;
            }

            default: 
            {
                Log.d("DEBUG", "THE POSITION IS " + position);
                fragment = new RestaurantFragment();
                break;
            }
        }
        Utils.loadFragment(getFragmentManager(), fragment);
        mDrawerLayout.closeDrawers();
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

    private void updateNavHeader(User userData) {
    	
    	if (userData == null) {
    		return;
    	}
    	
    	String name = userData.getName();
    	String fullAddress = userData.getAddresses()[0].getAddress1() + " " + 
    	userData.getAddresses()[0].getAddress2() + ", " + 
    			userData.getAddresses()[0].getCity() + ", " + 
    	userData.getAddresses()[0].getState() + " " + 
    			userData.getAddresses()[0].getZip();
    	
    	TextView nameTextView = (TextView)findViewById(R.id.header_name);
    	TextView addressTextView = (TextView)findViewById(R.id.header_address);
    	nameTextView.setText(name);
    	addressTextView.setText(fullAddress);
    	
    }
    
    private void resetNavHeader() {
    	
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        private ListView listView = (ListView)findViewById(R.id.left_drawer);

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DrawerListObject selectedFromList =
                    (DrawerListObject)listView.getItemAtPosition(position);

            selectItem(selectedFromList.getPosition());
        }
    }
    
    private class UserLoaderCallback implements LoaderManager.LoaderCallbacks<User>
    {
    	private Context context;
    	
    	public UserLoaderCallback(Context context)
    	{
    		this.context = context;
    	}
    	
    	@Override
    	public Loader<User> onCreateLoader(int id, Bundle args) {
    		return new UserAccountLoader(context);
    	}

    	@Override
    	public void onLoadFinished(Loader<User> loader, User data) {
    		updateNavHeader(data);
    		
    	}

    	@Override
    	public void onLoaderReset(Loader<User> loader) {
    		resetNavHeader();
    		
    	}    	
    	
    }
    
    private class DrawerValuesLoaderCallback implements LoaderManager.LoaderCallbacks<List<DrawerListObject>>
    {
    	private Context context;
    	
    	public DrawerValuesLoaderCallback(Context context)
    	{
    		this.context = context;
    	}
    	
    	@Override
    	public Loader<List<DrawerListObject>> onCreateLoader(int id, Bundle args) {
    		return new DrawerValuesLoader(context);
    	}

    	@Override
    	public void onLoadFinished(Loader<List<DrawerListObject>> loader, List<DrawerListObject> data) {
    		mDrawerList.setAdapter(new DrawerListAdapter(data));
    	}

    	@Override
    	public void onLoaderReset(Loader<List<DrawerListObject>> loader) {
    		
    	}    	
    	
    }
}

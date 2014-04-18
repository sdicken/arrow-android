package com.arrowfoodcouriers.arrowfood;

import java.util.Date;
import java.util.List;

import org.json.JSONException;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager.LoaderCallbacks;
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
import android.widget.BaseAdapter;
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
import com.arrowfoodcouriers.arrowfood.Interfaces.ILoginDialogCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.INavigationDrawerCallback;
import com.arrowfoodcouriers.arrowfood.Interfaces.IRegistrationDialogCallback;
import com.arrowfoodcouriers.arrowfood.Loaders.DrawerValuesLoader;
import com.arrowfoodcouriers.arrowfood.Loaders.UserAccountLoader;
import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.arrowfoodcouriers.arrowfood.gson.GsonDataLoader;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

	
public class MainActivity extends RoboActivity implements INavigationDrawerCallback
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
    private static final String PAYPAL = "paypal";
    
    private static final String FRAGMENT_TAG_LOGIN = "login";
    public static final String FRAGMENT_TAG_REGISTER = "register";

    @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.left_drawer) ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    private ILoginDialogCallback _loginDialogCallback;
    private IRegistrationDialogCallback _registrationDialogCallback;
    
    private static PayPalConfiguration config = new PayPalConfiguration()
    .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
    .clientId(CLIENT_ID);
    
    private LoaderCallbacks<User> userAccountLoaderListener = new LoaderCallbacks<User>() {

    	@Override
    	public Loader<User> onCreateLoader(int id, Bundle args) {
    		return new UserAccountLoader(MainActivity.this);
    	}

    	@Override
    	public void onLoadFinished(Loader<User> loader, User data) {
    		updateNavHeader(data);
    		
    	}

    	@Override
    	public void onLoaderReset(Loader<User> loader) {
    		resetNavHeader();
    		
    	}    	
    };
    
    private LoaderCallbacks<List<DrawerListObject>> drawerValuesLoaderListener = new LoaderCallbacks<List<DrawerListObject>>() {

    	@Override
    	public Loader<List<DrawerListObject>> onCreateLoader(int id, Bundle args) {
    		return new DrawerValuesLoader(MainActivity.this);
    	}

    	@Override
    	public void onLoadFinished(Loader<List<DrawerListObject>> loader, List<DrawerListObject> data) {
    		MainActivity.this.mDrawerList.setAdapter(new DrawerListAdapter(data));
    	}

    	@Override
    	public void onLoaderReset(Loader<List<DrawerListObject>> loader) {
    		
    	}    	
    };
    
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
        
        // PayPal setup
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        
        // TODO: Remove because this is for debugging only
        GsonDataLoader<User> loader = new GsonDataLoader<User>(this, "user", User.class);
//        User testUser = new User("test", "customer", "test@test.test", "Tester Test", "123 Fake Address", "", "Louisville", "KY", "40208", new Date().getTime());
        int size = 2;
        User testUser = new User("test", "pass", "Customer", "test@test.test", "Tester Test", null, null, new String[size], new Phone[size], new Address[]{new Address("123 Fake Address", "", "Louisville", "KY", "40291")}, new Date().getTime(), new Date().getTime(), Integer.valueOf(2), Integer.valueOf(5));
        loader.saveData(testUser);
        //-------------------------------------------------

        if(savedInstanceState != null)	// scenario where user changing between apps
        {
            instantiateRegisterCallback(savedInstanceState);
            instantiateLoginCallback(savedInstanceState, _registrationDialogCallback);	// refresh callback in scenario where dialog was already open before state lost
        }
        else
        {
        	_registrationDialogCallback = new RegistrationDialogFragment();
        	_loginDialogCallback = new LoginDialogFragment(_registrationDialogCallback);

            // Create fragment here
            Fragment fragment = new RestaurantFragment();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }

        mTitle = mDrawerTitle = getTitle();

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
        getLoaderManager().initLoader(USER_ACCOUNT_LOADER, null, userAccountLoaderListener);
        getLoaderManager().initLoader(NAV_DRAWER_VALUE_LOADER, null, drawerValuesLoaderListener);
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
	
	// retrieve existing RegisterDialogFragment instance (if available) or create new
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
        else if(item.getTitle().equals(getResources().getString(R.string.actionbar_cart)))
        {
        	Utils.loadFragment(getFragmentManager(), new CartFragment());
        	return true;
        }

        return super.onOptionsItemSelected(item);
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
    	if(resultCode == Activity.RESULT_OK)
    	{
    		PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) 
            {
                try {
                    Log.i(PAYPAL, confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e(PAYPAL, "an extremely unlikely failure occurred: ", e);
                }
            }
    	}
    	else if(resultCode == Activity.RESULT_CANCELED)
    	{
    		Log.i(PAYPAL, "The user canceled.");
    	}
    	else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
    	{
    		Log.i(PAYPAL, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
    	}
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
}

package com.arrowfoodcouriers.arrowfood;

import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.arrowfoodcouriers.arrowfood.Models.Address;
import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.Email;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.Models.User;

public class Utils 
{
	private static final String URL = "http://rest-arrow.herokuapp.com/{query}";
//	private static final String USER = URL + "/user";
	
	public static void loadFragment(FragmentManager fragmentManager, Fragment fragment)
    {
    	FragmentTransaction ft = fragmentManager.beginTransaction();
    	ft.replace(R.id.container, fragment);
    	ft.addToBackStack(null);
    	ft.commit();
    }

	public static CartItem[] getCartItems() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
		int size = 2;
		CartItem [] items = new CartItem[size];
		items[0] = new CartItem("Qdoba", "Burrito deluxe", 1, 2.99, new Date().getTime(), new Date().getTime());
		items[1] = new CartItem("Quills", "Small coffee", 1, 2.99, new Date().getTime(), new Date().getTime());
		return items;
	}

	public static Cart getCart() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
		return new Cart("", new Date().getTime(), new Date().getTime(), null, null, 4.22);
	}
	
	public static MenuItem[] getMenuItems()
	{
		int size = 1;
		MenuItem[] menuItems = new MenuItem[size];
		menuItems[0] = new MenuItem("Burrito", 0.99, null, null, null, null, "House special", new Date().getTime(), null);
		return menuItems;
	}
	
	public static Restaurant[] getRestaurants()
	{
		int size = 7;
		Restaurant[] restaurants = new Restaurant[size];
		Email[] emails = new Email[] {new Email("Qdoba", "", new Date().getTime())};
		Phone[] phones = new Phone[] {new Phone("Qdoba", "(111) 123 2234", new Date().getTime())};
		Address[] addresses = new Address[] {new Address("111 22nd Street", "", "Louisville", "KY", "40202")};
		restaurants[0] = new Restaurant("J. Gumbos", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[1] = new Restaurant("Hill Street Fish Fry", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[2] = new Restaurant("Northend Cafe", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[3] = new Restaurant("Smoketown USA", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[4] = new Restaurant("Burger Boy", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[5] = new Restaurant("Comfy Cow", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		restaurants[6] = new Restaurant("China Inn", null, null, "Mexican Grill", null, emails, phones, addresses, new Date().getTime(), new Date().getTime(), null);
		return restaurants;
	}
	
	public static HttpStatus postUser(User user)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class, "user");
		return responseEntity.getStatusCode();		
	}
	
	public static HttpStatus getOrders()
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class, "orders");
		return responseEntity.getStatusCode();	
	}
}

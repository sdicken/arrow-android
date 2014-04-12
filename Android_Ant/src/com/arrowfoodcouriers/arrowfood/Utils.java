package com.arrowfoodcouriers.arrowfood;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import com.arrowfoodcouriers.arrowfood.Models.Order;
import com.arrowfoodcouriers.arrowfood.Models.PasswordReset;
import com.arrowfoodcouriers.arrowfood.Models.Phone;
import com.arrowfoodcouriers.arrowfood.Models.Restaurant;
import com.arrowfoodcouriers.arrowfood.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils 
{
	private static final String ROUTE = "route";
	private static final String ROUTE2 = "route2";
	private static final String ROUTE3 = "route3";
	private static final String ROUTE4 = "route4";
	private static final String ROUTE5 = "route5";
	private static final String URL_1VAR = "http://rest-arrow.herokuapp.com/{" + ROUTE + "}";
	private static final String URL_2VAR = URL_1VAR + "/{" + ROUTE2 + "}";
	private static final String URL_3VAR = URL_2VAR + "/{" + ROUTE3 + "}";
	private static final String URL_4VAR = URL_3VAR + "/{" + ROUTE4 + "}";
	private static final String URL_5VAR = URL_4VAR + "/{" + ROUTE5 + "}";
	private static final String USER = "user";
	private static final String CART = "cart";
	private static final String PROFILE = "profile";
	private static final String MENU = "menu";
	private static final String MENUS = "menus";
	private static final String ORDER = "order";
	private static final String ORDERS = "orders";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String RESET = "reset";
	private static final String TOKEN = "token";
	private static final String RESTAURANT = "restaurant";
	private static final String ITEM = "item";
	private static final String QUANTITY = "quantity";
	
	public static void loadFragment(FragmentManager fragmentManager, Fragment fragment)
    {
    	FragmentTransaction ft = fragmentManager.beginTransaction();
    	ft.replace(R.id.container, fragment);
    	ft.addToBackStack(null);
    	ft.commit();
    }
	
	// this will not be implemented by server
	public static CartItem[] getCartItems() 
	{
		int size = 2;
		CartItem [] items = new CartItem[size];
items[0] = new CartItem("Qdoba", "Burrito deluxe", 1, 2.99, new Date(), new Date());
		items[1] = new CartItem("Quills", "Small coffee", 1, 2.99, new Date(), new Date());
		return items;
	}

	public static Cart getCart() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
//		return get(URL_1VAR, Collections.singletonMap(ROUTE, CART));
		return new Cart("", new Date(), new Date(), null, null, 4.22);
	}
		
	// this will not be implemented by server
	public static MenuItem[] getMenuItems()
	{
		int size = 1;
		MenuItem[] menuItems = new MenuItem[size];
		menuItems[0] = new MenuItem("Burrito", 0.99, null, null, null, null, "House special", new Date(), null);
		return menuItems;
	}
	
	// this will not be implemented by server
	public static Restaurant[] getRestaurants()
	{
		int size = 7;
		Restaurant[] restaurants = new Restaurant[size];
		Email[] emails = new Email[] {new Email("Qdoba", "", new Date())};
		Phone[] phones = new Phone[] {new Phone("Qdoba", "(111) 123 2234", new Date())};
		Address[] addresses = new Address[] {new Address("111 22nd Street", "", "Louisville", "KY", "40202")};
		restaurants[0] = new Restaurant("J. Gumbos", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[1] = new Restaurant("Hill Street Fish Fry", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[2] = new Restaurant("Northend Cafe", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[3] = new Restaurant("Smoketown USA", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[4] = new Restaurant("Burger Boy", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[5] = new Restaurant("Comfy Cow", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		restaurants[6] = new Restaurant("China Inn", null, null, "Mexican Grill", null, emails, phones, addresses, new Date(), new Date(), null);
		return restaurants;
	}
	
	public static ResponseEntity<String> postUser(User user)
	{
		// post to /user
		return post(URL_1VAR, Collections.singletonMap(ROUTE, USER), user);
	}
	
	public static ResponseEntity<String> getOrders()
	{
		// get from /orders
		return get(URL_1VAR, Collections.singletonMap(ROUTE, ORDERS));
	}
	
	public static ResponseEntity<String> getMenus()
	{
		// get from /menus
		return get(URL_1VAR, Collections.singletonMap(ROUTE, MENUS));	
	}
	
	public static ResponseEntity<String> changePassword(User user)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		// post to /user/password
		return post(URL_2VAR, urlVariables, user);
	}
	
	public static ResponseEntity<String> getProfile()
	{
		// get from /profile
		return get(URL_1VAR, Collections.singletonMap(ROUTE, PROFILE));
	}
	
	public static ResponseEntity<String> postCartOrder(Order order)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, ORDER);
		// post to cart/order
		return post(URL_2VAR, urlVariables, order);
	}
	
	public static ResponseEntity<String> login(User user)
	{
		// post to /login
		return post(URL_1VAR, Collections.singletonMap(ROUTE, LOGIN), user);
	}
	
	public static ResponseEntity<String> logout(User user)
	{
		// post to /logout
		return post(URL_1VAR, Collections.singletonMap(ROUTE, LOGOUT), user);
	}
	
	public static ResponseEntity<String> resetPassword(PasswordReset reset)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		urlVariables.put(ROUTE3, RESET);
		urlVariables.put(ROUTE4, TOKEN);	// TODO: is this correct?
		// post to /user/password/reset/:token
		return post(URL_4VAR, urlVariables, reset);
	}
	
	public static void deleteCart()
	{
		delete(URL_1VAR, Collections.singletonMap(ROUTE, CART));
	}
	
	public static void deleteCartItem()
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, RESTAURANT); // TODO: are these right?
		urlVariables.put(ROUTE3, MENU);
		urlVariables.put(ROUTE4, ITEM);
		// delete from /cart/:restaurant/:menu/:item
		delete(URL_4VAR, urlVariables);
	}
	
	public static void deleteCartItemQuantity()
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, RESTAURANT); // TODO: are these right?
		urlVariables.put(ROUTE3, MENU);
		urlVariables.put(ROUTE4, ITEM);
		urlVariables.put(ROUTE5, QUANTITY);
		// delete from /cart/:restaurant/:menu/:item/:quantity
		delete(URL_5VAR, urlVariables);
	}
	
	public static <T> T convertResponseEntityToModel(ResponseEntity<String> responseEntityContainingJSON, Class<T> classOfT)
	{
		// parses a date of this format 2014-04-12T21:20:10.307Z (MongoDB standard)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'").create();
		return gson.fromJson(responseEntityContainingJSON.getBody(), classOfT);
	}
	
	private static ResponseEntity<String> get(String url, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, route);
		return responseEntity;
	}
	
	private static <T> ResponseEntity<String> post(String url, Map<String, String> route, T data)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<T> requestEntity = new HttpEntity<T>(data, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, route);
		return responseEntity;	
	}
	
	private static <T> void delete(String url, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		restTemplate.delete(url, route);
	}
}

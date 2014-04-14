package com.arrowfoodcouriers.arrowfood;

import java.net.CookieHandler;
import java.net.CookieManager;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.arrowfoodcouriers.arrowfood.Models.Address;
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

public class RESTUtils 
{	
	private static final String HOST_NAME = "http://rest-arrow.herokuapp.com";
	private static final String ROUTE = "route";
	private static final String ROUTE2 = "route2";
	private static final String ROUTE3 = "route3";
	private static final String ROUTE4 = "route4";
	private static final String ROUTE5 = "route5";
	private static final String URL_1VAR = HOST_NAME + "/{" + ROUTE + "}";
	private static final String URL_2VAR = URL_1VAR + "/{" + ROUTE2 + "}";
	private static final String URL_3VAR = URL_2VAR + "/{" + ROUTE3 + "}";
	private static final String URL_4VAR = URL_3VAR + "/{" + ROUTE4 + "}";
	private static final String URL_5VAR = URL_4VAR + "/{" + ROUTE5 + "}";
	private static final String USER = "user";
	private static final String CART = "cart";
	private static final String PROFILE = "profile";
	private static final String MENUS = "menus";
	private static final String ORDER = "order";
	private static final String ORDERS = "orders";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String RESET = "reset";
	
	public RESTUtils()
	{
		CookieHandler.setDefault(new CookieManager());
		triggerCookieManagement();
	}
	
	// this will not be implemented by server
	public CartItem[] getCartItems() 
	{
		int size = 2;
		CartItem [] items = new CartItem[size];
		items[0] = new CartItem("Qdoba", "Burrito deluxe", 1, 2.99, new Date(), new Date());
		items[1] = new CartItem("Quills", "Small coffee", 1, 2.99, new Date(), new Date());
		return items;
	}
	
//		public static Cart getCart() 
//		{
//			// TODO: replace with RestTemplate object making REST API calls to server
//			return get(URL_1VAR, Collections.singletonMap(ROUTE, CART));
//			return new Cart("", new Date(), new Date(), null, null, 4.22);
//		}

	public ResponseEntity<String> getCart() 
	{
		// TODO: replace with RestTemplate object making REST API calls to server
		return get(URL_1VAR, Collections.singletonMap(ROUTE, CART));
//			return new Cart("", new Date(), new Date(), null, null, 4.22);
	}
		
	// this will not be implemented by server
	public MenuItem[] getMenuItems()
	{
		int size = 1;
		MenuItem[] menuItems = new MenuItem[size];
		menuItems[0] = new MenuItem("Burrito", 0.99, null, null, null, null, "House special", new Date(), null);
		return menuItems;
	}
	
	// this will not be implemented by server
	public Restaurant[] getRestaurants()
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
	
	public ResponseEntity<String> postUser(User user)
	{
		// post to /user
		return post(URL_1VAR, Collections.singletonMap(ROUTE, USER), user);
	}
	
	public ResponseEntity<String> getOrders()
	{
		// get from /orders
		return get(URL_1VAR, Collections.singletonMap(ROUTE, ORDERS));
	}
	
	public ResponseEntity<String> getMenus()
	{
		// get from /menus
		return get(URL_1VAR, Collections.singletonMap(ROUTE, MENUS));	
	}
	
	public ResponseEntity<String> changePassword(User user)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		// post to /user/password
		return post(URL_2VAR, urlVariables, user);
	}
	
	public ResponseEntity<String> getProfile()
	{
		// get from /profile
		return get(URL_1VAR, Collections.singletonMap(ROUTE, PROFILE));
	}
	
	public ResponseEntity<String> postCart(String restaurantName, String menuName, 
			String itemName, Integer quantity)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		urlVariables.put(ROUTE5, quantity.toString());
		// post to cart/order
		return post(URL_5VAR, urlVariables);
	}
	
	public ResponseEntity<String> postCartOrder(Order order)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, ORDER);
		// post to cart/order
		return post(URL_2VAR, urlVariables, order);
	}
	
	public ResponseEntity<String> login(String username, String password)
	{
		User user = new User(username, password);
		// post to /login
		return post(URL_1VAR, Collections.singletonMap(ROUTE, LOGIN), user);
	}
	
	public ResponseEntity<String> logout(User user)
	{
		// post to /logout
		return post(URL_1VAR, Collections.singletonMap(ROUTE, LOGOUT), user);
	}
	
	public ResponseEntity<String> resetPassword(PasswordReset reset, String token)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		urlVariables.put(ROUTE3, RESET);
		urlVariables.put(ROUTE4, token);
		// post to /user/password/reset/:token
		return post(URL_4VAR, urlVariables, reset);
	}
	
	public void deleteCart()
	{
		delete(URL_1VAR, Collections.singletonMap(ROUTE, CART));
	}
	
	public void deleteCartItem(String restaurantName, String menuName, String itemName)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		// delete from /cart/:restaurant/:menu/:item
		delete(URL_4VAR, urlVariables);
	}
	
	public void deleteCartItemQuantity(String restaurantName, String menuName, 
			String itemName, Integer quantity)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		urlVariables.put(ROUTE5, quantity.toString());
		// delete from /cart/:restaurant/:menu/:item/:quantity
		delete(URL_5VAR, urlVariables);
	}
	
	public static <T> T convertResponseEntityToModel(ResponseEntity<String> responseEntityContainingJSON, Class<T> classOfT)
	{
		// parses a date of this format 2014-04-12T21:20:10.307Z (MongoDB standard)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'").create();
		return gson.fromJson(responseEntityContainingJSON.getBody(), classOfT);
	}
	
	private ResponseEntity<String> get(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		requestHeaders.set("Set-Cookie", rawCookieStore.get(this.baseUrl).get(0).toString());
//		requestHeaders.set("Cookie", cookie);
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl, String.class, route);
		return responseEntity;
	}
	
	private <T> ResponseEntity<String> post(String baseUrl, Map<String, String> route, T data)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		requestHeaders.set("Set-Cookie", rawCookieStore.get(this.baseUrl).get(0).toString());
//		requestHeaders.set("Cookie", cookie);
		HttpEntity<T> requestEntity = new HttpEntity<T>(data, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl, requestEntity, String.class, route);
		return responseEntity;	
	}
	
	private <T> ResponseEntity<String> post(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		requestHeaders.set("Set-Cookie", rawCookieStore.get(this.baseUrl).get(0).toString());
//		requestHeaders.set("Cookie", cookie);
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl, requestEntity, String.class, route);
		return responseEntity;	
	}
	
	private <T> void delete(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
//		requestHeaders.set("Set-Cookie", rawCookieStore.get(this.baseUrl).get(0).toString());
//		requestHeaders.set("Cookie", cookie);
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		restTemplate.exchange(baseUrl, HttpMethod.DELETE, requestEntity, String.class, route);
//		restTemplate.delete(baseUrl, route);
	}
	
	private <T> void triggerCookieManagement()
	{
		HttpEntity<T> requestEntity = new HttpEntity<T>(new HttpHeaders());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		try
		{
			restTemplate.exchange(HOST_NAME, HttpMethod.GET, requestEntity, String.class);
		}
		catch(HttpClientErrorException e)
		{
			e.getStatusCode();
		}
		
	}
}

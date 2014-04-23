package com.arrowfoodcouriers.arrowfood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.arrowfoodcouriers.arrowfood.Models.Cart;
import com.arrowfoodcouriers.arrowfood.Models.CartItem;
import com.arrowfoodcouriers.arrowfood.Models.CartItemOption;
import com.arrowfoodcouriers.arrowfood.Models.Geotags;
import com.arrowfoodcouriers.arrowfood.Models.Menu;
import com.arrowfoodcouriers.arrowfood.Models.MenuItem;
import com.arrowfoodcouriers.arrowfood.Models.Order;
import com.arrowfoodcouriers.arrowfood.Models.PasswordReset;
import com.arrowfoodcouriers.arrowfood.Models.Response;
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
	private static final String MENUS = "menus";
	private static final String ORDER = "order";
	private static final String ORDERS = "orders";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String RESET = "reset";
	private static final String GEOTAG = "geotag";
	private static final String RESTAURANTS = "restaurants";
	private static final String PRICE = "price";
	private static final String UPDATED = "updated";
	
	// filter through data in cart
	public static List<CartItem> getCartItems()
	{
		Cart cart = getCart();
		CartItem[] items = cart.getItems();
		return Arrays.asList(items);
	}

	public static Cart getCart() 
	{
		// get from /cart
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, CART)), Cart.class);
	}
	
	public static Cart getCartPrice()
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, PRICE);
		// get from /cart/price
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Cart.class);
	}
	
	// filter through data in menu
	public static List<String> getMenuCategories(String restaurantName)
	{
		Menu[] menus = getMenus();
		List<String> menuCategories = new ArrayList<String>();
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName))
			{
				menuCategories.add(menu.getName());
			}
		}
		return menuCategories;
	}
	
	// filter through data in menu
	public static List<MenuItem> getMenuItems(String restaurantName, String menuName)
	{
		Menu[] menus = getMenus();
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName) && menu.getName().equals(menuName))
			{
				MenuItem[] items = menu.getItems();
				return Arrays.asList(items);
			}
		}
		return menuItems;
	}
	
	// filter through data in menu
	public static MenuItem getMenuItem(String restaurantName, String menuName, String itemName)
	{
		Menu[] menus = getMenus();
		MenuItem menuItem = null;
		for(int i = 0; i < menus.length; i++)
		{
			Menu menu = menus[i];
			if(menu.getRestaurant().equals(restaurantName) && menu.getName().equals(menuName))
			{
				MenuItem[] items = menu.getItems();
				for(int j = 0; j < items.length; j++)
				{
					MenuItem item = items[j];
					if(item.getName().equals(itemName))
					{
						return menuItem;
					}
				}
			}
		}
		return menuItem;
	}

	public static Restaurant[] getRestaurants()
	{
		// get from /restaurants
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, RESTAURANTS)), Restaurant[].class);
	}
	
	public static Response getRestaurantsUpdated()
	{
		// get from /restaurants/updated
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, RESTAURANTS);
		urlVariables.put(ROUTE2, UPDATED);
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Response.class);
	}
	
	// could return either User (scenario: updating existing user account) or Response (scenario: creating new user)
	public static ResponseEntity<String> postUser(User user)
	{
		// post to /user
		return post(URL_1VAR, Collections.singletonMap(ROUTE, USER), user);
	}
	
	public static Order[] getOrders()
	{
		// get from /orders
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, ORDERS)), Order[].class);
	}
	
	public static Response getOrdersUpdated()
	{
		//get from /orders/updated
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, ORDERS);
		urlVariables.put(ROUTE2, UPDATED);
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Response.class);
	}
	
	public static Menu[] getMenus()
	{
		// get from /menus
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, MENUS)), Menu[].class);	
	}
	
	public static Response getMenusUpdated()
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, MENUS);
		urlVariables.put(ROUTE2, UPDATED);
		// get from /menus/updated
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Response.class);
	}
	
	public Response postChangePassword(User user)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		// post to /user/password
		return convertResponseEntityToModel(post(URL_2VAR, urlVariables, user), Response.class);
	}
	
	public static User getUser()
	{
		// get from /user
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, USER)), User.class);
	}
	
	public static Response getUserUpdated()
	{
		// get from /user/updated
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, UPDATED);
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Response.class);
	}
	
	public static Response postCart(String restaurantName, String menuName, 
			String itemName, Integer quantity, CartItemOption[] itemOptions)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		urlVariables.put(ROUTE5, quantity.toString());
		// post to /cart/order
		return convertResponseEntityToModel(post(URL_5VAR, urlVariables, itemOptions), Response.class);
	}
	
	public static Response postCartOrder(Order order)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, ORDER);
		// post to cart/order
		return convertResponseEntityToModel(post(URL_2VAR, urlVariables, order), Response.class);
	}
	
	public static Response postLogin(String username, String password)
	{
		User user = new User(username, password);
		// post to /login
		return convertResponseEntityToModel(post(URL_1VAR, Collections.singletonMap(ROUTE, LOGIN), user), Response.class);
	}
	
	public static Response postLogout()
	{
		// post to /logout
		return convertResponseEntityToModel(post(URL_1VAR, Collections.singletonMap(ROUTE, LOGOUT)), Response.class);
	}
	
	public static Response postResetPassword(PasswordReset reset, String token)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, USER);
		urlVariables.put(ROUTE2, PASSWORD);
		urlVariables.put(ROUTE3, RESET);
		urlVariables.put(ROUTE4, token);
		// post to /user/password/reset/:token
		return convertResponseEntityToModel(post(URL_4VAR, urlVariables, reset), Response.class);
	}
	
	public static Response deleteCart()
	{
		return convertResponseEntityToModel(delete(URL_1VAR, Collections.singletonMap(ROUTE, CART)), Response.class);
	}
	
	public static Response deleteCartItem(String restaurantName, String menuName, String itemName)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		// delete from /cart/:restaurant/:menu/:item
		return convertResponseEntityToModel(delete(URL_4VAR, urlVariables), Response.class);
	}
	
	public static Response deleteCartItemQuantity(String restaurantName, String menuName, 
			String itemName, Integer quantity)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, CART);
		urlVariables.put(ROUTE2, restaurantName);
		urlVariables.put(ROUTE3, menuName);
		urlVariables.put(ROUTE4, itemName);
		urlVariables.put(ROUTE5, quantity.toString());
		// delete from /cart/:restaurant/:menu/:item/:quantity
		return convertResponseEntityToModel(delete(URL_5VAR, urlVariables), Response.class);
	}
	
	public static Geotags[] getGeo()
	{
		// GET from /geotag
		return convertResponseEntityToModel(get(URL_1VAR, Collections.singletonMap(ROUTE, GEOTAG)), Geotags[].class);
	}
	
	public static Response postGeo(Geotags tags)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, tags.getLatitude().toString());
		urlVariables.put(ROUTE2, tags.getLongitude().toString());
		// POST to /geotag/:latitude/:longitude
		return convertResponseEntityToModel(post(URL_2VAR, urlVariables), Response.class);
	}
	
	public static Geotags getGeoUser(String username)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, GEOTAG);
		urlVariables.put(ROUTE2, username);
		// GET from /geotag/:username
		return convertResponseEntityToModel(get(URL_2VAR, urlVariables), Geotags.class);
	}
	
	public static Geotags getGeoUserLimit(String username, Integer limit)
	{
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put(ROUTE, GEOTAG);
		urlVariables.put(ROUTE2, username);
		urlVariables.put(ROUTE3, limit.toString());
		// GET from /geotag/:username/:limit
		return convertResponseEntityToModel(get(URL_3VAR, urlVariables), Geotags.class);
	}
	
	public static <T> T convertResponseEntityToModel(ResponseEntity<String> responseEntityContainingJSON, Class<T> classOfT)
	{
		// parses a date of this format 2014-04-12T21:20:10.307Z (MongoDB standard)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'").create();
		return gson.fromJson(responseEntityContainingJSON.getBody(), classOfT);
	}
	
	private static <T> ResponseEntity<String> get(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		return restTemplate.exchange(baseUrl, HttpMethod.GET, requestEntity, String.class, route);
	}
	
	/**
	 * POST with a request body
	 * @param baseUrl
	 * @param route
	 * @param data
	 * @return
	 */
	private static <T> ResponseEntity<String> post(String baseUrl, Map<String, String> route, T data)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<T> requestEntity = new HttpEntity<T>(data, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
	
		return restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class, route);
	}
	
	/**
	 * POST without a request body
	 * @param baseUrl
	 * @param route
	 * @return
	 */
	private static <T> ResponseEntity<String> post(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		return restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class, route);
	}
	
	private static <T> ResponseEntity<String> delete(String baseUrl, Map<String, String> route)
	{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		
		return restTemplate.exchange(baseUrl, HttpMethod.DELETE, requestEntity, String.class, route);
	}
}

package com.arrowfoodcouriers.arrowfood.Models;

import java.util.Date;

public class Order 
{
	String username;
	Date createdOn;
	Address billing;
	Address shipping;
	OrderStatus status;
	Cart cart;
}

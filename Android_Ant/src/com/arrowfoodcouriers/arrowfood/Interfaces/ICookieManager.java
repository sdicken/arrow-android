package com.arrowfoodcouriers.arrowfood.Interfaces;

import java.io.IOException;
import java.net.URLConnection;

public interface ICookieManager 
{
	void storeCookies(URLConnection conn) throws IOException;
	void setCookies(URLConnection conn) throws IOException;
}

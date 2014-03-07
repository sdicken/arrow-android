package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OpenCartItem {
    public String ProductId;
    public String Quantity;
    public Map<String, String> Options;

    public OpenCartItem() {
        Options = new HashMap<String, String>();
    }

    public Map<String, String> GetData() {
        Map<String, String> data = new HashMap<String, String>();
        Set<String> keys = Options.keySet();
        Iterator<String> keyIter = keys.iterator();
        for (int i = 0; keyIter.hasNext(); ++i) {
            Object key = keyIter.next();
            data.put("option[" + key + "]", Options.get(key));
        }
        data.put("quantity", Quantity);
        data.put("product_id", ProductId);
        return data;
    }
}

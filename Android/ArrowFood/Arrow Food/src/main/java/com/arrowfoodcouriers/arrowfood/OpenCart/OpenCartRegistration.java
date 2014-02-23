package com.arrowfoodcouriers.arrowfood.OpenCart;

import java.util.HashMap;
import java.util.Map;

public class OpenCartRegistration {
    public String FirstName; // Required
    public String LastName; // Required
    public String Email; // Required
    public String Password; // Required
    public String ConfirmPassword; // Required
    public String Telephone; // Required
    public String Fax;

    public String Company;
    public String CompanyId;
    public String Address1; // Required
    public String Address2;
    public String City; // Required
    public String PostalCode; // Required
    public String CountryId; // Required
    public String ZoneId; // Required

    public String CustomerGroupId; // Required
    public String TaxId;

    public String Newsletter; // default false == "0"
    public String Agree; // default false == "0", required true == "1"

    public OpenCartRegistration() {
        City = "Louisville";
        CountryId = "223";
        ZoneId = "3639";
        CustomerGroupId = "1";
        TaxId = "";
        Newsletter = "0";
        Agree = "0";
    }

    public Boolean IsValid() {
        return FirstName != null && LastName != null
                && Email != null && Password != null
                && Telephone != null && Address1 != null
                && City != null && PostalCode != null
                && CountryId != null && ZoneId != null;
    }

    public Map<String, String> GetData() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("firstname", FirstName);
        data.put("lastname", LastName);
        data.put("email", Email);
        data.put("telephone", Telephone);
        data.put("fax", Fax);
        data.put("company", Company);
        data.put("customer_group_id", CustomerGroupId);
        data.put("company_id", CompanyId);
        data.put("tax_id", TaxId);
        data.put("address_1", Address1);
        data.put("address_2", Address2);
        data.put("city", City);
        data.put("postcode", PostalCode);
        data.put("country_id", CountryId);
        data.put("zone_id", ZoneId);
        data.put("password", Password);
        data.put("confirm", ConfirmPassword);
        data.put("newsletter", Newsletter);
        data.put("agree", Agree);
        return data;
    }
}

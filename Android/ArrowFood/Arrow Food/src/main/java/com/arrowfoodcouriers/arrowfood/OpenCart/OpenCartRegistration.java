package com.arrowfoodcouriers.arrowfood.OpenCart;

public class OpenCartRegistration {
    public String FirstName; // Required
    public String LastName; // Required
    public String Email; // Required
    public String Password; // Required
    public String Telephone; // Required
    public String Fax;

    public String Company;
    public String CompanyID;
    public String Address1; // Required
    public String Address2;
    public String City; // Required
    public String PostalCode; // Required
    public Integer Country; // Required
    public Integer Zone; // Required

    public Boolean Subscribe; // default false

    public OpenCartRegistration() {
        City = "Louisville";
        Country = 223;
        Zone = 3639;
    }

    public Boolean IsValid() {
        return FirstName != null && LastName != null
                && Email != null && Password != null
                && Telephone != null && Address1 != null
                && City != null && PostalCode != null
                && Country != null && Zone != null;
    }

    public String GetJson() {
        StringBuilder sb = new StringBuilder("{");
        // TODO: Finish this code
        sb.append('}');
        return sb.toString();
    }
}

package com.jiaohuan.jiaohuan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {

    private String names;
    private String company;
    private String phone_nums;
    private String emails;
    private String locations;
    private String address;
    private String additional_info;
    private int pictures;
    private int business_card;
    private String title;
    private String website;
    private int color;
    private int flipside;
    private String date;
    private SimpleDateFormat formatted;

    // Dummy constructor, just in case there's only a name
    public Contact(String n) {
        names = n;
        company = "Unemployed";
        phone_nums = "No phone";
        emails = "No email";
        locations = "None";
        pictures = 0;
        address = "Unknown";
        additional_info = "Unknown";
    }

    // Main constructor
    public Contact(String n, String c, String p, String e, String l, int pics, String a, String i, int card, String ti, String w, int col, int flip, String da, Date formatted) {
        names = n;
        company = c;
        phone_nums = p;
        emails = e;
        locations = l;
        pictures = pics;
        address = a;
        additional_info = i;
        business_card = card;
        title = ti;
        website = w;
        color = col;
        flipside = flip;
        date = da;

    }

    public Contact(String n, String c, String p, String e, String l, int pics, String a, String i) {
        names = n;
        company = c;
        phone_nums = p;
        emails = e;
        locations = l;
        pictures = pics;
        address = a;
        additional_info = i;
    }




    // Various methods for getting/setting the variables
    public String getName() {return names;}
    public void setName(String n) { names = n; }

    public String getCompany() {return company;}
    public void setCompany(String c) {company = c;}

    public String getPhoneNum() {return phone_nums;}
    public void setPhoneNum(String p) {phone_nums = p;}

    public String getEmail() {return emails;}
    public void setEmail(String e) {emails = e;}

    public String getLocation() {return locations;}
    public void setLocation(String l) {locations = l;}

    public String getAddress() {return address;}
    public void setAddress(String a) {address = a;}

    public String getInfo() {return additional_info;}
    public void setInfo(String i) {additional_info = i;}

    public int getPic() {return pictures;}
    public void setPic(int pi) {pictures = pi;}

    public int getBusiness_card() {return business_card;}
    public void setBusiness_card(int pi) {business_card = pi;}

    public String getTitle() {return title;}
    public void setTitle(String ti){title = ti;}

    public String getWebsite() {return website;}
    public void setWebsite(String ti){website = ti;}

    public int getColor() {return color;}
    public void setColor(int col){color = col;}

    public int getFlipside() {return flipside;}
    public void setFlipside(int flip){flipside = flip;}

    public String getDate() {return date;}
    public void setDate(SimpleDateFormat da){formatted = da;}

    public String getFormattedDate() {return formatted.toString();}
}

package com.jiaohuan.jiaohuan;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact {

    // TRYING BITMAP

    private String names;
    private String company;
    private String phone_nums;
    private String emails;
    private String locations;
    private String address;
    private String additional_info;
    private int pictures;
    private Bitmap business_card;
    private String title;
    private String website;
    private int color;
    private Bitmap flipside;
    private long unix_time;
    private String simple_date;
    private int IDnumber;

    // Main constructor
    public Contact(int id, String n, String c, String p, String e, String l, int pics, String a, String i, Bitmap card, String ti, String w, int col, Bitmap flip, long ux, String sdf) {

        IDnumber = id;
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
        unix_time = ux;
        simple_date = sdf;
    }

    // Various methods for getting/setting the variables
    public int getID() {return IDnumber;}
    public void setID(int id){IDnumber = id;}

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

    public Bitmap getBusiness_card() {return business_card;}
    public void setBusiness_card(Bitmap pi) {business_card = pi;}

    public String getTitle() {return title;}
    public void setTitle(String ti){title = ti;}

    public String getWebsite() {return website;}
    public void setWebsite(String ti){website = ti;}

    public int getColor() {return color;}
    public void setColor(int col){color = col;}

    public Bitmap getFlipside() {return flipside;}
    public void setFlipside(Bitmap flip){flipside = flip;}

    public long getUnix_time() {return unix_time;}

    public String getSimple_date() {return simple_date;}
    public void setSimpleDate(String sdf){simple_date = sdf;}

}

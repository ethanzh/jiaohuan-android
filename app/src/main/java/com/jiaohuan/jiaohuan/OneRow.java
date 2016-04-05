package com.jiaohuan.jiaohuan;

public class OneRow {
    private String names;
    private String company;
    String phone_nums;
    String emails;
    String locations;
    String address;
    String additional_info;
    int pictures;
    int business_card;

    public OneRow(String n) {
        names = n;
        company = "Unemployed";
        phone_nums = "No phone";
        emails = "No email";
        locations = "None";
        pictures = 0;
        address = "Unknown";
        additional_info = "Unknown";
    }

    public OneRow(String n, String c, String p, String e, String l, int pics, String a, String i, int card) {
        names = n;
        company = c;
        phone_nums = p;
        emails = e;
        locations = l;
        pictures = pics;
        address = a;
        additional_info = i;
        business_card = card;
    }

    public OneRow(String n, String c, String p, String e, String l, int pics, String a, String i) {
        names = n;
        company = c;
        phone_nums = p;
        emails = e;
        locations = l;
        pictures = pics;
        address = a;
        additional_info = i;
    }

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
    public void setBusiness_card(int pi) {pictures = business_card;}
}

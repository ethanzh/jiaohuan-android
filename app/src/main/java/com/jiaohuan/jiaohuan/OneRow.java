package com.jiaohuan.jiaohuan;

public class OneRow {
    private String names;
    String company;
    String phone_nums;
    String emails;
    String locations;
    String address;
    String additional_info;
    int pictures;

    public OneRow(String n) {
        names = n;
        company = "Unemployed";
        phone_nums = "No phone";
        emails = "No email";
        locations = "None";
        pictures = 0;
    }

    public OneRow(String n, String c, String p, String e, String l, int pics) {
        names = n;
        company = c;
        phone_nums = p;
        emails = e;
        locations = l;
        pictures = pics;
    }

    public String getNames() {return names;}
    public void setNames(String n) { names = n; }
}

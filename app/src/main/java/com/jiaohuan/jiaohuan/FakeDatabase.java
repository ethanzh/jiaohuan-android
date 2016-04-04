package com.jiaohuan.jiaohuan;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<OneRow> data;

    private FakeDatabase() {
        data = new ArrayList<>();

        OneRow row1 = new OneRow("Sangwook Park", "Gate Education", "15488965321", "SWP@hotmail.com", "Beijing, China", R.drawable.tae, "WangJing", "Likes to party");
        data.add(row1);

        OneRow row2 = new OneRow("Kevin Wu", "Unemployed", "45789632594", "kevinwu@gmail.com", "Beijing, China", R.drawable.tae, "Yosemite Apartment", "Likes to TH");
        data.add(row2);

        OneRow row3 = new OneRow("Tian Ge Liu");
        data.add(row3);

        OneRow row4 = new OneRow("Teety So");
        data.add(row4);


    }

    public List<OneRow> getData() {
        return data;


/*
        String[] names = {"Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So","Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So"};
        String[] company = {"Gate Education", "Unemployed", "CCP", "ISB","Gate Education", "Unemployed", "CCP", "ISB"};
        String[] phone_nums = {"15488965321","45789632594","15699447584","15811556497","15488965321","45789632594","15699447584","15811556497"};
        String[] emails = {"SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com","SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com"};
        String[] locations = {"Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China"};
        int[] pictures = {R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae};
        */

    }
}

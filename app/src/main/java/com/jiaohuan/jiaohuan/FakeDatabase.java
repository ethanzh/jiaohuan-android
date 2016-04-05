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

        OneRow row1 = new OneRow("Sangwook Park", "Gate Education", "148 1653 2157", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!");
        data.add(row1);

        OneRow row2 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "158 1456 8743", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!");
        data.add(row2);

        OneRow row3 = new OneRow("Tian Ge Liu", "CCP", "158 3264 4562", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector");
        data.add(row3);

        OneRow row4 = new OneRow("Sangwook Park", "Gate Education", "148 1653 2157", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!");
        data.add(row4);

        OneRow row5 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "158 1456 8743", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!");
        data.add(row5);

        OneRow row6 = new OneRow("Tian Ge Liu", "CCP", "158 3264 4562", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector");
        data.add(row6);

        OneRow row7 = new OneRow("Sangwook Park", "Gate Education", "148 1653 2157", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!");
        data.add(row7);

        OneRow row8 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "158 1456 8743", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!");
        data.add(row8);

        OneRow row9 = new OneRow("Tian Ge Liu", "CCP", "158 3264 4562", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector");
        data.add(row9);

        OneRow row10 = new OneRow("Sangwook Park", "Gate Education", "148 1653 2157", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!");
        data.add(row10);

        OneRow row11 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "158 1456 8743", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!");
        data.add(row11);

        OneRow row12 = new OneRow("Tian Ge Liu", "CCP", "158 3264 4562", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector");
        data.add(row12);

        OneRow row13 = new OneRow("Sangwook Park", "Gate Education", "148 1653 2157", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!");
        data.add(row13);

        OneRow row14 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "158 1456 8743", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!");
        data.add(row14);

        OneRow row15 = new OneRow("Tian Ge Liu", "CCP", "158 3264 4562", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector");
        data.add(row15);


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

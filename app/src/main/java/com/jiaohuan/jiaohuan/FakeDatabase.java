package com.jiaohuan.jiaohuan;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<OneRow> data;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase() {

        // Create a new ArrayList, then add each row to it individually
        data = new ArrayList<>();

        OneRow row1 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row1);

        OneRow row2 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row2);

        OneRow row3 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CFO", "www.baidu.com");
        data.add(row3);

        OneRow row4 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row4);

        OneRow row5 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CTO", "www.baidu.com");
        data.add(row5);

        OneRow row6 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CFO", "www.baidu.com");
        data.add(row6);

        OneRow row7 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row7);

        OneRow row8 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CTO", "www.baidu.com");
        data.add(row8);

        OneRow row9 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CFO", "www.baidu.com");
        data.add(row9);

        OneRow row10 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row10);

        OneRow row11 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CTO", "www.baidu.com");
        data.add(row11);

        OneRow row12 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CFO", "www.baidu.com");
        data.add(row12);

        OneRow row13 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row13);

        OneRow row14 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CTO", "www.baidu.com");
        data.add(row14);

        OneRow row15 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CFO", "www.baidu.com");
        data.add(row15);

        OneRow row16 = new OneRow("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.wook, "北京望京", "Is a valuble member of STUCO!", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row16);

        OneRow row17 = new OneRow("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.nyu, "CTO", "www.baidu.com");
        data.add(row17);

        OneRow row18 = new OneRow("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.tg, "Capital Paradise", "Is a watch collector", R.drawable.nyu, "CEO", "www.baidu.com");
        data.add(row18);


    }

    // Simply returns the data
    public List<OneRow> getData() {
        return data;
    }
}

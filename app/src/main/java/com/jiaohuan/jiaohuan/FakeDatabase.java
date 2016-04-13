package com.jiaohuan.jiaohuan;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<Contact> data;
    private Contact myData;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase() {

        // My own data
        myData = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        // Create a new ArrayList, then add each row to it individually
        data = new ArrayList<>();

        Contact row1 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -256, R.drawable.card_flip );;

        Contact row2 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row3 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -1, R.drawable.card_flip);

        Contact row4 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -16711681 , R.drawable.card_flip);

        Contact row5 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -65536, R.drawable.card_flip);

        Contact row6 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -1, R.drawable.card_flip);

        Contact row7 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip );

        Contact row8 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -65536, R.drawable.card_flip);

        Contact row9 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -1, R.drawable.card_flip);

        Contact row10 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row11 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -65536, R.drawable.card_flip);

        Contact row12 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -1, R.drawable.card_flip);

        Contact row13 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row14 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -65536, R.drawable.card_flip);

        Contact row15 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -1, R.drawable.card_flip);

        Contact row16 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row17 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -65536, R.drawable.card_flip);

        Contact row18 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip );

        // Add the data
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);
        data.add(row8);
        data.add(row9);
        data.add(row10);
        data.add(row11);
        data.add(row12);
        data.add(row13);
        data.add(row14);
        data.add(row15);
        data.add(row16);
        data.add(row17);
        data.add(row18);
        Log.wtf("Data", "" + getAllNames(data));

        // Alphabetize
        Log.wtf("SortedData", "" );
    }

    // Returns all names
    public ArrayList<String> getAllNames(List<Contact> allData){
        ArrayList<String> nameList = new ArrayList<String>();

        for(int i = 0; i < (allData.size() - 1); i++){
            nameList.add(allData.get(i).getName());
        }

        return nameList;
    }

    // Simply returns other people data
    public List<Contact> getData() {
        return data;
    }

    // Return my data
    public Contact getMyData() {return myData;}
}

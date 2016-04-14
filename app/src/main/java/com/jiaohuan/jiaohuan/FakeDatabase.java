package com.jiaohuan.jiaohuan;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<Contact> unsortedData;
    private Contact myData;
    private  List<Contact> fullySortedData;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase() {

        // My own data
        myData = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "ethan.houston@yahoo.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        // Create a new ArrayList, then add each row to it individually
        unsortedData = new ArrayList<>();

        Contact row1 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip );;

        Contact row2 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row3 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row4 = new Contact("Xia Nong", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row5 = new Contact("Larry David", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row6 = new Contact("Donald Duck", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row7 = new Contact("Joseph Kwok", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip );

        Contact row8 = new Contact("Weston Liu", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row9 = new Contact("Gilbert Choy", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row10 = new Contact("Yong Ik", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row11 = new Contact("Wang Peng", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row12 = new Contact("Guo Ao Choi", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row13 = new Contact("Tae Hyung Kim", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row14 = new Contact("Austin Kim", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row15 = new Contact("Mohammed Lee", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row16 = new Contact("Dominus", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip);

        Contact row17 = new Contact("Osuofia", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip);

        Contact row18 = new Contact("Dave Major", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip );

        // Add the data
        unsortedData.add(row1);
        unsortedData.add(row2);
        unsortedData.add(row3);
        unsortedData.add(row4);
        unsortedData.add(row5);
        unsortedData.add(row6);
        unsortedData.add(row7);
        unsortedData.add(row8);
        unsortedData.add(row9);
        unsortedData.add(row10);
        unsortedData.add(row11);
        unsortedData.add(row12);
        unsortedData.add(row13);
        unsortedData.add(row14);
        unsortedData.add(row15);
        unsortedData.add(row16);
        unsortedData.add(row17);
        unsortedData.add(row18);


        fullySortedData = new ArrayList<>();

        fullySortedData = sorter(unsortedData);

    }

    public List<Contact> sorter(List<Contact> unsortedData){
        List<Contact> sortedData = new ArrayList<>();

        ComparatorUser comparator = new ComparatorUser();
        Collections.sort(unsortedData, comparator);

        for (int i = 0; i < unsortedData.size(); i++) {
            Contact user_temp = unsortedData.get(i);
            //Log.wtf("TEST", "" + user_temp.getName());
            sortedData.add(user_temp);
        }
        return sortedData;
    }

    // Simply returns other people data
    public List<Contact> getData() {
        return fullySortedData;
    }

    // Return my data
    public Contact getMyData() {return myData;}
}


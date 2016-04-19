package com.jiaohuan.jiaohuan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<Contact> unsortedData;
    private Contact myData;
    private  List<Contact> fullyAlphaData;
    private List<Contact> fullyUnixData;
    private List<Contact> reverseAlpha;
    private List<Contact> reverseUnix;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase()  { // Maybe throw exception here?

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());

        // My own data
        myData = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "ethan.houston@yahoo.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "", R.drawable.card_nyu, "CTO",
                "jiaohuan.com.cn", -12303292 , R.drawable.card_flip, 1366038215, null);

        // Create a new ArrayList, then add each row to it individually
        unsortedData = new ArrayList<>();

        Contact row1 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1366211015, null);

        Contact row2 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292 , R.drawable.card_flip, 1260732717, null);

        Contact row3 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1260342717, null);

        Contact row4 = new Contact("Xia Nong", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292 , R.drawable.card_flip, 1260346717, null);

        Contact row5 = new Contact("Larry David", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1220346717, null);

        Contact row6 = new Contact("Donald Duck", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1120346717, null);

        Contact row7 = new Contact("Joseph Kwok", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1120526717, null);

        Contact row8 = new Contact("Weston Liu", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1120546727, null);

        Contact row9 = new Contact("Gilbert Choy", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1120543727, null);

        Contact row10 = new Contact("Yong Ik", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292 , R.drawable.card_flip, 1122123727, null);

        Contact row11 = new Contact("Wang Peng", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1122123437, null);

        Contact row12 = new Contact("Guo Ao Choi", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1121723437, null);

        Contact row13 = new Contact("Tae Hyung Kim", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292 , R.drawable.card_flip, 1472723437, null);

        Contact row14 = new Contact("Austin Kim", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 1172723137, null);

        Contact row15 = new Contact("Mohammed Lee", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 157274337, null);

        Contact row16 = new Contact("Dominus", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292 , R.drawable.card_flip, 157234337, null);

        Contact row17 = new Contact("Osuofia", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 137234335, null);

        Contact row18 = new Contact("Dave Major", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CEO",
                "www.baidu.com", -12303292, R.drawable.card_flip, 198534335, null);

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

        fullyAlphaData = new ArrayList<>();
        fullyAlphaData = SortByNames(unsortedData);

        reverseAlpha = new ArrayList<>();
        reverseAlpha = SortByNames(unsortedData);

        convertFromUnix(fullyAlphaData);

        fullyUnixData = new ArrayList<>();
        fullyUnixData = SortByUnix(fullyAlphaData);

        reverseUnix = new ArrayList<>();
        reverseUnix = SortByUnix(fullyAlphaData);
    }

    public void convertFromUnix(List<Contact> list){
        for(int i = 0; i < list.size(); i++){
            long unixSeconds = list.get(i).getUnix_time();
            Date date = new Date(unixSeconds * 1000L);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

            String formattedDate = sdf.format(date);
            list.get(i).setSimpleDate(formattedDate);
        }
    }

    public List<Contact> SortByUnix(List<Contact> unsortedData){
        List<Contact> sortedData = new ArrayList<>();

        for (int i = 0; i < unsortedData.size(); i++) {
            Contact user_temp = unsortedData.get(i);
            sortedData.add(user_temp);
        }

        SortUnixTime comparator = new SortUnixTime();
        Collections.sort(sortedData, comparator);


        return sortedData;
    }

    public List<Contact> SortByNames(List<Contact> unsortedData){
        List<Contact> sortedData = new ArrayList<>();

        SortAlphaNames comparator = new SortAlphaNames();
        Collections.sort(unsortedData, comparator);

        for (int i = 0; i < unsortedData.size(); i++) {
            Contact user_temp = unsortedData.get(i);
            sortedData.add(user_temp);
        }
        return sortedData;
    }

    // Methods to return various ArrayList<Contact>'s
    public List<Contact> getAlphaSorted() {
        return fullyAlphaData;
    }

    public List<Contact> getDateSorted() {
        return fullyUnixData;
    }

    public List<Contact> getReverseAlpha() {
        Collections.reverse(reverseAlpha);
        return reverseAlpha;
    }

    public List<Contact> getReverseUnix() {
        Collections.reverse(reverseUnix);
        return reverseUnix;
    }

    // Return my data
    public Contact getMyData() {return myData;}
}


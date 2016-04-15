package com.jiaohuan.jiaohuan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FakeDatabase {
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<Contact> unsortedData;
    private Contact myData;
    private  List<Contact> fullyAlphaData;
    private  List<Contact> fullyDateData;
    private Date formattedDate;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase()  { // Maybe throw exception here?

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());

        // My own data
        myData = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "ethan.houston@yahoo.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/2006", null);

        // Create a new ArrayList, then add each row to it individually
        unsortedData = new ArrayList<>();

        Contact row1 = new Contact("Sangwook Park", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2008", null );

        Contact row2 = new Contact("Ethan Houston", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/1994", null);

        Contact row3 = new Contact("Tian Ge Liu", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/1940", null);

        Contact row4 = new Contact("Xia Nong", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/2014", null);

        Contact row5 = new Contact("Larry David", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2015", null);

        Contact row6 = new Contact("Donald Duck", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2001", null);

        Contact row7 = new Contact("Joseph Kwok", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2002", null );

        Contact row8 = new Contact("Weston Liu", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2010", null);

        Contact row9 = new Contact("Gilbert Choy", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2011", null);

        Contact row10 = new Contact("Yong Ik", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/1994", null);

        Contact row11 = new Contact("Wang Peng", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2006", null);

        Contact row12 = new Contact("Guo Ao Choi", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2004", null);

        Contact row13 = new Contact("Tae Hyung Kim", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/2005", null);

        Contact row14 = new Contact("Austin Kim", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2016", null);

        Contact row15 = new Contact("Mohammed Lee", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CFO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/2017", null);

        Contact row16 = new Contact("Dominus", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China", R.drawable.pp_wookie, "北京望京", "Is a valuble member of STUCO!", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292 , R.drawable.card_flip, "12/31/1955", null);

        Contact row17 = new Contact("Osuofia", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China", R.drawable.pp_ethan, "龙湖滟澜山", "Enjoys lifting weights!", R.drawable.card_nyu, "CTO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/1945", null);

        Contact row18 = new Contact("Dave Major", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China", R.drawable.pp_tiange, "Capital Paradise", "Is a watch collector", R.drawable.card_nyu, "CEO", "www.baidu.com", -12303292, R.drawable.card_flip, "12/31/1042", null );

        row1.setName("Kim Jong Un");

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

        // Loop through unsorted data, and change date from String to Date
        for(int i = 0; i < unsortedData.size(); i++){

            String tempdate = unsortedData.get(i).getDate();
            unsortedData.get(i).setDate(parsedDate(tempdate));
        }

        fullyAlphaData = new ArrayList<>();
        fullyAlphaData = SortByNames(unsortedData);

        fullyDateData = new ArrayList<>();
        fullyDateData = SortByDate(fullyAlphaData);
    }

    public Date parsedDate(String unformatted){

        String pattern = "MM/dd/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            formattedDate = format.parse(unformatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
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

    public List<Contact> SortByDate(List<Contact> fullyAlphaData){
        List<Contact> fullyDateData = new ArrayList<>();

        SortDateNames comparator = new SortDateNames();
        Collections.sort(fullyAlphaData, comparator);

        for (int i = 0; i < fullyAlphaData.size(); i++) {
            Contact user_temp = fullyAlphaData.get(i);
            fullyDateData.add(user_temp);
        }
        for(int i = 0; i < fullyDateData.size(); i++){
            //Log.wtf("Date", "" + fullyDateData.get(i).getFormattedDate());
        }
        return fullyDateData;
    }

    // Simply returns other people data
    public List<Contact> getData() {
        return fullyDateData;
    }

    // Return my data
    public Contact getMyData() {return myData;}
}


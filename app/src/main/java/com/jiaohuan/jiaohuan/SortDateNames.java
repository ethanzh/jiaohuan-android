package com.jiaohuan.jiaohuan;


import java.util.Comparator;

public class SortDateNames implements Comparator {

    public int compare(Object arg0, Object arg1) {
        Contact user0 = (Contact) arg0;
        Contact user1 = (Contact) arg1;

        int flag = user0.getFormattedDate().compareTo(user1.getFormattedDate());
        //Log.wtf("Comparing", user0.getFormattedDate() + " : " + user1.getFormattedDate() + "  -  " + flag);
        if (flag == 0) {
            return user0.getFormattedDate().compareTo(user1.getFormattedDate());
        } else {
            return flag;
        }
    }
}
package com.jiaohuan.jiaohuan;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorUser implements Comparator {

    public int compare(Object arg0, Object arg1) {
        Contact user0 = (Contact) arg0;
        Contact user1 = (Contact) arg1;

        int flag = user0.getName().compareTo(user1.getName());
        if (flag == 0) {
            return user0.getName().compareTo(user1.getName());
        } else {
            return flag;
        }
    }
}

class SortTest {

    public void Run() {
        List userlist = new ArrayList();
        userlist.add(new Contact("Dave"));
        userlist.add(new Contact("Bart"));
        userlist.add(new Contact("Adam"));
        userlist.add(new Contact("Zack"));
        userlist.add(new Contact("Greg"));

        ComparatorUser comparator = new ComparatorUser();
        Collections.sort(userlist, comparator);

        for (int i = 0; i < userlist.size(); i++) {
            Contact user_temp = (Contact) userlist.get(i);
            Log.wtf("TEST", "" + user_temp.getName());
        }

    }
}
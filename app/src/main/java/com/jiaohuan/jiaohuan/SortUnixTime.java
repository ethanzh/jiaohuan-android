package com.jiaohuan.jiaohuan;

import java.util.Comparator;

public class SortUnixTime implements Comparator {

    public int compare(Object arg0, Object arg1) {
        Contact user0 = (Contact) arg0;
        Contact user1 = (Contact) arg1;

        return Long.compare(user0.getUnix_time(), user1.getUnix_time());
    }
}
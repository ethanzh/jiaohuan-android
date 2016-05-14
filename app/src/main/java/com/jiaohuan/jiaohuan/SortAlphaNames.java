package com.jiaohuan.jiaohuan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortAlphaNames implements Comparator {

    public int compare(Object arg0, Object arg1) {
        Contact user0 = (Contact) arg0;
        Contact user1 = (Contact) arg1;

        int flag = user0.getPinyin().compareTo(user1.getPinyin());
        if (flag == 0) {
            return user0.getPinyin().compareTo(user1.getPinyin());
        } else {
            return flag;
        }
    }
}


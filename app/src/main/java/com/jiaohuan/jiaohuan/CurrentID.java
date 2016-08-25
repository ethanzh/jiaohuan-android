package com.jiaohuan.jiaohuan;

import com.jiaohuan.jiaohuan.jsonData.User;

public class CurrentID {
    public static int id;

    public static int getCurrent(){
        return id;
    }

    public static void setCurrent(int c){
        id = c;
    }
}

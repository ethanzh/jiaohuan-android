package com.jiaohuan.jiaohuan;

public class CurrentToken {
    public static String token;

    public static String getCurrent(){
        return token;
    }

    public static void setCurrent(String c){
        token = c;
    }
}

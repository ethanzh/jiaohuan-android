package com.jiaohuan.jiaohuan;

public class SelectedRow {
    public static Contact current;

    public static Contact getCurrent(){
        return current;
    }

    public static void setCurrent(Contact c){
        current = c;
    }
}

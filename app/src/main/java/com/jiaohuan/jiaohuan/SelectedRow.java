package com.jiaohuan.jiaohuan;

import com.jiaohuan.jiaohuan.jsonData.FriendsListJSON;
import com.jiaohuan.jiaohuan.jsonData.User;

public class SelectedRow {
    public static FriendsListJSON current;

    public static FriendsListJSON getCurrent(){
        return current;
    }

    public static void setCurrent(FriendsListJSON c){
        current = c;
    }
}

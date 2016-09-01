package com.jiaohuan.jiaohuan;

import com.jiaohuan.jiaohuan.jsonData.FriendsListJSON;
import com.jiaohuan.jiaohuan.jsonData.User;

import java.util.ArrayList;

public class UserList {
   public static ArrayList<FriendsListJSON> listOfUsers;

    public static ArrayList<FriendsListJSON> getCurrent(){
        return listOfUsers;
    }

    public static void setCurrent(ArrayList<FriendsListJSON> c){
        listOfUsers = c;
    }
}

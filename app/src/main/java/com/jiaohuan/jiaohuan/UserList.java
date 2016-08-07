package com.jiaohuan.jiaohuan;

import com.jiaohuan.jiaohuan.jsonData.User;

import java.util.ArrayList;

public class UserList {
   public static ArrayList<User> listOfUsers;

    public static ArrayList<User> getCurrent(){
        return listOfUsers;
    }

    public static void setCurrent(ArrayList<User> c){
        listOfUsers = c;
    }
}

package com.jiaohuan.jiaohuan;

import com.jiaohuan.jiaohuan.jsonData.User;

public class CurrentUserObject {
    public static User current;

    public static User getCurrent(){
        return current;
    }

    public static void setCurrent(User c){
        current = c;
    }
}

package com.jiaohuan.jiaohuan;

import android.util.Log;

import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentUser {

    public static User create = new User();

    private static User ourInstance = create;

    public static User getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
        String authentication = "Token " + CurrentToken.getCurrent();
        UserAPI.Factory.getInstance().getPrimaryKey(authentication).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String username = response.body().getUsername();
                String email = response.body().getEmail();

                create.setUsername(username);
                create.setEmail(email);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());
            }
        });
    }

    public static User getMyData() {return create;}
}

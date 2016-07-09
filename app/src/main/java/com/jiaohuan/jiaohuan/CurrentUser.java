package com.jiaohuan.jiaohuan;

import android.util.Log;

import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentUser {
    private static User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
    }


    public User getUserDataFromServer(){
        String authentication = "Token " + CurrentToken.getCurrent();

        final User cu = new User();

        UserAPI.Factory.getInstance().getPrimaryKey(authentication).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String username = response.body().getUsername();
                String email = response.body().getEmail();

                cu.setUsername(username);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());
            }
        });
        return cu;
    }

}

package com.jiaohuan.jiaohuan;

import android.util.Log;

import com.jiaohuan.jiaohuan.jsonData.GetTokenJSON;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitLogin {

    String mUsername;
    String mPassword;

    public RetrofitLogin(String email, String password) {

        mUsername = email;
        mPassword = password;

    }

    void logInTask(String username, String password, final LoginCallback callback){

        UserAPI.Factory.getInstance().authenticateUser(username, password).enqueue(new Callback<GetTokenJSON>() {
            @Override
            public void onResponse(Call<GetTokenJSON> call, Response<GetTokenJSON> response) {

                try{
                    String token = response.body().getToken();
                    Log.wtf("TOKEN",""+ token);

                    callback.onLoginSuccess();

                }catch (NullPointerException t){

                    callback.onLoginFailure();

                    t.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetTokenJSON> call, Throwable t) {

                callback.onLoginFailure();
            }
        });
    }
}
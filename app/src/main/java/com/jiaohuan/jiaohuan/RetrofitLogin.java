package com.jiaohuan.jiaohuan;

import android.util.Log;

import com.jiaohuan.jiaohuan.jsonData.GetTokenJSON;
import com.jiaohuan.jiaohuan.jsonData.User;
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

                    CurrentToken.setCurrent(token);

                    User mUser = getUserFromServer();

                    CurrentUserObject.setCurrent(mUser);

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

    User cu = new User();

    public User getUserFromServer(){

        String authentication = "Token " + CurrentToken.getCurrent();

        UserAPI.Factory.getInstance().getPrimaryKey(authentication).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String username = response.body().getUsername();
                String email = response.body().getEmail();
                String first_name = response.body().getFirstName();
                String last_name = response.body().getLastName();
                boolean is_staff = response.body().getIsStaff();
                String date_joined = response.body().getDateJoined();
                Integer id = response.body().getId();
                String location = response.body().getLocation();

                cu.setUsername(username);
                cu.setEmail(email);
                cu.setFirstName(first_name);
                cu.setLastName(last_name);
                cu.setIsStaff(is_staff);
                cu.setDateJoined(date_joined);
                cu.setId(id);
                cu.setLocation(location);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());
            }
        });

        return cu;
    }
}
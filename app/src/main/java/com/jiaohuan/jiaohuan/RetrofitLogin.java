package com.jiaohuan.jiaohuan;

import android.util.Log;

import com.jiaohuan.jiaohuan.jsonData.FriendsListJSON;
import com.jiaohuan.jiaohuan.jsonData.GetTokenJSON;
import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import java.util.ArrayList;

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

                    getFriendsList();

                    callback.onLoginSuccess();

                }catch (NullPointerException t){

                    callback.onLoginFailure();

                    t.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetTokenJSON> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());

                callback.onLoginFailure();
            }
        });
    }


    public void getFriendsList(){
        UserAPI.Factory.getInstance().friendList(7).enqueue(new Callback<ArrayList<FriendsListJSON>>() {
            @Override
            public void onResponse(Call<ArrayList<FriendsListJSON>> call, Response<ArrayList<FriendsListJSON>> response) {

                int numberOfUsers = response.body().size();

                String logger = "";

                ArrayList<User> listOfUsers = new ArrayList<>();

                for(int i = 0; i < numberOfUsers; i++){
                    logger += response.body().get(i).getFields().getUsername() + "\n";

                    User friendCard = new User();
                    friendCard.setCompany(response.body().get(i).getFields().getCompany());
                    friendCard.setFirstName(response.body().get(i).getFields().getFirstName());
                    friendCard.setLastName(response.body().get(i).getFields().getLastName());
                    friendCard.setEmail(response.body().get(i).getFields().getEmail());
                    friendCard.setLocation(response.body().get(i).getFields().getLocation());
                    friendCard.setPhoneNumber(response.body().get(i).getFields().getPhoneNumber());
                    friendCard.setUsername(response.body().get(i).getFields().getUsername());
                    friendCard.setId(response.body().get(i).getPk());

                    listOfUsers.add(friendCard);
                }
                UserList.setCurrent(listOfUsers);

            }

            @Override
            public void onFailure(Call<ArrayList<FriendsListJSON>> call, Throwable t) {
                Log.wtf("FAILED", t.getMessage());
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
                String phone_number = response.body().getPhoneNumber();
                String company = response.body().getCompany();

                cu.setUsername(username);
                cu.setEmail(email);
                cu.setFirstName(first_name);
                cu.setLastName(last_name);
                cu.setIsStaff(is_staff);
                cu.setCompany(company);
                cu.setDateJoined(date_joined);
                cu.setId(id);
                cu.setLocation(location);
                cu.setPhoneNumber(phone_number);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("FAIL",""+t.getMessage());
            }
        });

        return cu;
    }
}
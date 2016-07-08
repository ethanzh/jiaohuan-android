package com.jiaohuan.jiaohuan.jsonData;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {

    String BASE_URL = "http://www.nutshoo.com";

    @Headers("Authorization: Token " + "3cbe3746e3bd0d63dd0db4ca81dce844d586f43c")
    @GET("/json.json")
    Call<ArrayList<User>> getUsers();

    @FormUrlEncoded
    @POST("/api-token-auth/")
    Call<TokenJSON> authenticateUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/register/")
    Call<User> createUser(@Field("username") String username, @Field("password") String password, @Field("first_name") String first_name);


    class Factory {

        private static UserAPI service;

        public static UserAPI getInstance(){

            if(service == null){

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                service = retrofit.create(UserAPI.class);
                return service;
            }

            else{
                return service;
            }

        }

    }

}

package com.jiaohuan.jiaohuan.jsonData;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {

    String BASE_URL = "http://www.nutshoo.com";

    @Headers("Authorization: Token " + "3cbe3746e3bd0d63dd0db4ca81dce844d586f43c")
    @GET("/json.json")
    Call<ArrayList<GeneratedJSON>> getUsers();

    @Headers({
            "username: testuser",
            "password: Ethan3824"
    })
    @POST("/api-token-auth")
    Call<TokenJSON> getToken();


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

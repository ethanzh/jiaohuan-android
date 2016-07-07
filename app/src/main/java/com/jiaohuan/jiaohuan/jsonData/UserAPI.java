package com.jiaohuan.jiaohuan.jsonData;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface UserAPI {

    String BASE_URL = "http://www.nutshoo.com";


    @GET("/json.json")
    Call<ArrayList<GeneratedJSON>> getUsers();




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

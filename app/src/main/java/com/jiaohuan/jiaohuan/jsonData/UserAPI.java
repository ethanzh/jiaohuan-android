package com.jiaohuan.jiaohuan.jsonData;


import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {

    String BASE_URL = "http://www.nutshoo.com";

    String auth_token = "3cbe3746e3bd0d63dd0db4ca81dce844d586f43c";

    @Headers("Authorization: Token " + auth_token)
    @GET("/json.json")
    Call<ArrayList<User>> getUsers();

    @FormUrlEncoded
    @POST("/api-token-auth/")
    Call<GetTokenJSON> authenticateUser(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("/mobile_register/")
    Call<MobileAuthUserJSON> createUser(@Field("username") String username, @Field("password") String password);

    //@Headers("Authorization: Token " + auth_token)
    @GET("/current_user/")
    Call<User> getPrimaryKey(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/update_email/")
    Call<Void> updateEmail(@Field("email") String email, @Field("id_number") Integer id);

    @FormUrlEncoded
    @POST("/update_location/")
    Call<Void> updateLocation(@Field("location") String location, @Field("id_number") Integer id);



    class Factory {

        private static UserAPI service;

        public static UserAPI getInstance(){

            if(service == null){

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .client(client)
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

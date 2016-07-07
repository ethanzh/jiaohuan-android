package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.jiaohuan.jiaohuan.jsonData.GeneratedJSON;
import com.jiaohuan.jiaohuan.jsonData.TokenJSON;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends android.support.v4.app.Fragment {

    // GitHub does work!

    private Button mGET;
    private TextView mTextView;
    final String url = "http://nutshoo.com/json/";
    private Button mPOST;
    RequestQueue mRequestQueue;
    private StringRequest request;
    public static final String ENDPOINT = "http://www.nutshoo.com";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mGET = (Button) view.findViewById(R.id.network);
        mPOST = (Button) view.findViewById(R.id.post);

        mGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserAPI.Factory.getInstance().getUsers().enqueue(new Callback<ArrayList<GeneratedJSON>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GeneratedJSON>> call, Response<ArrayList<GeneratedJSON>> response) {

                        try{
                            Log.wtf("LENGTH", "" + response.body().size());

                            int arrayLength = response.body().size();

                            for(int i = 0; i < arrayLength; i++){

                                String printme = "Username: ";

                                printme += response.body().get(i).getUsername();

                                printme += "\nEmail: ";

                                printme += response.body().get(i).getEmail() + "\n";

                                Log.wtf("JSON", printme);
                            }
                        }catch (NullPointerException t){
                            t.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GeneratedJSON>> call, Throwable t) {
                        Log.wtf("FAIL",""+t.getMessage());
                    }

                });

            }
        });


        mPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserAPI.Factory.getInstance().getToken().enqueue(new Callback<TokenJSON>() {
                    @Override
                    public void onResponse(Call<TokenJSON> call, Response<TokenJSON> response) {

                        try {
                            Log.wtf("TOKEN", "" + response.body().getToken());
                        }
                        catch (NullPointerException t){
                            t.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenJSON> call, Throwable t) {
                        Log.wtf("FAIL",""+t.getMessage());
                    }
                });

            }
        });

        return view;
    }

}

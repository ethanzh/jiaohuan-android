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
import com.android.volley.toolbox.Volley;
import com.jiaohuan.jiaohuan.jsonData.JsonObject;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

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

        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());



        mGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserAPI.Factory.getInstance().getUsers().enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        Log.wtf("Response", "" + response.body());

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.wtf("FAIL", "This failed...");

                    }
                });

            }
        });


        mPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return view;
    }

}

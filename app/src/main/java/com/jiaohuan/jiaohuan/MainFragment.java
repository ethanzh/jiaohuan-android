package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends android.support.v4.app.Fragment {

    // GitHub does work!

    private Button mGET;
    private TextView mTextView;
    private Button mPOST;
    private String authorization = "Token ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mGET = (Button) view.findViewById(R.id.network);
        mPOST = (Button) view.findViewById(R.id.post);
        mTextView = (TextView) view.findViewById(R.id.welcome);

        //3cbe3746e3bd0d63dd0db4ca81dce844d586f43c
        authorization += "5a99b2e1f4a9be61029ee2b37120ce544a872608";

        UserAPI.Factory.getInstance().getPrimaryKey(authorization).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.wtf("AUTH", authorization);
                String name = response.body().getUsername();

                String welcome = "Welcome, " + name;

                mTextView.setText(welcome);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.wtf("AUTH", authorization);
                Log.wtf("FAIL",""+t.getMessage());
            }
        });







        mGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




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

package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends android.support.v4.app.Fragment {

    private TextView mTextView;

    private Button mEmail;
    private Button mPhone;
    private Button mLocation;
    private Button mCompany;
    private Button mPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mEmail = (Button) view.findViewById(R.id.email);
        mPhone = (Button) view.findViewById(R.id.phone);
        mLocation = (Button) view.findViewById(R.id.location);
        mCompany = (Button) view.findViewById(R.id.company);

        mPost = (Button) view.findViewById(R.id.post);

        mTextView = (TextView) view.findViewById(R.id.welcome);

        mTextView.setText(CurrentUserObject.getCurrent().getUsername());

        final int account_id = CurrentUserObject.getCurrent().getId();

        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().updateAll("", "", "",
                        "", account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.wtf("WORKED", "This worked");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.wtf("ERROR", t.getMessage());
                    }
                });
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().updateEmail("bobb@brown.net", account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().updatePhone("9001900155", account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().updateLocation("Shanghai", account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        mCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().updateCompany("ACME Enterprises", account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });



        return view;
    }

}

package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiaohuan.jiaohuan.jsonData.FriendsListJSON;
import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends android.support.v4.app.Fragment {

    private TextView mTextView;

    private Button mFriend;
    private Button mList;
    private EditText mFriendNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mFriend = (Button) view.findViewById(R.id.friend);
        mTextView = (TextView) view.findViewById(R.id.welcome);
        mList = (Button) view.findViewById(R.id.list);
        mFriendNo = (EditText) view.findViewById(R.id.friend_no);

        mTextView.setText("Welcome, " + CurrentUserObject.getCurrent().getUsername());


        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String auth = "Token " + "26fc33b3765e65e4ececeb1ff7a45ae735004854";

                UserAPI.Factory.getInstance().testFunction(auth).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.wtf("WORKED", "Worked");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        Log.wtf("FAILED", throwable.getStackTrace() + "");
                    }
                });

            }
        });


        mFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int their_pk = Integer.parseInt(mFriendNo.getText().toString());

                Log.wtf("IDs", CurrentUserObject.getCurrent().getId() + ", " + their_pk);

                UserAPI.Factory.getInstance().addFriend(CurrentUserObject.getCurrent().getId(), their_pk).
                        enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.wtf("SUCCESSFUL", "Request worked");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.wtf("FAILED", t.getMessage());
                    }
                });
            }
        });

        return view;
    }

}

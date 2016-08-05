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

    private Button mFriend;
    private Button mPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mFriend = (Button) view.findViewById(R.id.friend);

        mTextView = (TextView) view.findViewById(R.id.welcome);

        mTextView.setText(CurrentUserObject.getCurrent().getUsername());

        final int account_id = CurrentUserObject.getCurrent().getId();

        mFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.Factory.getInstance().addFriend(4, 5).enqueue(new Callback<Void>() {
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

package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends android.support.v4.app.Fragment {

    private Button mGET;
    private TextView mTextView;
    private Button mPOST;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mGET = (Button) view.findViewById(R.id.network);
        mPOST = (Button) view.findViewById(R.id.post);
        mTextView = (TextView) view.findViewById(R.id.welcome);


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

package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewMyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mLinearLayout;
    private TextView mPopName;
    private TextView mPopCompany;
    private TextView mPopEmail;
    private TextView mPopPhone;
    private TextView mPopAddress;
    private TextView mPopInfo;

    private ImageView mImageView;

    public static String mTransfer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the view
        View view = inflater.inflate(R.layout.new_my_cards, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mAdapter = new RecycleAdapter(getActivity(), FakeDatabase.getInstance().getData());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), R.dimen.padding_four));

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("CLICK", "" + position);

                //Make popup here
                mLayoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.popup, null);

                mPopName = (TextView) mContainer.findViewById(R.id.pop_name);
                mPopCompany = (TextView) mContainer.findViewById(R.id.pop_company);
                mPopEmail = (TextView) mContainer.findViewById(R.id.pop_email);
                mPopPhone = (TextView) mContainer.findViewById(R.id.pop_phone);
                mPopAddress = (TextView) mContainer.findViewById(R.id.pop_address);
                mPopInfo = (TextView) mContainer.findViewById(R.id.pop_info);
                mImageView = (ImageView) mContainer.findViewById(R.id.image);


                OneRow selectedRow = mAdapter.getRow(position);

                mPopName.setText(selectedRow.getName());
                mPopCompany.setText(selectedRow.getCompany());
                mPopEmail.setText(selectedRow.getEmail());
                mPopPhone.setText(selectedRow.getPhoneNum());
                mPopAddress.setText(selectedRow.getAddress());
                mPopInfo.setText(selectedRow.getInfo());

                mImageView.setImageResource(selectedRow.getPic());

                mPopupWindow = new PopupWindow(mContainer,900, 1600, true);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                mContainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        return view;
    }

}

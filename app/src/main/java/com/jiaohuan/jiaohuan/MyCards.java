package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyCards extends android.support.v4.app.Fragment {

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
    private TextView mTitle;
    private TextView mClose;
    private TextView mWebsite;
    private ImageView mImageView;
    private ImageView mCard;
    private int mColor;
    private RelativeLayout mTopPanel;
    private LayoutInflater mFullCard;
    private ImageView mBigCard;
    int initial = 0;
    private TextView mShowName;
    private TextView mShowCompany;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_cards, container, false);

        // Start the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        mAdapter = new RecycleAdapter(getActivity(), FakeDatabase.getInstance().getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), R.dimen.padding_four));

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        // On click listener for each list item
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                // Make card_expand here
                mLayoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.card_expand, null);

                // Assign all of the pop up's TextViews
                mPopName = (TextView) mContainer.findViewById(R.id.pop_name);
                mPopCompany = (TextView) mContainer.findViewById(R.id.pop_company);
                mPopEmail = (TextView) mContainer.findViewById(R.id.pop_email);
                mPopPhone = (TextView) mContainer.findViewById(R.id.pop_phone);
                mPopAddress = (TextView) mContainer.findViewById(R.id.pop_address);
                mPopInfo = (TextView) mContainer.findViewById(R.id.pop_info);
                mImageView = (ImageView) mContainer.findViewById(R.id.image);
                mCard = (ImageView) mContainer.findViewById(R.id.card_pic);
                mClose = (TextView) mContainer.findViewById(R.id.close);
                mTitle = (TextView) mContainer.findViewById(R.id.pop_title);
                mWebsite = (TextView) mContainer.findViewById(R.id.website);
                mShowName = (TextView) mContainer.findViewById(R.id.showname);
                mShowCompany = (TextView) mContainer.findViewById(R.id.showcompany);

                // Get top panel
                mTopPanel = (RelativeLayout) mContainer.findViewById(R.id.topPanel);

                // Gets the data of the clicked card
                final OneRow selectedRow = mAdapter.getRow(position);

                // Get color
                mColor = selectedRow.getColor();

                // Set color
                mTopPanel.setBackgroundColor(mColor);

                Log.wtf("Color", mColor + "");

                // If white background, make top panel text black
                if(mColor == -1){
                    mShowName.setTextColor(Color.BLACK);
                    mShowCompany.setTextColor(Color.BLACK);
                    mPopName.setTextColor(Color.BLACK);
                    mPopCompany.setTextColor(Color.BLACK);
                }

                // Gets text from the (fake) database and prints them to the activity
                mPopName.setText(selectedRow.getName());
                mPopCompany.setText(selectedRow.getCompany());
                mPopEmail.setText(selectedRow.getEmail());
                mPopAddress.setText(selectedRow.getAddress());
                mPopInfo.setText(selectedRow.getInfo());
                mWebsite.setText(selectedRow.getWebsite());
                mPopPhone.setText(selectedRow.getPhoneNum());
                mTitle.setText(selectedRow.getTitle());
                mImageView.setImageResource(selectedRow.getPic());
                mCard.setImageResource(selectedRow.getBusiness_card());

                // Starts the pop up
                mPopupWindow = new PopupWindow(mContainer, 930, 1620, true);
                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);


                // Card picture on click listener
                mCard.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Log.wtf("HI", "IT WORKED");

                        if(initial == 0){
                            mCard.setImageResource(selectedRow.getFlipside());
                            initial = 1;
                        }else{
                            mCard.setImageResource(selectedRow.getBusiness_card());
                            initial = 0;
                        }
                    }
                });

                // Close button click listener
                mClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });
            }
        });

        return view;
    }

}

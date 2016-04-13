package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends android.support.v4.app.Fragment {
    @Nullable

    private ImageView mImageView;
    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mLocation;
    private TextView mEdit;
    private ImageView mCard;
    int initial = 0;
    private Contact myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_profile, container, false);

        mImageView = (ImageView) view.findViewById(R.id.picture);
        mCard = (ImageView) view.findViewById(R.id.pic);

        // Connect the TextViews to their ids
        mName = (TextView) view.findViewById(R.id.name);
        mEmail = (TextView) view.findViewById(R.id.email);
        mPhone = (TextView) view.findViewById(R.id.phone);
        mLocation = (TextView) view.findViewById(R.id.location);
        mEdit = (TextView) view.findViewById(R.id.edit);

        // Get my data from fake database
        myData = (Contact) FakeDatabase.getInstance().getMyData();

        // Set dummy data, eventually to be replaced with data from server
        mImageView.setImageResource(myData.getPic());
        mName.setText(myData.getName());
        mEmail.setText(myData.getEmail());
        mPhone.setText(myData.getPhoneNum());
        mLocation.setText(myData.getLocation());
        mCard.setImageResource(myData.getBusiness_card());

        // Gets phone dimensions
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Makes card size device independent
        double cardHeight;
        cardHeight = height * 0.078;
        int realCardHeight = (int) cardHeight;
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realCardHeight, getResources().getDisplayMetrics());
        mCard.getLayoutParams().height = px;

        // Edit listener
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("HI", "PRESED");
                Intent intent = new Intent(getContext(), EditMyProfile.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        // Set card click listener
        mCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(initial == 0){
                    mCard.setImageResource(myData.getFlipside());
                    initial = 1;
                }else{
                    mCard.setImageResource(myData.getBusiness_card());
                    initial = 0;
                }
            }
        });

        return view;
    }
}

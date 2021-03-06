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
    private TextView mCompany;
    private ImageView mCard;
    int initial = 0;
    private Contact myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_profile, container, false);

        mImageView = (ImageView) view.findViewById(R.id.picture);
        mCard = (ImageView) view.findViewById(R.id.pic);

        // Connect the TextViews to their ids
        mName = (TextView) view.findViewById(R.id.name_tv);
        mEmail = (TextView) view.findViewById(R.id.email_tv);
        mPhone = (TextView) view.findViewById(R.id.phone_tv);
        mLocation = (TextView) view.findViewById(R.id.location_tv);
        mCompany = (TextView) view.findViewById(R.id.company_tv);

        mEdit = (TextView) view.findViewById(R.id.edit);

        setLabelValues();

        // Take +86 off the front of the phone number
        //String shortendPhone = SelectedRow.getCurrent().getPhoneNum();
        //shortendPhone = shortendPhone.substring(3);
        //Log.wtf("SHORT", shortendPhone);


        // Gets phone dimensions
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Makes card size device independent
        double cardHeight;
        cardHeight = height * 0.1;
        int realCardHeight = (int) cardHeight;
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realCardHeight, getResources().getDisplayMetrics());
        mCard.getLayoutParams().height = px;

        // Edit listener
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
                Intent intent = new Intent(getContext(), EditMyProfile.class);
                startActivity(intent);
            }
        });

        // Set card click listener
        mCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (initial == 0) {
//                    mCard.setImageBitmap(myData.getFlipside());
                    initial = 1;
                } else {
//                    mCard.setImageBitmap(myData.getBusiness_card());
                    initial = 0;
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setLabelValues();
        Log.wtf("RESTART", "Activity restarted");


    }


    public void setLabelValues(){
        mName.setText(CurrentUserObject.getCurrent().getUsername());
        mEmail.setText(CurrentUserObject.getCurrent().getEmail());
        mPhone.setText(CurrentUserObject.getCurrent().getPhoneNumber());
        mLocation.setText(CurrentUserObject.getCurrent().getLocation());
        mCompany.setText(CurrentUserObject.getCurrent().getCompany());
    }
}

package com.jiaohuan.jiaohuan;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends android.support.v4.app.Fragment {
    @Nullable

    private ImageView mImageView;
    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_profile, container, false);

        mImageView = (ImageView) view.findViewById(R.id.picture);

        // Connect the TextViews to their ids
        mName = (TextView) view.findViewById(R.id.name);
        mEmail = (TextView) view.findViewById(R.id.email);
        mPhone = (TextView) view.findViewById(R.id.phone);
        mLocation = (TextView) view.findViewById(R.id.location);

        // Set dummy data, eventually to be replaced with data from server
        mName.setText("Xia Nong");
        mEmail.setText("xianong@gmail.com");
        mPhone.setText("156 6511 1548");
        mLocation.setText("Beijing, China");

        return view;
    }
}

package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditMyProfile extends Activity {

    private EditText mNames;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mLocation;
    private Contact myData;
    ImageView mLeft;
    ImageView mCenter;
    ImageView mRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_my_profile);

        // Get my data from fake database
        myData = FakeDatabase.getInstance().getMyData();

        // Connect edit texts
        mNames = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.phone);
        mLocation = (EditText) findViewById(R.id.location);

        // Images
        // These are the bottom buttons
        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);

        mLeft.setImageDrawable(null);
        mCenter.setImageDrawable(null);
        mRight.setImageResource(R.drawable.white_profile);

        // Set the text
        mNames.setText(myData.getName());
        mEmail.setText(myData.getEmail());
        mPhone.setText(myData.getPhoneNum());
        mLocation.setText(myData.getLocation());


    }

    public void back_to_profile(View view) {
        fromEditToProfile(view);
    }

    public void fromEditToCards(View view) {
        finish();
    }

    public void fromEditToMain(View view) {
        finish();

    }

    public void fromEditToProfile(View view) {
        myData.setName(mNames.getText().toString());
        myData.setEmail(mEmail.getText().toString());
        myData.setPhoneNum(mPhone.getText().toString());
        myData.setLocation(mLocation.getText().toString());
        finish();
    }
}

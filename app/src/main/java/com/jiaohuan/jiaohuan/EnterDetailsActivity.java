package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterDetailsActivity extends Activity {

    private TextView mNext;

    private EditText mName;
    private EditText mPassword;
    private EditText mPhone;
    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        mNext = (TextView) findViewById(R.id.next);

        mName = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        mBack = (TextView)findViewById(R.id.back);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mName.getText().toString();
                String password = mPassword.getText().toString();
                String phone = mPhone.getText().toString();

                if (email.matches("") || password.matches("") || phone.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    mName.setHintTextColor(Color.RED);
                    mPassword.setHintTextColor(Color.RED);
                    mPhone.setHintTextColor(Color.RED);
                    return;
                }else{
                    /*

                    Send email, password, and phone to server

                     */
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}

package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends Activity {

    private TextView mNext;

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPhone;

    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acccount);

        mNext = (TextView) findViewById(R.id.next);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        mBack = (TextView)findViewById(R.id.back);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String phone = mPhone.getText().toString();

                if (email.matches("") || password.matches("") || phone.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    mEmail.setHintTextColor(Color.RED);
                    mPassword.setHintTextColor(Color.RED);
                    mPhone.setHintTextColor(Color.RED);
                    return;
                }else{
                    // DO STUFF
                    Intent intent = new Intent(getApplicationContext(), CreateAccountCard.class);
                    startActivity(intent);
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

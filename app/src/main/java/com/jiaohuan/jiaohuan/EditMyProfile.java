package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyProfile extends Activity {

    private Contact myData;
    private String initialName;
    private String initialEmail;
    private String initialPhone;
    private String initialLocation;
    private int card_front;
    private int card_back;

    private String finalName;
    private String finalEmail;
    private String finalPhone;
    private String finalLocation;

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mLocation;

    private Button mFinish;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_my_profile);

        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.phone);
        mLocation = (EditText) findViewById(R.id.location);

        mFinish = (Button) findViewById(R.id.finish);
        mBack = (Button) findViewById(R.id.back);

        // Get my data from fake database
        myData = FakeDatabase.getInstance().getMyData();

        // INITIAL VALUES
        initialName = myData.getName();
        initialEmail = myData.getEmail();
        initialPhone = myData.getPhoneNum();
        initialLocation = myData.getLocation();

        mName.setText(initialName);
        mEmail.setText(initialEmail);
        mPhone.setText(initialPhone);
        mLocation.setText(initialLocation);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalName = mName.getText().toString();
                finalEmail = mEmail.getText().toString();
                finalPhone = mPhone.getText().toString();
                finalLocation = mLocation.getText().toString();


                if (!finalName.equals(initialName)){
                    Toast.makeText(EditMyProfile.this, "Name was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setName(finalName);
                }
                if (!finalEmail.equals(initialEmail)){
                    Toast.makeText(EditMyProfile.this, "Email was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setEmail(finalEmail);
                }
                if (!finalPhone.equals(initialPhone)){
                    Toast.makeText(EditMyProfile.this, "Phone was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setPhoneNum(finalPhone);
                }
                if (!finalLocation.equals(initialLocation)){
                    Toast.makeText(EditMyProfile.this, "Location was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setLocation(finalLocation);
                }
            }
        });
    }
}

package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartupPage extends Activity {

    private Button mSignin;
    private Button mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        mSignin = (Button) findViewById(R.id.signin);
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mSignup = (Button) findViewById(R.id.signup);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }
}

package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class StartupPage extends Activity {

    private Button mSignin;
    private Button mSignup;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        mLinearLayout = (LinearLayout) findViewById(R.id.start_page);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mLinearLayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.evenlow) );
        } else {
            mLinearLayout.setBackground( getResources().getDrawable(R.drawable.evenlow));
        }

        // Start sign in process
        mSignin = (Button) findViewById(R.id.signin);
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Start sign up process
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

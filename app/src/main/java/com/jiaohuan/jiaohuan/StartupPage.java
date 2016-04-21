package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class StartupPage extends Activity {

    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        mLinearLayout = (LinearLayout) findViewById(R.id.start_layout);

        //mLinearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.blurred_shanghai_startpage));

        mLinearLayout.setBackgroundColor(Color.parseColor("#0e0019"));

        // Start sign in process
        Button signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Start sign up process
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }
}

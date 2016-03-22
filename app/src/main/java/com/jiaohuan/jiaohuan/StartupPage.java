package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.app.ActivityOptions;
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


                //startActivity(intent);


                //overridePendingTransition(R.drawable.slide_in, R.drawable.slide_out);



                //Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                // ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.drawable.slide_in_up, 0);
                //startActivity(intent2, options.toBundle());


            }
        });

        mSignup = (Button) findViewById(R.id.signup);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(intent, 2);
                //startActivity(intent);
                //overridePendingTransition(R.drawable.slide_in, R.drawable.slide_out);
            }
        });



    }
}

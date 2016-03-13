package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;

public class CreateAccountActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_acccount);
    }
    public void backFunction(){
        /*Intent openMainActivity = new Intent(CreateAccountActivity.this, LoginActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);
        super.finish();*/

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        super.finish();
    }
}

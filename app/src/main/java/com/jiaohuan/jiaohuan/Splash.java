package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        try {
            // Sleeping
            Thread.sleep(5000);
        } catch (Exception e) {
            Log.e("Splash", e.getMessage());
        }

        Intent intent = new Intent(getApplicationContext(), StartupPage.class);
        startActivity(intent);
        finish();
    }
}

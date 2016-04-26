package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void deflateSettings(View view) {
        finish();
    }

    public void onLogOut(View view){

        Intent intent = new Intent(getApplicationContext(), StartPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);

    }
}

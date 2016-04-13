package com.jiaohuan.jiaohuan;

import android.app.Activity;
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
        finish();
    }
}

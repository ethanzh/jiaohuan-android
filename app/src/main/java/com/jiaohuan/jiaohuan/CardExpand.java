package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CardExpand extends Activity{

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_expand);

        mLinearLayout = (LinearLayout) findViewById(R.id.touch);

    }
}

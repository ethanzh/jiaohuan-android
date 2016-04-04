package com.jiaohuan.jiaohuan;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainFragment extends android.support.v4.app.Fragment {
    @Nullable

    private LinearLayout mLinearLayout;
    private ShakeDetector mShaker;
    private LayoutInflater mLayoutInflater;
    private PopupWindow mPopupWindow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.main_linear);
        /*mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), QRCode.class);
                startActivity(myIntent);
            }
        });*/

        /*mShaker = new ShakeDetector(getContext());
        mShaker.setOnShakeListener(new ShakeDetector.OnShakeListener () {
            public void onShake()
            {
                //Make popup here
                mLayoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.shake_popup, null);

                mPopupWindow = new PopupWindow(mContainer,900, 1200, true);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                mContainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        mShaker.resume();
                        return true;
                    }
                });
                mShaker.pause();
            }
        });*/

        return view;
    }
}

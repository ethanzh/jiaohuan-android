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

    // GitHub does work!

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        SwipeDetector swipeDetector = new SwipeDetector();
        view.setOnTouchListener(swipeDetector);

        if (swipeDetector.getAction() == SwipeDetector.Action.LR) {
            Log.wtf("HI", "THIS HAPPENED");
        }

        return view;
    }

}

class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None, // when no action was detected
        Click
    }

    private static final String logTag = "SwipeDetector";
    private static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;
    private Action mSwipeDetected = Action.None;

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                // Log.i(logTag, "Click On List" );
                return false; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // horizontal swipe detection
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {

                        mSwipeDetected = Action.LR;
                        return false;
                    }
                    if (deltaX > 0) {

                        mSwipeDetected = Action.RL;
                        return false;
                    }
                }
            /*
             * else
             *
             * // vertical swipe detection if (Math.abs(deltaY) > MIN_DISTANCE)
             * { // top or down if (deltaY < 0) { Log.i(logTag,
             * "Swipe Top to Bottom"); mSwipeDetected = Action.TB; return false;
             * } if (deltaY > 0) { Log.i(logTag, "Swipe Bottom to Top");
             * mSwipeDetected = Action.BT; return false; } }
             */

                mSwipeDetected = Action.Click;
                return false;
            }
        }
        return false;
    }

}
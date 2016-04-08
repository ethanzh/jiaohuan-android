package com.jiaohuan.jiaohuan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager = null;
    ImageView mLeft;
    ImageView mCenter;
    ImageView mRight;
    TextView mSettings;
    private ShakeDetector mShaker;
    private LayoutInflater mLayoutInflater;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;
    private TextView mGPS;
    private Button mButton;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private TextView mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        // These are the bottom buttons
        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);

        // App starts on the main page, so obviously the arrow will be the 'on' one
        mCenter.setImageResource(R.drawable.white_arrow);
        mLeft.setImageDrawable(null);
        mRight.setImageDrawable(null);

        // Settings is off by default, because app starts on the 'main' screen
        mSettings = (TextView) findViewById(R.id.settings);
        mSettings.setVisibility(View.INVISIBLE);

        // Shake listener
        mShaker = new ShakeDetector(getApplicationContext());
        mShaker.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            public void onShake() {

                // Make card_expand here
                mLayoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.shake_popup, null);

                // Set the location where the pop up occurs
                mPopupWindow = new PopupWindow(mContainer, 1000, 1300, true);
                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                // Connect button and TextView
                mButton = (Button) findViewById(R.id.button);
                mGPS = (TextView) findViewById(R.id.gps);
                mTime = (TextView) findViewById(R.id.time);

                mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                mLocationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        mGPS.append("\n " + location.getLatitude() + " " + location.getLongitude());

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                };

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestLocationUpdates("gps", 5000, 0, mLocationListener);


                // When anywhere is tapped, the pop up dismisses, it also resumes the shaker
                mContainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        mShaker.resume();
                        return true;
                    }
                });

                // Shaker is paused when pop up is displayed
                mShaker.pause();
            }
        });

        // Code the start the view pager
        mViewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new MyAdapter(fragmentManager));
        mViewPager.setCurrentItem(1);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            // Either pause or resume shaker, and assign appropriate bottom icon
            public void onPageSelected(int position) {
                if (position == 1) {
                    mSettings.setVisibility(View.INVISIBLE);
                    mShaker.resume();
                    changeToMain();
                } else if (position == 2) {
                    mSettings.setVisibility(View.VISIBLE);
                    mShaker.pause();
                    changeToProfile();
                } else if (position == 0) {
                    mSettings.setVisibility(View.INVISIBLE);
                    mShaker.pause();
                    changeToCards();
                }
            }
        });
    }

    // Various methods to change the pictures

    public void changeToCards(){
        mLeft.setImageResource(R.drawable.white_card);
        mCenter.setImageDrawable(null);
        mRight.setImageDrawable(null);
    }

    public void changeToMain (){
        mLeft.setImageDrawable(null);
        mCenter.setImageResource(R.drawable.white_arrow);
        mRight.setImageDrawable(null);
    }

    public void changeToProfile (){
        mLeft.setImageDrawable(null);
        mCenter.setImageDrawable(null);
        mRight.setImageResource(R.drawable.white_profile);
    }

    public void jumpToMain(View view) {
        mSettings.setVisibility(View.INVISIBLE);
        mShaker.resume();
        mViewPager.setCurrentItem(1);
    }
    public void jumpToProfile(View view) {
        mShaker.pause();
        mSettings.setVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(2);
    }

    public void jumpToCards(View view) {
        mShaker.pause();
        mSettings.setVisibility(View.INVISIBLE);
        mViewPager.setCurrentItem(0);
    }

    public void inflateSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void jumpToEdit(View view){
        mSettings.setVisibility(View.INVISIBLE);
        mRight.setImageDrawable(null);
    }
}

class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        Fragment fragment = null;
        if(i == 0){
            fragment = new MyCards();
        }
        if(i == 1){
            fragment = new MainFragment();
        }
        if(i == 2){
            fragment = new MyProfile();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
package com.jiaohuan.jiaohuan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    private TextView mTime;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private Button mGPS;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Button mBarButton;
    private TextView mBarText;
    private Barometer blis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new MyLocationListener();

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

                boolean hasBarometer = barometerLister();

                // Make card_expand here
                mLayoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.shake_popup, null);

                // Gets phone dimensions
                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                double popUpWidth;
                double popUpHeight;

                popUpWidth = width * 0.93;
                popUpHeight = height * 0.67;

                int popWidth = (int) popUpWidth;
                int popHeight = (int) popUpHeight;

                // 1000, 400
                mPopupWindow = new PopupWindow(mContainer, popWidth, popHeight, true);

                // Connect button and TextView
                mTime = (TextView) mPopupWindow.getContentView().findViewById(R.id.time);
                mBarText = (TextView) mPopupWindow.getContentView().findViewById(R.id.bar);
                mBarButton = (Button) mPopupWindow.getContentView().findViewById(R.id.barbutton);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                // When anywhere is tapped, the pop up dismisses, it also resumes the shaker
                mContainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        mShaker.resume();
                        mSensorManager.unregisterListener(blis);
                        return true;
                    }
                });

                Log.e("FIRST", "" + Barometer.getInstance().getValue());

                // Gets current time (UNIX time) and displays it
                long time= System.currentTimeMillis();
                Log.d("Time", time + "");
                String stringTime = Long.toString(time);
                mTime.setText(stringTime);

                if (Barometer.getInstance().getValue() == 0){
                    //
                }

                if(hasBarometer){
                    try{
                        mBarText.setText("" + Barometer.getInstance().getValue());
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                    mBarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float barometerValue = Barometer.getInstance().getValue();
                            mBarText.setText("" + barometerValue);
                        }
                    });
                }

                // THIS IS THE VALUE THAT GETS SENT TO SERVER
                // time -> Database
                //

                // Shaker is paused when pop up is displayed
                mShaker.pause();
            }
        });

        mShaker.resume();

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

    private void getGPSWrapper() {
        int hasLocationPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }

        //getGPS();
    }

    public boolean barometerLister(){

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (mSensor == null){
            return false;
        } else {
            blis = Barometer.getInstance();

            mSensorManager.registerListener(blis, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

            return true;
        }
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
/*---------- Listener class to get coordinates ------------- */
class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {

        Log.wtf("CODE", "RUNNING");

        String longitude = "Longitude: " + loc.getLongitude();
        Log.e("HI", longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.e("HI", latitude);

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
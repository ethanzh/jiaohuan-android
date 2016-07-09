package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiaohuan.jiaohuan.jsonData.User;

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager = null;
    ImageView mLeft;
    ImageView mCenter;
    ImageView mRight;
    TextView mSettings;
    private ShakeDetector mShaker;
    private LayoutInflater mLayoutInflater;
    private PopupWindow mPopupWindow;
    private TextView mTime;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Button mBarButton;
    private TextView mBarText;
    private Barometer blis;
    private LruCache<String, Bitmap> mMemoryCache;
    public User cu;

    public void getUser(){
        Log.wtf("CU", cu.getInstance().getUsername());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // The app works without this for now...
        //setBitmapMemory();

        initializeBottomButtons();

        setInitialValues();

        setShakeListener();

        startViewPager();

        viewPagerListener();

        getUser();
    }

    public void startViewPager() {
        // Code the start the view pager
        mViewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new ViewPagerAdapter(fragmentManager));
        mViewPager.setCurrentItem(1);
    }

    public void viewPagerListener() {
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
                } else if (position == 2) {
                    mSettings.setVisibility(View.VISIBLE);
                    mShaker.pause();
                } else if (position == 0) {
                    mSettings.setVisibility(View.INVISIBLE);
                    mShaker.pause();
                }
            }
        });

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

    public void inflateSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public boolean barometerLister() {

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (mSensor == null) {
            return false;
        } else {
            blis = Barometer.getInstance();

            mSensorManager.registerListener(blis, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

            return true;
        }
    }



    public void setBitmapMemory() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/7th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void initializeBottomButtons() {
        // These are the bottom buttons
        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);
    }

    public void setInitialValues() {
        // App starts on the main page, so obviously the arrow will be the 'on' one
        mCenter.setImageResource(R.drawable.highlighted_arrow);
        mLeft.setImageResource(R.drawable.white_card);
        mRight.setImageResource(R.drawable.white_profile);


        // Settings is off by default, because app starts on the 'main' screen
        mSettings = (TextView) findViewById(R.id.settings);
        mSettings.setVisibility(View.INVISIBLE);
    }

    public void setShakeListener() {
        // Shake listener
        mShaker = new ShakeDetector(getApplicationContext());
        mShaker.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            public void onShake() {

                boolean hasBarometer = barometerLister();

                // Make card_expand here
                mLayoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.shake_popup, null);

                getPhoneDimens(mContainer);

                // Connect button and TextView
                mTime = (TextView) mPopupWindow.getContentView().findViewById(R.id.time);
                mBarText = (TextView) mPopupWindow.getContentView().findViewById(R.id.bar);
                mBarButton = (Button) mPopupWindow.getContentView().findViewById(R.id.barbutton);

                //mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

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

                long time = System.currentTimeMillis();
                String stringTime = Long.toString(time);
                mTime.setText(stringTime);

                if (Barometer.getInstance().getValue() == 0) {
                    //
                }

                if (hasBarometer) {
                    try {
                        mBarText.setText("" + Barometer.getInstance().getValue());
                    } catch (NullPointerException e) {
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
    }

    public void getPhoneDimens(ViewGroup mContainer) {
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
    }
}

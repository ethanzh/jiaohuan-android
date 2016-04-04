package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager = null;
    Toolbar mToolbar;
    TextView mChange;
    ImageView mLeft;
    ImageView mCenter;
    ImageView mRight;
    TextView mSettings;
    private ShakeDetector mShaker;
    private LayoutInflater mLayoutInflater;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);

        mSettings = (TextView) findViewById(R.id.settings);
        mSettings.setVisibility(View.INVISIBLE);

        mShaker = new ShakeDetector(getApplicationContext());
        mShaker.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            public void onShake() {
                //Make popup here
                mLayoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.shake_popup, null);

                mPopupWindow = new PopupWindow(mContainer, 900, 1200, true);

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
        });



        mViewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new MyAdapter(fragmentManager));
        mViewPager.setCurrentItem(1);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mChange = (TextView) findViewById(R.id.change_me);



        String[] array = {"One", "Two", "Three", "Four"};

        ListAdapter mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                if(position == 1){
                    mSettings.setVisibility(View.INVISIBLE);
                    mShaker.resume();
                    //changeToMain(mBlackCard, mWhiteArrow, mBlackProfile);
                }
                else if (position == 2){
                    mSettings.setVisibility(View.VISIBLE);
                    mShaker.pause();
                    //changeToProfile(mBlackCard, mBlackArrow, mWhiteProfile);
                }
                else if(position == 0){
                    mSettings.setVisibility(View.INVISIBLE);
                    mShaker.pause();
                    //changeToCards(mWhiteCard, mBlackArrow, mBlackProfile);
                }
            }
        });
    }

    public void changeToCards(Drawable whiteCard, Drawable blackArrow, Drawable blackProfile){
       /* mLeft.setImageDrawable(whiteCard);
        mCenter.setImageDrawable(blackArrow);
        mRight.setImageDrawable(blackProfile);*/

        mLeft.setImageDrawable(whiteCard);
        mCenter.setImageDrawable(null);
        mRight.setImageDrawable(null);
    }

    public void changeToMain (Drawable blackCard, Drawable whiteArrow, Drawable blackProfile){
        //mLeft.setImageDrawable(blackCard);
        //mCenter.setImageDrawable(whiteArrow);
        //mRight.setImageDrawable(blackProfile);

        mLeft.setImageDrawable(null);
        mCenter.setImageDrawable(whiteArrow);
        mRight.setImageDrawable(null);
    }

    public void changeToProfile (Drawable blackCard, Drawable blackArrow, Drawable whiteProfile){
        //mLeft.setImageDrawable(blackCard);
      //  mCenter.setImageDrawable(blackArrow);
      //  mRight.setImageDrawable(whiteProfile);

        mLeft.setImageDrawable(null);
        mCenter.setImageDrawable(null);
        mRight.setImageDrawable(whiteProfile);
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
        Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
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
            fragment = new NewMyCards();
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
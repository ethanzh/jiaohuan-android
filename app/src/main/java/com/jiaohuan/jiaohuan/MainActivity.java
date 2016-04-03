package com.jiaohuan.jiaohuan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);

        mSettings = (TextView) findViewById(R.id.settings);
        mSettings.setVisibility(View.INVISIBLE);

        //final Drawable mBlackCard = getResources().getDrawable(R.drawable.black_card);
        //final Drawable mBlackCard = null;
        //final Drawable mWhiteCard = getResources().getDrawable(R.drawable.white_card);

        //final Drawable mBlackArrow = getResources().getDrawable(R.drawable.black_arrow);
        //final Drawable mBlackArrow = null;
        //final Drawable mWhiteArrow = getResources().getDrawable(R.drawable.white_arrow);

        //final Drawable mBlackProfile = getResources().getDrawable(R.drawable.black_profile);
        //final Drawable mBlackProfile = null;
        //final Drawable mWhiteProfile = getResources().getDrawable(R.drawable.white_profile);

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
                    //changeToMain(mBlackCard, mWhiteArrow, mBlackProfile);
                }
                else if (position == 2){
                    mSettings.setVisibility(View.VISIBLE);
                    //changeToProfile(mBlackCard, mBlackArrow, mWhiteProfile);
                }
                else if(position == 0){
                    mSettings.setVisibility(View.INVISIBLE);
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
        mViewPager.setCurrentItem(1);
    }
    public void jumpToProfile(View view) {
        mSettings.setVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(2);
    }
    public void jumpToCards(View view) {
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
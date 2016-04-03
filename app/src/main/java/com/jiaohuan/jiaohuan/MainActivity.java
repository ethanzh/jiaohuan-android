package com.jiaohuan.jiaohuan;

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

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager = null;
    Toolbar mToolbar;
    TextView mChange;
    ImageView mLeft;
    ImageView mCenter;
    ImageView mRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLeft = (ImageView) findViewById(R.id.left);
        mCenter = (ImageView) findViewById(R.id.center);
        mRight = (ImageView) findViewById(R.id.right);


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
                    changeToMain();
                }
                else if (position == 2){
                    changeToProfile();
                }
                else if(position == 0){
                    changeToCards();
                }
            }
        });
    }




    public void jumpToMain(View view) {

        mViewPager.setCurrentItem(1);
    }
    public void jumpToProfile(View view) {

        mViewPager.setCurrentItem(2);
    }
    public void jumpToCards(View view) {

        mViewPager.setCurrentItem(0);
    }

    public void changeToCards(){
        mLeft.setImageResource(R.drawable.white_card);
        mCenter.setImageResource(R.drawable.black_arrow);
        mRight.setImageResource(R.drawable.black_profile);
    }

    public void changeToMain (){
        mCenter.setImageResource(R.drawable.white_arrow);
        mLeft.setImageResource(R.drawable.black_card);
        mRight.setImageResource(R.drawable.black_profile);
    }

    public void changeToProfile (){
        mRight.setImageResource(R.drawable.white_profile);
        mLeft.setImageResource(R.drawable.black_card);
        mCenter.setImageResource(R.drawable.black_arrow);
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
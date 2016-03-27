package com.jiaohuan.jiaohuan;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager = null;
    Toolbar mToolbar;
    TextView mChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new MyAdapter(fragmentManager));
        mViewPager.setCurrentItem(1);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mChange = (TextView) findViewById(R.id.change_me);

        String[] array = {"One", "Two", "Three", "Four"};

        ListAdapter mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
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
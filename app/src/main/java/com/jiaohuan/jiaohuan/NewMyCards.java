package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class NewMyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mLinearLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the view
        View view = inflater.inflate(R.layout.new_my_cards, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mAdapter = new RecycleAdapter(getActivity(), getData());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), R.dimen.padding_four));

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("CLICK", "" + position);

                //Make popup here
                mLayoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.popup, null);

                mPopupWindow = new PopupWindow(mContainer,900, 1600, true);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                mContainer.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mPopupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        return view;
    }

    public static List<OneRow> getData(){
        List<OneRow> data = new ArrayList<>();

        String[] names = {"Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So","Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So"};
        String[] company = {"Gate Education", "Unemployed", "CCP", "ISB","Gate Education", "Unemployed", "CCP", "ISB"};
        String[] phone_nums = {"15488965321","45789632594","15699447584","15811556497","15488965321","45789632594","15699447584","15811556497"};
        String[] emails = {"SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com","SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com"};
        String[] locations = {"Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China"};
        int[] pictures = {R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae,R.drawable.tae};

        for(int i = 0; i < names.length && i<company.length && i<phone_nums.length && i<emails.length && i<locations.length && i < pictures.length; i++){
            OneRow current = new OneRow();

            current.names = names[i];
            current.company = company[i];
            current.phone_nums = phone_nums[i];
            current.emails = emails[i];
            current.locations = locations[i];
            current.pictures = pictures[i];

            data.add(current);
        }
        return data;
    }

}

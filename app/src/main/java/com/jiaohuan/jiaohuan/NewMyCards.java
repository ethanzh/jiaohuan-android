package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewMyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the view
        View view = inflater.inflate(R.layout.new_my_cards, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mAdapter = new RecycleAdapter(getActivity(), getData());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

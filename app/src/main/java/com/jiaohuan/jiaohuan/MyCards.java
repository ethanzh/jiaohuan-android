package com.jiaohuan.jiaohuan;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MyCards extends android.support.v4.app.Fragment {

    private ListView mListView;
    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.my_cards, container, false);

        View view = inflater.inflate(R.layout.my_cards, container, false);

        mListView = (ListView) view.findViewById( R.id.list_view );

        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.my_cards, );


        return view;
    }
}
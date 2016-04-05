package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OldMyCards extends android.support.v4.app.Fragment{

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the view
        View view = inflater.inflate(R.layout.my_cards, container, false);

        //Arrays for test information
        String[] names = {"Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So","Sangwook Park", "Kevin Wu", "Tian Ge Liu", "Teety So"};
        String[] company = {"Gate Education", "Unemployed", "CCP", "ISB","Gate Education", "Unemployed", "CCP", "ISB"};
        String[] phone_nums = {"15488965321","45789632594","15699447584","15811556497","15488965321","45789632594","15699447584","15811556497"};
        String[] emails = {"SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com","SWP@hotmail.com","kevinwu@gmail.com","TGL@hotmail.com","Teety.so@gmail.com"};
        String[] locations = {"Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China","Beijing, China"};
        int[] pictures = {R.drawable.xia,R.drawable.xia,R.drawable.xia,R.drawable.xia,R.drawable.xia,R.drawable.xia,R.drawable.xia,R.drawable.xia};

        //Get reference to the listview
        mListView = (ListView) view.findViewById( R.id.list_view );

        //Initialize the adapter, and set it to the listview
        customAdapter adapter = new customAdapter(getContext(),names,company,phone_nums,emails,locations,pictures);
        mListView.setAdapter(adapter);

        return view;
    }
}
class customAdapter extends ArrayAdapter<String>{
    Context mContext;
    String[] mName;
    String[] mCompany;
    String[] mPhone;
    String[] mLocation;
    String[] mEmail;
    int[] mPicture;

    customAdapter(Context c, String[] names, String[] companies, String[] phonenums, String[] emails, String[] locations, int[] images){
        super(c, R.layout.single_row, R.id.name, names);
        this.mContext = c;
        this.mName = names;
        this.mCompany = companies;
        this.mPhone = phonenums;
        this.mLocation = locations;
        this.mEmail = emails;
        this.mPicture = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mRow = inflater.inflate(R.layout.single_row, parent, false);

        TextView mNameText = (TextView) mRow.findViewById(R.id.name);
        TextView mCompanyText = (TextView) mRow.findViewById(R.id.company);
        TextView mPhoneText = (TextView) mRow.findViewById(R.id.phone);
        TextView mLocationText = (TextView) mRow.findViewById(R.id.location);
        TextView mEmailText = (TextView) mRow.findViewById(R.id.email);
        ImageView mPictureView = (ImageView) mRow.findViewById(R.id.image_view);

        mNameText.setText(mName[position]);
        mCompanyText.setText(mCompany[position]);
        mPhoneText.setText(mPhone[position]);
        mLocationText.setText(mLocation[position]);
        mEmailText.setText(mEmail[position]);

        mPictureView.setImageResource(mPicture[position]);

        int color = Color.argb(255,255,255,255);
        mRow.setBackgroundColor(color);

        mRow.setBackgroundResource(R.drawable.custom_listview_shape);

        return mRow;
    }
}
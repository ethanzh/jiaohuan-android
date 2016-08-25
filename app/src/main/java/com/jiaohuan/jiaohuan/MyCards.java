package com.jiaohuan.jiaohuan;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.jiaohuan.jiaohuan.jsonData.FriendsListJSON;
import com.jiaohuan.jiaohuan.jsonData.User;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mNameAdapter;
    private RecycleAdapter mDateAdapter;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;
    private TextView mPopName;
    private TextView mPopCompany;
    private TextView mPopEmail;
    private TextView mPopPhone;
    private TextView mPopAddress;
    private TextView mPopInfo;
    private TextView mTitle;
    private TextView mClose;
    private TextView mWebsite;
    private ImageView mImageView;
    private ImageView mCard;
    int initial = 0;
    private TextView mShowName;
    private TextView mShowCompany;
    private Button mContactButton;
    private TextView mKnownSince;
    private TextView mDate;
    private TextView mName;
    private boolean nameSelected;
    private boolean dateSelected;
    private RecycleAdapter mNewAdapter;
    private TextView mPinyin;
    RecycleAdapter mTestAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_cards, container, false);


        // Start the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), 32));

        mTestAdapter = new RecycleAdapter(getActivity(), UserList.getCurrent());
        //Log.wtf("LIST", UserList.getCurrent().size() + "");
        mRecyclerView.setAdapter(mTestAdapter);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        mDate = (TextView) view.findViewById(R.id.date);
        mName = (TextView) view.findViewById(R.id.name_tv);

        final int selectedColorValue = Color.parseColor("#FFAF8CFF");
        final int nonSelectedColorValue = Color.parseColor("#FFFFFF");

        nameSelected = true;
        dateSelected = false;

        mName.setTextColor(selectedColorValue);

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameSelected = true;
                dateSelected = false;

                mName.setTextColor(selectedColorValue);
                mDate.setTextColor(nonSelectedColorValue);

//                mRecyclerView.setAdapter(mNameAdapter);
//                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameSelected = false;
                dateSelected = true;

                mName.setTextColor(nonSelectedColorValue);
                mDate.setTextColor(selectedColorValue);

//                mRecyclerView.setAdapter(mDateAdapter);
//                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        // On click listener for each list item
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                expandCard(position);
            }
        });

        return view;
    }

    public void setLabelValues(){
        mPopName.setText(SelectedRow.getCurrent().getUsername());
        mPopCompany.setText(SelectedRow.getCurrent().getCompany());
        mWebsite.setText(SelectedRow.getCurrent().getWebsite());
        mTitle.setText(SelectedRow.getCurrent().getTitle());
        mPopEmail.setText(SelectedRow.getCurrent().getEmail());
        mPopPhone.setText(SelectedRow.getCurrent().getPhoneNumber());
        mPopAddress.setText(SelectedRow.getCurrent().getLocation());
        mPopInfo.setText(SelectedRow.getCurrent().getInfo());

    }

    public void expandCard(int position) {

        // Make card_expand here
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup mContainer = (ViewGroup) layoutInflater.inflate(R.layout.card_expand, null);

        final ScrollView mainScrollView=(ScrollView)mContainer.findViewById(R.id.scroll);
        mainScrollView.smoothScrollTo(0, 0);

        SelectedRow.setCurrent(mTestAdapter.getRow(position));

        // Assign all of the pop up's TextViews
        assignIDs(mContainer);
        setLabelValues();

        // Get top panel
        RelativeLayout topPanel = (RelativeLayout) mContainer.findViewById(R.id.topPanel);

//        if (nameSelected) {
//            SelectedRow.setCurrent(mNameAdapter.getRow(position));
//
//        } else if (dateSelected) {
//            SelectedRow.setCurrent(mDateAdapter.getRow(position));
//        }

        // Get color
        //int color = SelectedRow.getCurrent().getColor();

        // Set color
        //topPanel.setBackgroundColor(color);

        // If white background, make top panel text black
//        if (color == -1) {
//            mShowName.setTextColor(Color.BLACK);
//            mShowCompany.setTextColor(Color.BLACK);
//            mPopName.setTextColor(Color.BLACK);
//            mPopCompany.setTextColor(Color.BLACK);
//            mClose.setTextColor(Color.BLACK);
//        }

        // Take +86 off the front of the phone number
        //String shortendPhone = SelectedRow.getCurrent().getPhoneNum();
        //shortendPhone = shortendPhone.substring(3);

        //String website = trimURL();

        //setExpandValues(website, shortendPhone);

        // Open website
//        mWebsite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = SelectedRow.getCurrent().getWebsite();
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });

        // Open email client
        emailListener(mPopEmail);

        //Call phone number
        // TODO: Test this
//        mPopPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phonnum = SelectedRow.getCurrent().getPhoneNum();
//
//                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phonnum, null)));
//            }
//        });

        List<Integer> dimensions = getPhoneDimens();

        // Starts the pop up (930, 1620)
        mPopupWindow = new PopupWindow(mContainer, dimensions.get(0), dimensions.get(1), true);
        mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

        // Makes card size device independent
        double cardHeight;
        cardHeight = dimensions.get(3) * 0.1;
        int realCardHeight = (int) cardHeight;
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realCardHeight, getResources().getDisplayMetrics());
        mCard.getLayoutParams().height = px;

        // Card picture on click listener
//        mCard.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (initial == 0) {
//                    mCard.setImageBitmap(SelectedRow.getCurrent().getFlipside());
//                    initial = 1;
//                } else {
//                    mCard.setImageBitmap(SelectedRow.getCurrent().getBusiness_card());
//                    initial = 0;
//                }
//            }
//        });

        mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkContactPerms();
                //addContactInfo();

            }
        });

        // Close button click listener
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    public List<Integer> getPhoneDimens(){

        // Returns:
        // popWdith
        // popHeight
        // Width
        // Height

        List<Integer> array = new ArrayList<>();

        // Gets phone dimensions
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        double popUpWidth;
        double popUpHeight;

        popUpWidth = width * 0.86;
        popUpHeight = height * 0.85;

        int popWidth = (int) popUpWidth;
        int popHeight = (int) popUpHeight;

        array.add(popWidth);
        array.add(popHeight);

        array.add(width);
        array.add(height);

        return array;
    }

    void assignIDs(ViewGroup v) {
        mPopName = (TextView) v.findViewById(R.id.pop_name);
        mPopCompany = (TextView) v.findViewById(R.id.pop_company);
        mPopEmail = (TextView) v.findViewById(R.id.pop_email);
        mPopPhone = (TextView) v.findViewById(R.id.pop_phone);
        mPopAddress = (TextView) v.findViewById(R.id.pop_address);
        mPopInfo = (TextView) v.findViewById(R.id.pop_info);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mCard = (ImageView) v.findViewById(R.id.card_pic);
        mClose = (TextView) v.findViewById(R.id.close);
        mTitle = (TextView) v.findViewById(R.id.pop_title);
        mWebsite = (TextView) v.findViewById(R.id.website);
        mShowName = (TextView) v.findViewById(R.id.showname);
        mShowCompany = (TextView) v.findViewById(R.id.showcompany);
        mContactButton = (Button) v.findViewById(R.id.contactButton);
        mKnownSince = (TextView) v.findViewById(R.id.knownthing);
        mPinyin = (TextView) v.findViewById(R.id.pinyin);
    }

    // TODO: Work on this later
    public ArrayList getContactInfo() {
        // Make function here to iterate through all the current contacts
        ContentResolver cr = getContext().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> phoneList = new ArrayList<String>();

        int numberOfContacts = (cur.getCount());

        if (cur.getCount() > 0) {

            while (cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        nameList.add(name);
                        phoneList.add(phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        Log.wtf("Count", "Number of contacts: " + numberOfContacts);
        return nameList;
    }

    private void checkContactPerms() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_CODE_ASK_PERMISSIONS = 123;
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }

    }

//    public void addContactInfo() {
//        ContentResolver cr = getContext().getContentResolver();
//        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "accountname@gmail.com")
//                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "com.cn.jiaohuan.jiaohuan")
//                .build());
//
//        // Add name
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE,
//                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, SelectedRow.getCurrent().getName())
//                .build());
//
//        // Add phone number
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE,
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, SelectedRow.getCurrent().getPhoneNum())
//                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE)
//                .build());
//
//        // Add notes
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE,
//                        ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Note.NOTE, SelectedRow.getCurrent().getInfo())
//                .build());
//
//        // Add picture WORK IN PROGRESS
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, SelectedRow.getCurrent().getPic())
//                .build());
//
//        // Add email
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Email.DATA, SelectedRow.getCurrent().getEmail())
//                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
//                .build());
//
//        // Add address
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, SelectedRow.getCurrent().getLocation())
//                .build());
//        try {
//            cr.applyBatch(ContactsContract.AUTHORITY, ops);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<List<Contact>> createLists() {

        ArrayList<List<Contact>> list = new ArrayList<>();

//        list.add(FakeDatabase.getInstance().getAlphaSorted());
//        list.add(FakeDatabase.getInstance().getDateSorted());

        return list;
    }

    public void makeSearch(View view, final List<Contact> alphaSorted) {
        SearchView searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();

                final List<Contact> filteredList = new ArrayList<>();

                for (int i = 0; i < alphaSorted.size(); i++) {

                    final String chineseName = alphaSorted.get(i).getName().toLowerCase();
                    final String pinyinName = alphaSorted.get(i).getPinyin().toLowerCase();
                    final String company = alphaSorted.get(i).getCompany().toLowerCase();

                    if (chineseName.contains(query)) {
                        filteredList.add(alphaSorted.get(i));
                    }
                    else if (company.contains(query)) {
                        filteredList.add(alphaSorted.get(i));
                    }
                    else if (pinyinName.contains(query)){
                        filteredList.add(alphaSorted.get(i));
                    }
                }

                //mNewAdapter = new RecycleAdapter(getActivity(), filteredList);
//                mRecyclerView.setAdapter(mNewAdapter);
               // mNewAdapter.notifyDataSetChanged();  // data set changed

                return true;
            }

        });
    }

//    public String trimURL(){
//
//        // Take beginning off of website
//        String website = SelectedRow.getCurrent().getWebsite();
//        String http = website.substring(0, 5);
//
//        // Remove http:// or https://
//        if(http.equals("http:")){
//            website = website.substring(7);
//        }else if(http.equals("https")){
//            website = website.substring(8);
//        }
//
//        // Remove www.
//        String www = website.substring(0, 4);
//        if(www.equals("www.")){
//            website = website.substring(4);
//        }
//
//        // Remove ending '/' if it's there
//        String end = website.substring(website.length() - 1);
//        if(end.equals("/")){
//            website = website.substring(0, website.length() - 1);
//        }
//
//        return website;
//    }

    public void setExpandValues(){
        // Gets text from the (fake) database and prints them to the activity
        mPopName.setText(SelectedRow.getCurrent().getUsername());
        mPopCompany.setText(SelectedRow.getCurrent().getCompany());
        mPopEmail.setText(SelectedRow.getCurrent().getEmail());
        //mPopAddress.setText(SelectedRow.getCurrent().ge());
        //mPopInfo.setText(SelectedRow.getCurrent().getInfo());
        //mWebsite.setText(website);
        //mPopPhone.setText(shortendPhone);
        //mTitle.setText(SelectedRow.getCurrent().getTitle());
        //mImageView.setImageBitmap(SelectedRow.getCurrent().getPic());
        //mCard.setImageBitmap(SelectedRow.getCurrent().getBusiness_card());
        //mKnownSince.setText(SelectedRow.getCurrent().getSimple_date());
        //mPinyin.setText(SelectedRow.getCurrent().getPinyin());
    }

    public void emailListener(TextView PopEmail){
        PopEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SelectedRow.getCurrent().getEmail();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");

                // Maybe consider using this in the future
                //intent.setType(android.content.Intent.ACTION_SENDTO (new Intent(Intent.ACTION_SENDTO);))
                intent.putExtra(Intent.EXTRA_EMAIL, email);

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        PopEmail.setTextIsSelectable(true);
    }
}

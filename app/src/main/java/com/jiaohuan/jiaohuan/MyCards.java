package com.jiaohuan.jiaohuan;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.TextView;

import java.util.ArrayList;

public class MyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private RecycleAdapter mSecond;
    private PopupWindow mPopupWindow;
    private LayoutInflater mLayoutInflater;
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
    private int mColor;
    private RelativeLayout mTopPanel;
    int initial = 0;
    private TextView mShowName;
    private TextView mShowCompany;
    private Button mContactButton;
    private Button mGetContacts;
    private TextView mKnownSince;

    private Button mDate;
    private Button mName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_cards, container, false);

        // Start the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        mAdapter = new RecycleAdapter(getActivity(), FakeDatabase.getInstance().getAlphaSorted());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), R.dimen.padding_four));

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        mDate = (Button) view.findViewById(R.id.date);
        mName = (Button) view.findViewById(R.id.name);

        mGetContacts = (Button) view.findViewById(R.id.getcontacts);

        mGetContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("Contact", "" + getContactInfo());
            }
        });

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAdapter.swap(FakeDatabase.getInstance().getDateSorted());

            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSecond = new RecycleAdapter(getActivity(), FakeDatabase.getInstance().getDateSorted());

                mRecyclerView.swapAdapter(mSecond, false);

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });

        // On click listener for each list item
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                // Make card_expand here
                mLayoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.card_expand, null);

                // Assign all of the pop up's TextViews
                mPopName = (TextView) mContainer.findViewById(R.id.pop_name);
                mPopCompany = (TextView) mContainer.findViewById(R.id.pop_company);
                mPopEmail = (TextView) mContainer.findViewById(R.id.pop_email);
                mPopPhone = (TextView) mContainer.findViewById(R.id.pop_phone);
                mPopAddress = (TextView) mContainer.findViewById(R.id.pop_address);
                mPopInfo = (TextView) mContainer.findViewById(R.id.pop_info);
                mImageView = (ImageView) mContainer.findViewById(R.id.image);
                mCard = (ImageView) mContainer.findViewById(R.id.card_pic);
                mClose = (TextView) mContainer.findViewById(R.id.close);
                mTitle = (TextView) mContainer.findViewById(R.id.pop_title);
                mWebsite = (TextView) mContainer.findViewById(R.id.website);
                mShowName = (TextView) mContainer.findViewById(R.id.showname);
                mShowCompany = (TextView) mContainer.findViewById(R.id.showcompany);
                mContactButton = (Button) mContainer.findViewById(R.id.contactButton);
                mKnownSince = (TextView) mContainer.findViewById(R.id.knownthing);

                // Get top panel
                mTopPanel = (RelativeLayout) mContainer.findViewById(R.id.topPanel);

                // Gets the data of the clicked card
                final Contact selectedRow = mAdapter.getRow(position);

                // Get color
                mColor = selectedRow.getColor();

                // Set color
                mTopPanel.setBackgroundColor(mColor);

                // If white background, make top panel text black
                if (mColor == -1) {
                    mShowName.setTextColor(Color.BLACK);
                    mShowCompany.setTextColor(Color.BLACK);
                    mPopName.setTextColor(Color.BLACK);
                    mPopCompany.setTextColor(Color.BLACK);
                    mClose.setTextColor(Color.BLACK);
                }

                // Gets text from the (fake) database and prints them to the activity
                mPopName.setText(selectedRow.getName());
                mPopCompany.setText(selectedRow.getCompany());
                mPopEmail.setText(selectedRow.getEmail());
                mPopAddress.setText(selectedRow.getAddress());
                mPopInfo.setText(selectedRow.getInfo());
                mWebsite.setText(selectedRow.getWebsite());
                mPopPhone.setText(selectedRow.getPhoneNum());
                mTitle.setText(selectedRow.getTitle());
                mImageView.setImageResource(selectedRow.getPic());
                mCard.setImageResource(selectedRow.getBusiness_card());
                mKnownSince.setText(selectedRow.getSimple_date());

                // Gets phone dimensions
                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                Log.d("ScreenResolution", "" + width + "," + height);

                double popUpWidth;
                double popUpHeight;

                popUpWidth = width * 0.86;
                popUpHeight = height * 0.85;

                int popWidth = (int) popUpWidth;
                int popHeight = (int) popUpHeight;

                // Starts the pop up               (930, 1620)
                mPopupWindow = new PopupWindow(mContainer, popWidth, popHeight, true);
                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

                // Card picture on click listener
                mCard.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (initial == 0) {
                            mCard.setImageResource(selectedRow.getFlipside());
                            initial = 1;
                        } else {
                            mCard.setImageResource(selectedRow.getBusiness_card());
                            initial = 0;
                        }
                    }
                });

                mContactButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentResolver cr = getContext().getContentResolver();
                        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

                        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "accountname@gmail.com")
                                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "com.cn.jiaohuan.jiaohuan")
                                .build());

                        // Add name
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, selectedRow.getName())
                                .build());

                        // Add phone number
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, selectedRow.getPhoneNum())
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE)
                                .build());

                        // Add notes
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Note.NOTE, selectedRow.getInfo())
                                .build());

                        // Add picture WORK IN PROGRESS
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, selectedRow.getPic())
                                .build());

                        // Add email
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Email.DATA, selectedRow.getEmail())
                                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                .build());

                        // Add address
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, selectedRow.getLocation())
                                .build());


                        try {
                            cr.applyBatch(ContactsContract.AUTHORITY, ops);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (OperationApplicationException e) {
                            e.printStackTrace();
                        }
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
        });

        return view;
    }

    // Work on this later
    public ArrayList getContactInfo(){
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
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);

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
}

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
import android.widget.TextView;

import java.util.ArrayList;

public class MyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mNameAdapter;
    private RecycleAdapter mDateAdapter;
    private RecycleAdapter mReverseNameAdapter;
    private RecycleAdapter mReverseDateAdapter;
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

    private ImageView mArrow;

    boolean NAME = true;
    boolean DATE = false;

    private int selectedColorValue;
    private int nonSelectedColorValue;

    private Boolean nameSelected;
    private Boolean dateSelected;
    private Boolean arrowUp;

    private TextView mDate;
    private TextView mName;

    private ArrayList<Contact> AlphaSorted;
    private ArrayList<Contact> UnixSorted;

    private ArrayList<Contact> AlphaReversed;
    private ArrayList<Contact> UnixReversed;

    //public static Contact selectedRow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_cards, container, false);

        AlphaSorted = (ArrayList<Contact>) FakeDatabase.getInstance().getAlphaSorted();
        UnixSorted = (ArrayList<Contact>) FakeDatabase.getInstance().getDateSorted();

        AlphaReversed = (ArrayList<Contact>) FakeDatabase.getInstance().getReverseAlpha();
        UnixReversed = (ArrayList<Contact>) FakeDatabase.getInstance().getReverseUnix();

        // Start the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mNameAdapter = new RecycleAdapter(getActivity(), AlphaSorted);
        mDateAdapter = new RecycleAdapter(getActivity(), UnixSorted);

        mReverseNameAdapter = new RecycleAdapter(getActivity(), AlphaReversed);
        mReverseDateAdapter = new RecycleAdapter(getActivity(), UnixReversed);

        //mLinearLayoutManager = (LinearLayoutManager) getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mNameAdapter);
        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), 32));

        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        mDate = (TextView) view.findViewById(R.id.date);
        mName = (TextView) view.findViewById(R.id.name);
        mArrow = (ImageView) view.findViewById(R.id.arrow);

        final int selectedColorValue = Color.parseColor("#FF00FF");
        final int nonSelectedColorValue = Color.parseColor("#FFFFFF");

        arrowUp = false;

        mName.setTextColor(selectedColorValue);

        mGetContacts = (Button) view.findViewById(R.id.getcontacts);

        nameSelected = true;
        dateSelected = false;


        mGetContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("Contact", "" + getContactInfo());
            }
        });

        mArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NAME){
                    if(nameSelected){
                        mRecyclerView.swapAdapter(mReverseNameAdapter, false);
                        nameSelected = false;
                        mArrow.setImageResource(R.drawable.up);
                        arrowUp = true;
                    } else{
                        mRecyclerView.swapAdapter(mNameAdapter, false);
                        nameSelected = true;
                        mArrow.setImageResource(R.drawable.down);
                        arrowUp = false;
                    }
                }
                if(DATE){
                    if(dateSelected){
                        mRecyclerView.swapAdapter(mReverseDateAdapter, false);
                        dateSelected = false;
                        mArrow.setImageResource(R.drawable.up);
                        arrowUp = true;
                    } else{
                        mRecyclerView.swapAdapter(mDateAdapter, false);
                        dateSelected = true;
                        mArrow.setImageResource(R.drawable.down);
                        arrowUp = false;
                    }
                }
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = true;
                DATE = false;

                if(mName.getCurrentTextColor() == nonSelectedColorValue){
                    mArrow.setImageResource(R.drawable.down);
                    mRecyclerView.swapAdapter(mNameAdapter, false);
                    mName.setTextColor(selectedColorValue);
                    mDate.setTextColor(nonSelectedColorValue);
                    arrowUp = false;
                } else{
                    if(nameSelected){
                        mRecyclerView.swapAdapter(mReverseNameAdapter, false);
                        nameSelected = false;
                        mArrow.setImageResource(R.drawable.up);
                        arrowUp = true;
                    } else{
                        mRecyclerView.swapAdapter(mNameAdapter, false);
                        nameSelected = true;
                        mArrow.setImageResource(R.drawable.down);
                        arrowUp = false;
                    }

                }
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = false;
                DATE = true;

                if(mDate.getCurrentTextColor() == nonSelectedColorValue){
                    mArrow.setImageResource(R.drawable.down);
                    mRecyclerView.swapAdapter(mDateAdapter, false);
                    mName.setTextColor(nonSelectedColorValue);
                    mDate.setTextColor(selectedColorValue);
                    arrowUp = false;
                } else{
                    if(dateSelected){
                        mRecyclerView.swapAdapter(mReverseDateAdapter, false);
                        dateSelected = false;
                        mArrow.setImageResource(R.drawable.up);
                        arrowUp = true;
                    } else{
                        mRecyclerView.swapAdapter(mDateAdapter, false);
                        dateSelected = true;
                        mArrow.setImageResource(R.drawable.down);
                        arrowUp = false;
                    }
                }
                mRecyclerView.smoothScrollToPosition(0);

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

                // TODO: fix this - Gets the data of the clicked card
                Contact selectedRow = mNameAdapter.getRow(position);
                Log.wtf("THIS", "" + mArrow.getAlpha());

                if(NAME){
                    if(arrowUp){
                        //selectedRow = mReverseNameAdapter.getRow(position);
                        SelectedRow.setCurrent(mReverseNameAdapter.getRow(position));
                    } else{
                        SelectedRow.setCurrent(mNameAdapter.getRow(position));
                    }
                }
                else if(DATE){
                    if(arrowUp){
                        SelectedRow.setCurrent(mReverseDateAdapter.getRow(position));
                    } else{
                        SelectedRow.setCurrent(mDateAdapter.getRow(position));
                    }
                }


                // Get color
                mColor = SelectedRow.getCurrent().getColor();



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
                mPopName.setText(SelectedRow.getCurrent().getName());
                mPopCompany.setText(SelectedRow.getCurrent().getCompany());
                mPopEmail.setText(SelectedRow.getCurrent().getEmail());
                mPopAddress.setText(SelectedRow.getCurrent().getAddress());
                mPopInfo.setText(SelectedRow.getCurrent().getInfo());
                mWebsite.setText(SelectedRow.getCurrent().getWebsite());
                mPopPhone.setText(SelectedRow.getCurrent().getPhoneNum());
                mTitle.setText(SelectedRow.getCurrent().getTitle());
                mImageView.setImageResource(SelectedRow.getCurrent().getPic());
                mCard.setImageResource(SelectedRow.getCurrent().getBusiness_card());
                mKnownSince.setText(SelectedRow.getCurrent().getSimple_date());

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

                mRecyclerView.smoothScrollToPosition(position);

                // Makes card size device independent
                double cardHeight;
                cardHeight = height * 0.1;
                int realCardHeight = (int) cardHeight;
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, realCardHeight, getResources().getDisplayMetrics());
                mCard.getLayoutParams().height = px;

                // Card picture on click listener
                mCard.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (initial == 0) {
                            mCard.setImageResource(SelectedRow.getCurrent().getFlipside());
                            initial = 1;
                        } else {
                            mCard.setImageResource(SelectedRow.getCurrent().getBusiness_card());
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
                                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, SelectedRow.getCurrent().getName())
                                .build());

                        // Add phone number
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, SelectedRow.getCurrent().getPhoneNum())
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE)
                                .build());

                        // Add notes
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Note.NOTE, SelectedRow.getCurrent().getInfo())
                                .build());

                        // Add picture WORK IN PROGRESS
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, SelectedRow.getCurrent().getPic())
                                .build());

                        // Add email
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Email.DATA, SelectedRow.getCurrent().getEmail())
                                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                .build());

                        // Add address
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, SelectedRow.getCurrent().getLocation())
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

    // TODO: Work on this later
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

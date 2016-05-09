package com.jiaohuan.jiaohuan;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
import android.widget.Toast;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCards extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mNameAdapter;
    private RecycleAdapter mDateAdapter;
    private RecycleAdapter mReverseNameAdapter;
    private RecycleAdapter mReverseDateAdapter;
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
    private ImageView mArrow;
    private TextView mDate;
    private TextView mName;
    private boolean arrowIsUp;
    private boolean nameSelected;
    private boolean dateSelected;
    private SearchView mSearchView;
    private RecycleAdapter mNewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_cards, container, false);

        final ArrayList<List<Contact>> adapters = createLists();

        final List<Contact> alphaSorted = adapters.get(0);
        List<Contact> unixSorted = adapters.get(1);
        List<Contact> alphaReversed = adapters.get(2);
        List<Contact> unixReversed = adapters.get(3);

        // Start the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        mNameAdapter = new RecycleAdapter(getActivity(), alphaSorted);
        mDateAdapter = new RecycleAdapter(getActivity(), unixSorted);

        mReverseNameAdapter = new RecycleAdapter(getActivity(), alphaReversed);
        mReverseDateAdapter = new RecycleAdapter(getActivity(), unixReversed);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mNameAdapter);
        mRecyclerView.addItemDecoration(new ListSpacingDecoration(getActivity(), 32));

        mSearchView = (SearchView) view.findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();

                final List<Contact> filteredList = new ArrayList<>();

                for (int i = 0; i < alphaSorted.size(); i++) {

                    final String name = alphaSorted.get(i).getName().toLowerCase();
                    final String company = alphaSorted.get(i).getCompany().toLowerCase();

                    if (name.contains(query)) {
                        filteredList.add(alphaSorted.get(i));

                    } else if(company.contains(query)){
                        filteredList.add(alphaSorted.get(i));
                    }
                }

                mNewAdapter = new RecycleAdapter(getActivity(), filteredList);
                mRecyclerView.setAdapter(mNewAdapter);
                mNewAdapter.notifyDataSetChanged();  // data set changed

                return true;
            }

        });


        mLinearLayout = (LinearLayout) view.findViewById(R.id.linlay);

        mDate = (TextView) view.findViewById(R.id.date);
        mName = (TextView) view.findViewById(R.id.name);
        mArrow = (ImageView) view.findViewById(R.id.arrow);

        final int selectedColorValue = Color.parseColor("#FFAF8CFF");
        final int nonSelectedColorValue = Color.parseColor("#FFFFFF");

        nameSelected = true;
        dateSelected = false;
        arrowIsUp = false;

        mName.setTextColor(selectedColorValue);

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setTextColor(selectedColorValue);
                mDate.setTextColor(nonSelectedColorValue);

                mRecyclerView.setAdapter(mNameAdapter);
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setTextColor(nonSelectedColorValue);
                mDate.setTextColor(selectedColorValue);

                mRecyclerView.setAdapter(mDateAdapter);
                mRecyclerView.smoothScrollToPosition(0);
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

    void assignIDs(ViewGroup v){
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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void checkContactPerms() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }

    }

    public void addContactInfo(){
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

    public void expandCard(int position){
        // Make card_expand here
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup mContainer = (ViewGroup) layoutInflater.inflate(R.layout.card_expand, null);

        // Assign all of the pop up's TextViews
        assignIDs(mContainer);

        // Get top panel
        RelativeLayout topPanel = (RelativeLayout) mContainer.findViewById(R.id.topPanel);

        if(nameSelected){
            if(arrowIsUp){
                SelectedRow.setCurrent(mReverseNameAdapter.getRow(position));
            } else{
                SelectedRow.setCurrent(mNameAdapter.getRow(position));
            }
        }
        else if(dateSelected){
            if(arrowIsUp){
                SelectedRow.setCurrent(mReverseDateAdapter.getRow(position));
            } else{
                SelectedRow.setCurrent(mDateAdapter.getRow(position));
            }
        }

        // Get color
        int color = SelectedRow.getCurrent().getColor();

        // Set color
        topPanel.setBackgroundColor(color);

        // If white background, make top panel text black
        if (color == -1) {
            mShowName.setTextColor(Color.BLACK);
            mShowCompany.setTextColor(Color.BLACK);
            mPopName.setTextColor(Color.BLACK);
            mPopCompany.setTextColor(Color.BLACK);
            mClose.setTextColor(Color.BLACK);
        }

        // Take +86 off the front of the phone number
        String shortendPhone = SelectedRow.getCurrent().getPhoneNum();
        shortendPhone = shortendPhone.substring(3);

        // Gets text from the (fake) database and prints them to the activity
        mPopName.setText(SelectedRow.getCurrent().getName());
        mPopCompany.setText(SelectedRow.getCurrent().getCompany());
        mPopEmail.setText(SelectedRow.getCurrent().getEmail());
        mPopAddress.setText(SelectedRow.getCurrent().getAddress());
        mPopInfo.setText(SelectedRow.getCurrent().getInfo());
        mWebsite.setText(SelectedRow.getCurrent().getWebsite());
        mPopPhone.setText(shortendPhone);
        mTitle.setText(SelectedRow.getCurrent().getTitle());
        mImageView.setImageBitmap(SelectedRow.getCurrent().getPic());
        mCard.setImageBitmap(SelectedRow.getCurrent().getBusiness_card());
        mKnownSince.setText(SelectedRow.getCurrent().getSimple_date());

        // Unicode testing
        String name = SelectedRow.getCurrent().getName();
        name = name.substring(0, 1);
        char single = name.charAt(0);
        String hex = String.format("%04X", (int)single);
        Log.wtf("Hex", hex + "");

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getFromAssets();

        // Open website
        mWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = SelectedRow.getCurrent().getWebsite();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Open email client
        mPopEmail.setOnClickListener(new View.OnClickListener() {
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

        //Call phone number
        // TODO: Test this
        mPopPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonnum = SelectedRow.getCurrent().getPhoneNum();

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phonnum, null)));
            }
        });

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

        // Starts the pop up (930, 1620)
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
                    mCard.setImageBitmap(SelectedRow.getCurrent().getFlipside());
                    initial = 1;
                } else {
                    mCard.setImageBitmap(SelectedRow.getCurrent().getBusiness_card());
                    initial = 0;
                }
            }
        });

        mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkContactPerms();
                addContactInfo();

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

    public ArrayList<List<Contact>> createLists(){

        ArrayList<List<Contact>> list = new ArrayList<>();

        list.add(FakeDatabase.getInstance().getAlphaSorted());
        list.add(FakeDatabase.getInstance().getDateSorted());
        list.add(FakeDatabase.getInstance().getReverseAlpha());
        list.add(FakeDatabase.getInstance().getReverseUnix());

        return list;

        /*ArrayList<Contact> alphaSorted = (ArrayList<Contact>) FakeDatabase.getInstance().getAlphaSorted();
        ArrayList<Contact> unixSorted = (ArrayList<Contact>) FakeDatabase.getInstance().getDateSorted();

        ArrayList<Contact> alphaReversed = (ArrayList<Contact>) FakeDatabase.getInstance().getReverseAlpha();
        ArrayList<Contact> unixReversed = (ArrayList<Contact>) FakeDatabase.getInstance().getReverseUnix();*/
    }
    public List<Contact> getCurrentList(ArrayList<List<Contact>> wholeList, int list){

        return wholeList.get(list);
    }

    void getFromAssets() {

         AssetManager assetManager = MyApplication.getContext().getAssets();
        try {
            String[] files = assetManager.list("");
            Log.wtf("Files", files + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile() throws IOException {

        File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File(sdcard,"pinyin.txt");

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            /*while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
                //Log.wtf("Line", "" + br.readLine());
            }*/
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
    }


}

package com.jiaohuan.jiaohuan;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditMyProfile extends Activity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private Contact myData;
    private String initialName;
    private String initialEmail;
    private String initialPhone;
    private String initialLocation;

    private String finalName;
    private String finalEmail;
    private String finalPhone;
    private String finalLocation;
    private Bitmap finalCard;
    private Bitmap finalFlip;

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mLocation;

    private Button mFinish;
    private Button mBack;

    private ImageView mTop;
    private ImageView mBottom;

    private LinearLayout mLinearLayout;
    private PopupWindow mPopupWindow;

    private Button mCamera;
    private Button mLocal;

    private LayoutInflater mLayoutInflater;

    private Bitmap initialCard;
    private Bitmap initialFlip;

    int cameraRequestCode;
    int localRequestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_my_profile);

        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity_layout);

        mTop = (ImageView) findViewById(R.id.frontofcard);
        mBottom = (ImageView) findViewById(R.id.backofcard);

        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.phone);
        mLocation = (EditText) findViewById(R.id.location);

        mFinish = (Button) findViewById(R.id.finish);
        mBack = (Button) findViewById(R.id.back);

        mLayoutInflater = (LayoutInflater) EditMyProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.card_select_menu, null);

        mCamera = (Button) mContainer.findViewById(R.id.camera);
        mLocal = (Button) mContainer.findViewById(R.id.local);

        // Get my data from fake database
        myData = FakeDatabase.getInstance().getMyData();

        // INITIAL VALUES
        initialName = myData.getName();
        initialEmail = myData.getEmail();
        initialPhone = myData.getPhoneNum();
        initialLocation = myData.getLocation();
        initialCard = myData.getBusiness_card();
        initialFlip = myData.getFlipside();

        mName.setText(initialName);
        mEmail.setText(initialEmail);
        mPhone.setText(initialPhone);
        mLocation.setText(initialLocation);
        mTop.setImageBitmap(initialCard);
        mBottom.setImageBitmap(initialFlip);

        String dirname = Environment.getExternalStorageDirectory() + "/Jiaohuan/My Profile/";

        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWindow(mContainer, 0);
            }
        });

        mBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWindow(mContainer, 1);
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalName = mName.getText().toString();
                finalEmail = mEmail.getText().toString();
                finalPhone = mPhone.getText().toString();
                finalLocation = mLocation.getText().toString();
                finalCard = ((BitmapDrawable)mTop.getDrawable()).getBitmap();
                finalFlip = ((BitmapDrawable)mBottom.getDrawable()).getBitmap();


                if (!finalName.equals(initialName)){
                    Toast.makeText(EditMyProfile.this, "Name was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setName(finalName);
                }
                if (!finalEmail.equals(initialEmail)){
                    Toast.makeText(EditMyProfile.this, "Email was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setEmail(finalEmail);
                }
                if (!finalPhone.equals(initialPhone)){
                    Toast.makeText(EditMyProfile.this, "Phone was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setPhoneNum(finalPhone);
                }
                if (!finalLocation.equals(initialLocation)){
                    Toast.makeText(EditMyProfile.this, "Location was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setLocation(finalLocation);
                }
                if(finalCard != initialCard){
                    Toast.makeText(EditMyProfile.this, "Card was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setBusiness_card(finalCard);
                }
                if(finalFlip != initialFlip){
                    Toast.makeText(EditMyProfile.this, "Flip was changed",
                            Toast.LENGTH_SHORT).show();
                    myData.setFlipside(finalFlip);
                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1 || requestCode == 3) {
                setPictureFromCamera(requestCode, data);

            } else if (requestCode == 2 || requestCode == 4) {
                setPictureFromGallery(requestCode, data);
            }
        }
    }
    public void openWindow(ViewGroup v, int position){
        mPopupWindow = new PopupWindow(v, 1000, 400, true);

        mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

        mCamera.setBackgroundColor(Color.WHITE);
        mLocal.setBackgroundColor(Color.WHITE);

        // When anywhere is tapped, the pop up dismisses, it also resumes the shaker
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPopupWindow.dismiss();
                return true;
            }
        });

        if(position == 0){
            cameraRequestCode = 1;
            localRequestCode = 2;
        }
        else if(position == 1){
            cameraRequestCode = 3;
            localRequestCode = 4;
        }

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPerms(cameraRequestCode);
            }
        });

        mLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture(localRequestCode);
            }
        });
    }

    // Check that app has permission to use camera
    private void checkCameraPerms(int requestCode) {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(EditMyProfile.this, Manifest.permission.CAMERA);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
            }

        }
        takePicture(requestCode);
    }

    private void takePicture(int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);
    }

    private void selectPicture(int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);
    }

    private void setPictureFromCamera(int requestCode, Intent data){
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(requestCode == 1){
            mTop.setImageBitmap(thumbnail);
        } else if(requestCode == 3){
            mBottom.setImageBitmap(thumbnail);
        }

        mPopupWindow.dismiss();

    }

    private void setPictureFromGallery(int requestCode, Intent data){
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
                null);
        Cursor cursor =cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        bm = Bitmap.createBitmap(bm, 0, 0, 400, 400);

        if(requestCode == 2){
            mTop.setImageBitmap(bm);
        } else if(requestCode == 4){
            mBottom.setImageBitmap(bm);
        }
        mPopupWindow.dismiss();
    }
}

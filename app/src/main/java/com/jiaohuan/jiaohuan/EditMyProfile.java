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
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyProfile extends Activity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private Contact myData;
    private String initialName;
    private String initialEmail;
    private String initialPhone;
    private String initialLocation;
    private String initialCompany;

    private String finalName;
    private String finalEmail;
    private String finalPhone;
    private String finalLocation;
    private Bitmap finalCard;
    private Bitmap finalFlip;
    private String finalCompany;

    String request_email;
    String request_phone;
    String request_location;
    String request_company;

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mLocation;
    private EditText mCompany;

    private Button mPost;

    private Button mFinish;

    private ImageView mTop;
    private ImageView mBottom;

    private RelativeLayout mRelativeLayout;
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

        mRelativeLayout = (RelativeLayout) findViewById(R.id.main_activity_layout);

        mEmail = (EditText) findViewById(R.id.email_tv);
        mPhone = (EditText) findViewById(R.id.phone_tv);
        mLocation = (EditText) findViewById(R.id.location_tv);
        mCompany = (EditText) findViewById(R.id.company_tv);

        mEmail.setText("");
        mPhone.setText("");
        mLocation.setText("");
        mCompany.setText("");

        mFinish = (Button) findViewById(R.id.finish);

        mLayoutInflater = (LayoutInflater) EditMyProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup mContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.card_select_menu, null);

        mCamera = (Button) mContainer.findViewById(R.id.camera);
        mLocal = (Button) mContainer.findViewById(R.id.local);

        // INITIAL VALUES
        initialEmail = CurrentUserObject.getCurrent().getEmail();
        initialPhone = CurrentUserObject.getCurrent().getPhoneNumber();
        initialCompany = CurrentUserObject.getCurrent().getCompany();
        initialLocation = CurrentUserObject.getCurrent().getLocation();

        mEmail.setHint(initialEmail);
        mPhone.setHint(initialPhone);
        mLocation.setHint(initialLocation);
        mCompany.setHint(initialCompany);


        Log.wtf("Initial Values", "Email: " + initialEmail + "\n" + "Phone: " + initialPhone + "\n" + "Company: " + initialCompany + "\n"
        + "Location: " + initialLocation);

        initialCard = null;
        initialFlip = null;

//        mTop.setImageBitmap(initialCard);
//        mBottom.setImageBitmap(initialFlip);

        String dirname = Environment.getExternalStorageDirectory() + "/Jiaohuan/My Profile/";

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer account_id = CurrentUserObject.getCurrent().getId();

                finalEmail = mEmail.getText().toString();
                finalPhone = mPhone.getText().toString();
                finalLocation = mLocation.getText().toString();
                finalCompany = mCompany.getText().toString();

                Log.wtf("Final Values", "Email: " + finalEmail + "\n" + "Phone: " + finalPhone + "\n" + "Company: " + finalCompany + "\n"
                        + "Location: " + finalLocation);

                if(finalEmail.equals(initialEmail) || finalEmail.equals("")){
                    request_email = "";
                } else{
                    request_email = finalEmail;
                    CurrentUserObject.getCurrent().setEmail(request_email);
                }

                if(finalPhone.equals(initialPhone) || finalPhone.equals("")){
                    request_phone = "";
                }else{
                    request_phone = finalPhone;
                    CurrentUserObject.getCurrent().setPhoneNumber(request_phone);
                }

                if(finalLocation.equals(initialLocation) || finalLocation.equals("")){
                    request_location = "";
                } else{
                    request_location = finalLocation;
                    CurrentUserObject.getCurrent().setLocation(request_location);
                }

                if(finalCompany.equals(initialCompany) || finalCompany.equals("")){
                    request_company = "";
                } else{
                    request_company = finalCompany;
                    CurrentUserObject.getCurrent().setCompany(request_company);
                }

                Log.wtf("Request Values", "Email: " + request_email + "\n" + "Phone: " + request_phone + "\n" + "Company: " + request_company + "\n"
                        + "Location: " + request_location);

                UserAPI.Factory.getInstance().updateAll(request_email, request_company, request_phone,
                        request_location, account_id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.wtf("SUCCESSFUL", "Request worked");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.wtf("FAILED", t.getMessage());
                    }
                });


                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish();

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
        // Gets phone dimensions
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        double popUpWidth;
        double popUpHeight;

        popUpWidth = width * 0.93;
        popUpHeight = height * 0.20;

        int popWidth = (int) popUpWidth;
        int popHeight = (int) popUpHeight;

        // 1000, 400
        mPopupWindow = new PopupWindow(v, popWidth, popHeight, true);

        // 0.93, 0.20

        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER_HORIZONTAL, 0, 0);

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

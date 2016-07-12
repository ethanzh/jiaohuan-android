package com.jiaohuan.jiaohuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaohuan.jiaohuan.jsonData.MobileAuthUserJSON;
import com.jiaohuan.jiaohuan.jsonData.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterDetailsActivity extends Activity {

    private TextView mNext;

    private EditText mName;
    private EditText mPassword;
    private EditText mPhone;
    private TextView mBack;
    private Spinner mSpinner;

    private RetrofitLogin mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);

        mNext = (TextView) findViewById(R.id.next);
        mName = (EditText) findViewById(R.id.email_tv);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone_tv);
        mBack = (TextView) findViewById(R.id.back);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nums, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mName.getText().toString();
                final String password = mPassword.getText().toString();
                final String phone = mPhone.getText().toString();

                Log.wtf("PHONE", phone);

                //int phone_number = Integer.parseInt(phone);

                if (email.matches("") || password.matches("") || phone.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
                    mName.setHintTextColor(Color.RED);
                    mPassword.setHintTextColor(Color.RED);
                    mPhone.setHintTextColor(Color.RED);
                }

                else{
                    /*

                    Send email, password, and phone to server*/

                    UserAPI.Factory.getInstance().createUser(email, password, phone).enqueue(new Callback<MobileAuthUserJSON>() {

                    @Override
                    public void onResponse(Call<MobileAuthUserJSON> call, Response<MobileAuthUserJSON> response) {

                        try{

                            Log.wtf("CREDENTIALS", response.body().getUsername());
                            Log.wtf("CREDENTIALS", response.body().getAuthenticated() + "");




                            mAuthTask = new RetrofitLogin(email, password);
                            mAuthTask.logInTask(email, password, new LoginCallback(){

                                @Override
                                public void onLoginSuccess() {

                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    openMainActivity();

                                }

                                @Override
                                public void onLoginFailure() {
                                    Log.wtf("AUTH", "Unable to login");

                                }
                            });
                        }catch (NullPointerException t){
                            Log.wtf("NO", "Didn't work, most likely incorrect username+password");
                            t.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<MobileAuthUserJSON> call, Throwable t) {
                        Log.wtf("FAIL",""+t.getMessage());
                    }
                });
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void openMainActivity(){
        // Start the new activity, with no animation
        Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}

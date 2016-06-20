package com.jiaohuan.jiaohuan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainFragment extends android.support.v4.app.Fragment {

    // GitHub does work!

    private Button mGET;
    private TextView mTextView;

    private Button mPOST;
    RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mGET = (Button) view.findViewById(R.id.network);
        mPOST = (Button) view.findViewById(R.id.post);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());

        final String url = "http://nutshoo.com/users/json/";

        mGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("JSON","Start" + ": " + url);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray jsonArray = response.getJSONArray("Users");

                                    for(int i = 0; i < jsonArray.length(); i++){
                                        JSONObject user = jsonArray.getJSONObject(i);

                                        String username = user.getString("username");


                                        Log.wtf("JSON", username );
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLEY", "ERROR");

                            }
                        });
                mRequestQueue.add(jsObjRequest);
            }
        });




        return view;
    }

}

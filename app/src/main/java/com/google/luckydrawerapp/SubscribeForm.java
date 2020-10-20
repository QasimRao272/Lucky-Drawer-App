package com.google.luckydrawerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SubscribeForm extends AppCompatActivity {

    private Button btn_subscribe;
    private EditText subscriber_form_name, subscriber_form_email, subscriber_form_telephone,
            subscriber_form_social_link, subscriber_form_address, subscriber_form_township_city,
            subscriber_form_division_state, subscriber_form_id;
    String MY_PREFS_NAME = "Lucky_Draw";

    String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_form);

        initialization();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = prefs.getString("token", "No name defined");
        Log.d("token_num", "onResponse: " + ACCESS_TOKEN);

        btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe_form_Response();
            }
        });


    }

    private void subscribe_form_Response() {

        String YourUrl = "http://mmrandom.com/api/itemsub";
        StringRequest request = new StringRequest(Request.Method.POST, YourUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        Log.d("ksnsknnxkn", "onResponse: " + obj);
                        String id = obj.getJSONObject("subscribers").getString("id");
                        String user_name = obj.getJSONObject("subscribers").getString("user_name");
                        String email = obj.getJSONObject("subscribers").getString("email");
                        String phone_number = obj.getJSONObject("subscribers").getString("phone_number");
                        String social_link_to_contact = obj.getJSONObject("subscribers").getString("social_link_to_contact");
                        String address = obj.getJSONObject("subscribers").getString("address");

                        Toast.makeText(SubscribeForm.this, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Exception", "onResponse: " + e.getMessage());
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "" + error);
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Content-Type", "application/json; charset=UTF-8");
                //params.put("access_token", ACCESS_TOKEN);


                params.put("Accept","application/json");
                params.put("Content-Type","application/json");


                params.put("Authorization", "Bearer " + ACCESS_TOKEN);
                return params;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                 params.put("item_id", subscriber_form_id.getText().toString().trim());
                params.put("user_name", subscriber_form_name.getText().toString().trim());
                params.put("phone_number", subscriber_form_telephone.getText().toString().trim());
                //params.put("email", subscriber_form_email.getText().toString().trim());
                params.put("social_link_to_contact", subscriber_form_social_link.getText().toString().trim());
                //params.put("address", subscriber_form_address.getText().toString().trim());
                //params.put("township_or_city", subscriber_form_township_city.getText().toString().trim());
                //params.put("division_or_state", subscriber_form_division_state.getText().toString().trim());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void initialization() {

        subscriber_form_id = (EditText) findViewById(R.id.subscriber_form_id);
        subscriber_form_name = (EditText) findViewById(R.id.subscriber_form_name);
        subscriber_form_email = (EditText) findViewById(R.id.subscriber_form_email);
        subscriber_form_telephone = (EditText) findViewById(R.id.subscriber_form_telephone);
        subscriber_form_social_link = (EditText) findViewById(R.id.subscriber_form_social_link);
        subscriber_form_address = (EditText) findViewById(R.id.subscriber_form_address);
        subscriber_form_township_city = (EditText) findViewById(R.id.subscriber_form_township_city);
        subscriber_form_division_state = (EditText) findViewById(R.id.subscriber_form_division_state);

        btn_subscribe = (Button) findViewById(R.id.btn_subscribe);

    }


}


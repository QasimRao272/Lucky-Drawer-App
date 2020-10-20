package com.google.luckydrawerapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.luckydrawerapp.CustomDataModel;
import com.google.luckydrawerapp.LoginActivity;
import com.google.luckydrawerapp.R;
import com.google.luckydrawerapp.SubscribeForm;
import com.google.luckydrawerapp.SubscriberList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHome extends Fragment {
    String MY_PREFS_NAME = "Lucky_Draw";

    private Button screen_1_btn_subs_list, screen3_btn_subs_list, screen2_btn_subs_list;
    private Button screen_1_btn_subscriber, screen2_btn_subscriber, screen3_btn_subscriber;
    //screen 1
    private TextView screen_1_tv_phone, screen_1_text_View;
    private ImageView screen_1_img_screen1;
    //screen 2
    private TextView screen2_tv_phone, screen2_text_View;
    private ImageView screen2_img_screen1;
    //screen 3

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        response();

        //screen 1
        screen_1_btn_subs_list = (Button) view.findViewById(R.id.screen_1_btn_subs_list);
        screen_1_btn_subscriber = (Button) view.findViewById(R.id.screen_1_btn_subscriber);
        screen_1_tv_phone = (TextView) view.findViewById(R.id.screen_1_tv_phone);
        screen_1_text_View = (TextView) view.findViewById(R.id.screen_1_text_View);
        screen_1_img_screen1 = (ImageView) view.findViewById(R.id.screen_1_img_screen1);


        //screen 2
        screen2_btn_subs_list = (Button) view.findViewById(R.id.screen2_btn_subs_list);
        screen2_btn_subscriber = (Button) view.findViewById(R.id.screen2_btn_subscriber);
        screen2_tv_phone = (TextView) view.findViewById(R.id.screen2_tv_phone);
        screen2_text_View = (TextView) view.findViewById(R.id.screen2_text_View);
        screen2_img_screen1 = (ImageView) view.findViewById(R.id.screen2_img_screen1);

        //screen3
        screen3_btn_subscriber = (Button) view.findViewById(R.id.screen3_btn_subscriber);
        screen3_btn_subs_list = (Button) view.findViewById(R.id.screen3_btn_subs_list);


        screen_1_btn_subs_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscriberList.class);
                startActivity(intent);
            }
        });
        screen2_btn_subs_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscriberList.class);
                startActivity(intent);
            }
        });
        screen3_btn_subs_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscriberList.class);
                startActivity(intent);
            }
        });

        screen_1_btn_subscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscribeForm.class);
                startActivity(intent);
            }
        });

        screen2_btn_subscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscribeForm.class);
                startActivity(intent);
            }
        });
        screen3_btn_subscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SubscribeForm.class);
                startActivity(intent);
            }
        });

        return view;
        }

    public void response() {

        String YourUrl = "http://mmrandom.com/api/itemlist";
        StringRequest request = new StringRequest(Request.Method.GET, YourUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(getContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("jjjjjssssoooonnnn", "onResponse: " + jsonArray.getJSONObject(i));

                        /*CustomDataModel customDataModel = new CustomDataModel();
                        customDataModel.setId(jsonArray.getString(i));
                        customDataModel.setItem_name(jsonArray.getString(i));
                        customDataModel.setDescription(jsonArray.getString(i));
                        customDataModel.setImages(jsonArray.getString(i));
                        customDataModel.setCount(jsonArray.getString(i));
                        customDataModel.setSubscribe_count(jsonArray.getString(i));*/
                    }

                    // Data One
                    String id1 = jsonArray.getJSONObject(0).getString("id");
                    String item_name1 = jsonArray.getJSONObject(0).getString("item_name");
                    String description1 = jsonArray.getJSONObject(0).getString("description");
                    String images1 = jsonArray.getJSONObject(0).getString("images");
                    String count1 = jsonArray.getJSONObject(0).getString("count");
                    String subscribe_count1 = jsonArray.getJSONObject(0).getString("subscribe_count");
                    Log.d("json_array", "ID : " + id1);
                    Log.d("json_array", "Item Name: " + item_name1);
                    Log.d("json_array", "Description : " + description1);
                    Log.d("json_array", "Images : " + images1);
                    Log.d("json_array", "Count : " + count1);
                    Log.d("json_array", "Subscribe_count : " + subscribe_count1);

                    //screen_1_tv_phone.setText(description1);
                    //screen_1_text_View.setText(description1);

                    // Data Two
                    String id2 = jsonArray.getJSONObject(1).getString("id");
                    String item_name2 = jsonArray.getJSONObject(1).getString("item_name");
                    String description2 = jsonArray.getJSONObject(1).getString("description");
                    String images2 = jsonArray.getJSONObject(1).getString("images");
                    String count2 = jsonArray.getJSONObject(1).getString("count");
                    String subscribe_count2 = jsonArray.getJSONObject(1).getString("subscribe_count");
                    Log.d("json_array", "ID : " + id2);
                    Log.d("json_array", "Item Name: " + item_name2);
                    Log.d("json_array", "Description : " + description2);
                    Log.d("json_array", "Images : " + images2);
                    Log.d("json_array", "Count : " + count2);
                    Log.d("json_array", "Subscribe_count : " + subscribe_count2);

                    //screen2_tv_phone.setText(description2);

                    // Data Three
                    String id3 = jsonArray.getJSONObject(2).getString("id");
                    String item_name3 = jsonArray.getJSONObject(2).getString("item_name");
                    String description3 = jsonArray.getJSONObject(2).getString("description");
                    String images3 = jsonArray.getJSONObject(2).getString("images");
                    String count3 = jsonArray.getJSONObject(2).getString("count");
                    String subscribe_count3 = jsonArray.getJSONObject(2).getString("subscribe_count");
                    Log.d("json_array", "ID : " + id3);
                    Log.d("json_array", "Item Name: " + item_name3);
                    Log.d("json_array", "Description : " + description3);
                    Log.d("json_array", "Images : " + images3);
                    Log.d("json_array", "Count : " + count3);
                    Log.d("json_array", "Subscribe_count : " + subscribe_count3);

                    // Data Four
                    String id4 = jsonArray.getJSONObject(3).getString("id");
                    String item_name4 = jsonArray.getJSONObject(3).getString("item_name");
                    String description4 = jsonArray.getJSONObject(3).getString("description");
                    String images4 = jsonArray.getJSONObject(3).getString("images");
                    String count4 = jsonArray.getJSONObject(3).getString("count");
                    String subscribe_count4 = jsonArray.getJSONObject(3).getString("subscribe_count");
                    Log.d("json_array", "ID : " + id4);
                    Log.d("json_array", "Item Name: " + item_name4);
                    Log.d("json_array", "Description : " + description4);
                    Log.d("json_array", "Images : " + images4);
                    Log.d("json_array", "Count : " + count4);
                    Log.d("json_array", "Subscribe_count : " + subscribe_count4);

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Server Error\n" + e, Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onResponse: " + e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "" + error);
            }
        }) {

            SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String ACCESS_TOKEN = prefs.getString("token", "No name defined");

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + ACCESS_TOKEN);
                Log.d("Token_Token", "getHeaders: " + ACCESS_TOKEN);
                return params;
            }

            //Pass Your Parameters here
            /*@Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", login_user_email.getText().toString().trim());
                params.put("password", login_user_password.getText().toString().trim());
                return params;
            }*/

        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
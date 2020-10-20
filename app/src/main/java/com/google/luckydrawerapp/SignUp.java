package com.google.luckydrawerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SignUp extends AppCompatActivity {

    private EditText register_user_name, register_user_email, register_user_password, register_user_confirm_password;
    private Button btn_register_user;
    String MY_PREFS_NAME = "Lucky_Draw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Register Fields
        register_user_name = (EditText) findViewById(R.id.register_user_name);
        register_user_email = (EditText) findViewById(R.id.register_user_email);
        register_user_password = (EditText) findViewById(R.id.register_user_password);
        register_user_confirm_password = (EditText) findViewById(R.id.register_user_confirm_password);

        btn_register_user = (Button) findViewById(R.id.btn_register_user);

        btn_register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regiterResponse();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("done", "yes");
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }

    public void regiterResponse() {
        String YourUrl = "http://mmrandom.com/api/register";
        StringRequest request = new StringRequest(Request.Method.POST, YourUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Toast.makeText(SignUp.this, "Response " + response, Toast.LENGTH_SHORT).show();
                    Log.e("check", "Success");

                    try {
                        JSONObject obj = new JSONObject(response);
                        String id = obj.getJSONObject("user").getString("id");
                        String name = obj.getJSONObject("user").getString("name");
                        String email = obj.getJSONObject("user").getString("email");
                        String token = String.valueOf(obj.getString("access_token"));
                        Toast.makeText(getApplicationContext(), "ID: " + id, Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onResponse: " + token);
                        Log.d("TAG", "onResponse: " + name);
                        Log.d("TAG", "onResponse: " + email);
                        Log.d("TAG", "onResponse: " + id);

                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("id", id);
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.putString("token", token);
                        editor.apply();
                        Toast.makeText(SignUp.this, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("Your Array Response", "Data Null");
                }

                try {
                    JSONObject objres = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), objres.toString(), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Server Error" + e, Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onResponse: " + e);

                }
                Log.d("VOLLEY1", response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "" + error);
            }
        }) {

    /*        //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                //params.put("access_token", ACCESS_TOKEN);
                return params;
            }*/

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", register_user_name.getText().toString().trim());
                params.put("email", register_user_email.getText().toString().trim());
                params.put("password", register_user_password.getText().toString().trim());
                params.put("password_confirmation", register_user_confirm_password.getText().toString().trim());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}

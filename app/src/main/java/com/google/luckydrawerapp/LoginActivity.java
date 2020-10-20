package com.google.luckydrawerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    private EditText login_user_email, login_user_password;
    private EditText register_user_name, register_user_email, register_user_password, register_user_confirm_password;

    private Button btn_register_user, btn_login_user, btn_sign_up_user;

    String log_password, log_user_email;
    String MY_PREFS_NAME = "Lucky_Draw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("done", "No name defined");
        if (!TextUtils.isEmpty(name) && name.equals("yes")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("done", "yes");
            editor.apply();
        }

        login_user_email = (EditText) findViewById(R.id.login_user_email);
        login_user_password = (EditText) findViewById(R.id.login_user_password);


        btn_login_user = (Button) findViewById(R.id.btn_login_user);
        btn_sign_up_user = (Button) findViewById(R.id.btn_sign_up_user);

        log_user_email = login_user_email.getText().toString().trim();
        log_password = login_user_password.getText().toString().trim();

        btn_login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginResponse();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_sign_up_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loginResponse() {
        String YourUrl = "http://mmrandom.com/api/login";
        StringRequest request = new StringRequest(Request.Method.POST, YourUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Toast.makeText(LoginActivity.this, "Response " + response, Toast.LENGTH_SHORT).show();
                    Log.e("check", "" + response);
                } else {
                    Log.e("Your Array Response", "Data Null");
                }

                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.toString(), Toast.LENGTH_LONG).show();
                    ///
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
                params.put("email", login_user_email.getText().toString().trim());
                params.put("password", login_user_password.getText().toString().trim());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
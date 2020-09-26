package com.google.luckydrawerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class RegisterLogin extends AppCompatActivity {

    private LinearLayout ll_login_user;
    private LinearLayout ll_register_user;

    private EditText login_user_email, login_user_password;
    private EditText register_user_name, register_user_email, register_user_password, register_user_confirm_password;

    private Button btn_register_user, login_user;

    String user_name, password, user_email, confirm_pass;
    String ACCESS_TOKEN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        ll_login_user = (LinearLayout) findViewById(R.id.ll_login_user);
        ll_register_user = (LinearLayout) findViewById(R.id.ll_register_user);

        //login Fields
        login_user_email = (EditText) findViewById(R.id.login_user_email);
        login_user_password = (EditText) findViewById(R.id.login_user_password);

        //Register Fields
        register_user_name = (EditText) findViewById(R.id.register_user_name);
        register_user_email = (EditText) findViewById(R.id.register_user_email);
        register_user_password = (EditText) findViewById(R.id.register_user_password);
        register_user_confirm_password = (EditText) findViewById(R.id.register_user_confirm_password);

        btn_register_user = (Button) findViewById(R.id.btn_register_user);
        login_user = (Button) findViewById(R.id.login_user);

        user_name = register_user_name.getText().toString().trim();
        user_email = register_user_email.getText().toString().trim();
        password = register_user_password.getText().toString().trim();
        confirm_pass = register_user_confirm_password.getText().toString().trim();


        btn_register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                response();

            }
        });
    }

    public void response() {
        String YourUrl = "https://mmrandom.com/api/register";
        StringRequest request = new StringRequest(Request.Method.POST, YourUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Toast.makeText(RegisterLogin.this, "Response " + response, Toast.LENGTH_SHORT).show();
                    Log.e("check", "Success");

                } else {
                    Log.e("Your Array Response", "Data Null");
                }

                try {
                    JSONObject objres = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), objres.toString(), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Server Error" + e, Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onResponse: "+e);

                }
                Log.d("VOLLEY1", response);
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
                params.put("Content-Type", "application/json; charset=UTF-8");
                //params.put("access_token", ACCESS_TOKEN);
                return params;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", user_name);
                params.put("email", user_email);
                params.put("password", password);
                params.put("password_confirmation", confirm_pass);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
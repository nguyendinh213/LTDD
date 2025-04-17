package com.example.a21110939.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a21110939.R;
import com.example.a21110939.api.VolleySingle;
import com.example.a21110939.internalData.SharedPrefManager;
import com.example.a21110939.model.User;
import com.example.a21110939.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        progressBar = findViewById(R.id.progressBar);
        etEmail = findViewById(R.id.emailTxt);
        etPassword = findViewById(R.id.passwordTxt);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    public void userLogin() {
        progressBar.setVisibility(View.VISIBLE);
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String requestBody = jsonBody.toString();

        if(TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter your email!");
            etEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password!");
            etPassword.requestFocus();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                performLoginRequest(requestBody);
            }
        }).start();
    }

    private void performLoginRequest(String requestBody) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("status", "success");
                    JSONObject obj = new JSONObject(response);
                    if (!obj.getBoolean("error")) {
                        User user = new User(
                                obj.getInt("id"),
                                obj.getString("firstName"),
                                obj.getString("lastName"),
                                obj.getString("email"),
                                obj.getString("avatar"),
                                obj.getInt("gender")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("status", "fail");
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}
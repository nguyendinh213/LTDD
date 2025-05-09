package com.ldt.volley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        } else {
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUsername);
            userEmail = findViewById(R.id.textViewEmail);
            gender = findViewById(R.id.textViewGender);
            btnLogout = findViewById(R.id.btnLogout);
            imageViewprofile = findViewById(R.id.profile_image);
            User user = SharedPrefManager.getInstance(this).getUser();
            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            userName.setText(user.getName());
            Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewprofile);
            btnLogout.setOnClickListener(this);
        }
    }
    public void onClick(View view) {
        if (view.equals(btnLogout)) {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
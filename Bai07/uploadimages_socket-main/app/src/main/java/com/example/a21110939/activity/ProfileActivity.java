package com.example.a21110939.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a21110939.R;
import com.example.a21110939.internalData.SharedPrefManager;
import com.example.a21110939.model.User;
import com.example.a21110939.util.Constant;

public class ProfileActivity extends AppCompatActivity {
    TextView id, firstName, lastName, email, gender;
    Button btnLoggout, btnCategory;
    ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnCategory = findViewById(R.id.btn_category);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            id = findViewById(R.id.tv_userid_value);
            firstName = findViewById(R.id.tv_firstName_value);
            lastName = findViewById(R.id.tv_lastName_value);
            email = findViewById(R.id.tv_email_value);
            gender = findViewById(R.id.tv_gender_value);
            btnLoggout = findViewById(R.id.button_logout);
            imageViewProfile = findViewById(R.id.circleImageView);
            User user = SharedPrefManager.getInstance(this).getUser();
            String url = user.getAvatar();
            if(url == null) {
                url = Constant.DEFAULT_AVATAR;
            }
            Glide.with(getApplicationContext()).load(url).into(imageViewProfile);
            id.setText(String.valueOf(user.getId()));
            firstName.setText(String.valueOf(user.getFirstName()));
            lastName.setText(String.valueOf(user.getLastName()));
            String genderText = "Nữ";
            if(user.getGender() == 1) {
                genderText = "Nam";
            }
            gender.setText(String.valueOf(genderText));
            email.setText(String.valueOf(user.getEmail()));
            btnLoggout.setOnClickListener(this::onClick);

            imageViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfileActivity.this, ChooseAvatarActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Log.d("status", "transfer");
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }

    private void onClick(View view) {
        if (view.equals(btnLoggout)) {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}


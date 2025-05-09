package com.ldt.bai6tuan7_sharedpreferences;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            //xoa du lieu dang nhap trong sharepreferences
            getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().clear().apply();
            finish();
        });
    }
}
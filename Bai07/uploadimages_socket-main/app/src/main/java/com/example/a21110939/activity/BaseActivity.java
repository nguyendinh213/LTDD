package com.example.a21110939.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.a21110939.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BaseActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base2);
        FrameLayout container = findViewById(R.id.frame_layout);
        getLayoutInflater().inflate(layoutResID, container, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
        Log.d("Activity", "BaseActivity: onCreate: started.");

    }

    protected void initializeBottomBar() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setClickable(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == bottomNavigationView.getSelectedItemId()) {
                    return true;
                }
                Log.d("Navbar", "onNavigationItemSelected: ID = " + id );
                if (id == R.id.home) {
                    Intent intent = new Intent(BaseActivity.this , CategoryActivity.class);
                    intent.putExtra("SELECTED_MENU_ITEM_ID", id);
                    startActivity(intent);
                } else if (id == R.id.profile) {
                    Intent intent = new Intent(BaseActivity.this , ProfileActivity.class);
                    startActivity(intent);
                } else if (id == R.id.cart) {
                    Intent intent = new Intent(BaseActivity.this , CartDetailActivity.class);
                    intent.putExtra("SELECTED_MENU_ITEM_ID", id);
                    startActivity(intent);
                } else if (id == R.id.help) {
                    // Handle help click
                } else if (id == R.id.setting) {
                    // Handle setting click
                }

                return true;
            }

        });
    }
}
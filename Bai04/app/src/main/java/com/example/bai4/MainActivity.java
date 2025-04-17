package com.example.bai4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnListView = findViewById(R.id.btnListView);
        Button btnGridView = findViewById(R.id.btnGridView);
        Button btnRecyclerView = findViewById(R.id.btnRecyclerView);

        btnListView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListViewActivity.class)));
        btnGridView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GridViewActivity.class)));
        btnRecyclerView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class)));
    }
}

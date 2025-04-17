package com.example.bai4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.example.bai4.adapter.RecyclerViewAdapter;
import com.example.bai4.model.ItemModel;

public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel("Item 1", R.drawable.ic_launcher_foreground));
        items.add(new ItemModel("Item 2", R.drawable.ic_launcher_foreground));
        items.add(new ItemModel("Item 3", R.drawable.ic_launcher_foreground));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}

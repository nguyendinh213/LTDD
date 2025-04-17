package com.example.bai4;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.bai4.adapter.GridViewAdapter;
import com.example.bai4.model.ItemModel;

public class GridViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        GridView gridView = findViewById(R.id.gridView);
        ArrayList<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel("Item 1", R.drawable.ic_launcher_foreground));
        items.add(new ItemModel("Item 2", R.drawable.ic_launcher_foreground));
        items.add(new ItemModel("Item 3", R.drawable.ic_launcher_foreground));

        GridViewAdapter adapter = new GridViewAdapter(this, items);
        gridView.setAdapter(adapter);
    }
}

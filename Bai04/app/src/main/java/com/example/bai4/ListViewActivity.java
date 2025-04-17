package com.example.bai4;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.bai4.adapter.ListViewAdapter;
import com.example.bai4.model.ItemModel;

public class ListViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = findViewById(R.id.listView);
        ArrayList<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel("Item 1", R.drawable.ic_launcher_foreground));
        items.add(new ItemModel("Item 2", R.drawable.ic_launcher_foreground));

        ListViewAdapter adapter = new ListViewAdapter(this, items);
        listView.setAdapter(adapter);
    }
}

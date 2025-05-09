package com.ldt.a22110442bai5_animetrongrcv;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_animation extends AppCompatActivity {
    private Button btnAddItem;
    private RecyclerView rvItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_animation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAddItem=findViewById(R.id.btn_add_item);
        rvItem=findViewById(R.id.rv_items);
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Item "+i);
        }
        final CustomAnimationAdapter adapter=new CustomAnimationAdapter(data);
        rvItem.setAdapter(adapter);
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        rvItem.setItemAnimator(new DefaultItemAnimator());
        btnAddItem.setOnClickListener(v -> {
            adapter.addItem("New Item");
            rvItem.smoothScrollToPosition(adapter.getItemCount()-1);
        });
    }
}
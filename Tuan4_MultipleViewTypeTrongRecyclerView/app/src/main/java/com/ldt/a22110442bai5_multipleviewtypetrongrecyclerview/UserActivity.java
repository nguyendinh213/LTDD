package com.ldt.a22110442bai5_multipleviewtypetrongrecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";
    private RecyclerView rvMultipleViewType;
    private List<Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvMultipleViewType = (RecyclerView) findViewById(R.id.rv_multiple_view_type);
        mData = new ArrayList<>();
        mData.add(new UserModel("Nguyễn Văn A", "Ho Chi Minh"));
        mData.add(R.drawable.avatar_1);
        mData.add("Text 0");
        mData.add("Text 1");
        mData.add(new UserModel("Nguyễn Van B", "Ha Noi"));
        mData.add("Text 2");
        mData.add(R.drawable.avatar_2);
        mData.add(R.drawable.avatar_3);
        mData.add((new UserModel("Nguyễn Van C", "Da Nang")));
        mData.add("Text 3");
        mData.add("Text 4");
        CustomAdapter adapter = new CustomAdapter(this,mData);
        rvMultipleViewType.setAdapter(adapter);
        rvMultipleViewType.setLayoutManager(new LinearLayoutManager(this));



    }
}
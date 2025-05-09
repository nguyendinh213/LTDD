package com.ldt.a22110442bai5_listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity_CustomAdapterListView extends AppCompatActivity {
    ArrayList<MonHoc> arrayList;
    //khai báo
    ListView listView;
    //tao adapter
    MonhocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customlistview);
        listView = findViewById(R.id.listview);
        AnhXa();
//Tạo Adapter
        adapter = new MonhocAdapter(MainActivity_CustomAdapterListView.this,
                R.layout.row_monhoc,
                arrayList
        );
//truyền dữ liệu từ adapter ra listview
        listView.setAdapter(adapter);
    }
    private void AnhXa() {
//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("PHP", "PHP 1", R.drawable.dart));
        arrayList.add(new MonHoc("Kotlin", "Kotlin 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart", "Dart 1", R.drawable.dart));
        arrayList.add(new MonHoc("Java", "Java 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.kotlin));
    }
}

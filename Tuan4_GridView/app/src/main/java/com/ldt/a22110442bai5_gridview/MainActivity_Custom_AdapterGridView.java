package com.ldt.a22110442bai5_gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity_Custom_AdapterGridView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom_adapter_grid_view);

//        GridView gridView;
//        ArrayList<String> arrayList;
////ánh xạ
//        gridView = (GridView) findViewById(R.id.gridview1);
////Thêm dữ liệu vào List
//        arrayList = new ArrayList<>();
//        arrayList.add("Java");
//        arrayList.add("C#");
//        arrayList.add("PHP");
//        arrayList.add("Kotlin");
//        arrayList.add("Dart");
//        ArrayAdapter adapter = new ArrayAdapter(
//                MainActivity_Custom_AdapterGridView.this,
//                android.R.layout.simple_list_item_1,
//                arrayList
//        );
//        gridView.setAdapter(adapter);
        GridView gridView= (GridView) findViewById(R.id.gridview1);

        MonhocAdapter adapter;
//ánh xạ
        AnhXa();
//Tạo Adapter
        adapter = new MonhocAdapter(MainActivity_Custom_AdapterGridView.this,
                R.layout.row_monhoc,
                arrayList
        );
//truyền dữ liệu từ adapter ra listview
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonHoc monHoc = arrayList.get(position);
                Toast.makeText(MainActivity_Custom_AdapterGridView.this, monHoc.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    ArrayList<MonHoc> arrayList;

    private void AnhXa() {
//        gridView = (GridView) findViewById(R.id.gridview1);
//        editText1 = (EditText) findViewById(R.id.editText1);
//        btnNhap = (Button) findViewById(R.id.btnNhap);
//        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java", "Java 1", R.drawable.java1));
        arrayList.add(new MonHoc("C#", "C# 1", R.drawable.c));
        arrayList.add(new MonHoc("PHP", "PHP 1", R.drawable.php));
        arrayList.add(new MonHoc("Kotlin", "Kotlin 1", R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart", "Dart 1", R.drawable.dart));
    }
}
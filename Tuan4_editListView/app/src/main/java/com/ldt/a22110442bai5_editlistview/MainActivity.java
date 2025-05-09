package com.ldt.a22110442bai5_editlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView;
        ArrayList<String> arrayList;
        //ánh xạ
        listView = (ListView) findViewById(R.id.listview1);
//Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C#");
        arrayList.add("PHP");
        arrayList.add("Kotlin");
        arrayList.add("Dart");
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        listView.setAdapter(adapter);
        //bắt sự kiện click nhanh trên từng dòng của  Listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //code yêu cầu
//                //i: trả về vị trí click chuột trên ListView -> i ban đầu = 0
//                Toast.makeText(MainActivity.this,
//                        "" + arrayList.get(i),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        //bắt sự kiện click giữ trên từng dòng của Listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean
            onItemLongClick(AdapterView<?> adapterView,
                            View view, int i, long l) {
//code yêu cầu
//i: trả về vị trí click chuột trên ListView -> i                ban đầu = 0
                Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ " + i + " - " + arrayList.get(i),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        them du lieu vao listview
        EditText editText1;
        Button btnNhap;
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                arrayList.add(name);
                adapter.notifyDataSetChanged();
            }
        });
        Button btnCapNhat;

        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);

        //bắt sự kiện trên từng dòng của Listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //lấy nội dung đua lên edittext
                editText1.setText(arrayList.get(i));
                vitri = i;
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.set(vitri, editText1.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        //Xóa item
        Button btnDelete= (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(vitri);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
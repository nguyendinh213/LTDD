package com.ldt.bai6tuan7_sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        InitDatabaseSQLite();
        createDatabaseSQLite();
//        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_notes, arrayList);
        listView.setAdapter(adapter);
        databaseSQLite();

    }

    private void createDatabaseSQLite() {
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, ' Ví du SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, ' Vi du SQLite 2')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, ' Vi du SQLite 3')");
        //remove all
//        databaseHandler.QueryData("DELETE FROM Notes");

    }

    private void InitDatabaseSQLite() {
//khoi tạo database
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);
//tạo bảng Notes
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddNotes) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);
//ánh xa trong dialog
        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonThem);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);
//bắt sự kien cho nút thêm và huy
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui Long nhap ten Notes", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + name + "')");
                    Toast.makeText(MainActivity.this, "Da them Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();//gọi hàm load lại dũ Liệu

                }

            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void databaseSQLite() {
//Lấy dữ Liệu
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        arrayList.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int id = cursor.getInt(0);
            arrayList.add(new NotesModel(id, name));
//            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogCapNhat(String ten, int id) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_notes);
        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonEdit = dialog.findViewById(R.id.buttonEdit);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);
        editText.setText(ten);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                databaseHandler.QueryData("UPDATE Notes SET NameNotes = '" + name + "' WHERE Id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Da cap nhat", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                databaseSQLite();
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //hàm dialog xóa
    public void DialogDelete(String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ban co muon xoa Notes " + name + " nay khong ?");
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Da xoa Notes " + name + " thanh cong", Toast.LENGTH_SHORT).show();
                databaseSQLite();//gọi hàm load Lại dũ Liệu

            }
        });

        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}
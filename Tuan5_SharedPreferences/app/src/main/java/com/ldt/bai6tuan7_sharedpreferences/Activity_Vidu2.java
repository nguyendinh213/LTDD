package com.ldt.bai6tuan7_sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Vidu2 extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private CheckBox CheckBox;
    private Button button;
    public static final String MyPREFERENCES = "MyPrefs";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.ps);
        button = findViewById(R.id.button);
        CheckBox = findViewById(R.id.checkBox);
        loadData();
        //neu da luu thi vao home
        if (sharedpreferences.getBoolean("status", false)) {
            Intent intent = new Intent(Activity_Vidu2.this, HomeActivity.class);
            startActivity(intent);
            return;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nếu người dùng có chọn ghi nhớ
                if (CheckBox.isChecked())
                    //lưu lại thông tin đăng nhập
                    saveData(mEmail.getText().toString(), mPassword.getText().toString());
                else
                    clearData();//xóa thông tin đã lưu
                //nếu thông tin đăng nhập đúng thì đến màng hình home
                if (mEmail.getText().toString().equals("a@123") && mPassword.getText().toString().equals("1234")) {
                    Intent intent = new Intent(Activity_Vidu2.this, HomeActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(Activity_Vidu2.this, "Thông tin đăng nhập không đúng", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        if (sharedpreferences.getBoolean("status", false)) {
            mEmail.setText(sharedpreferences.getString("email", ""));
            mPassword.setText(sharedpreferences.getString("pass", ""));
            CheckBox.setChecked(true);
        } else
            CheckBox.setChecked(false);

    }

    private void saveData(String email, String pass) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("email", email);
        editor.putString("pass", pass);
        editor.putBoolean("status", CheckBox.isChecked());
        editor.commit();
    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

}
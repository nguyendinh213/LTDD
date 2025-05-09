package com.ldt.bai6tuan7_sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Vidu1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vidu1);
        //anh xa
        EditText user = findViewById(R.id.user);
        EditText ps = findViewById(R.id.ps);
        CheckBox cb = findViewById(R.id.checkBox);
        Button btn = findViewById(R.id.button);

        SharedPreferences sharePreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        user.setText(sharePreferences.getString("username",""));
        ps.setText(sharePreferences.getString("password",""));
        cb.setChecked(sharePreferences.getBoolean("status",false));
        btn.setOnClickListener(v -> {
                    String username = user.getText().toString().trim();
                    String password = ps.getText().toString().trim();
                    if (username.equals("abc") && password.equals("123")) {
                        Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        if (cb.isChecked()) {
                            SharedPreferences.Editor editor = sharePreferences.edit();
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putBoolean("status", true);
                            editor.commit();

                        } else {
                            SharedPreferences.Editor editor = sharePreferences.edit();
                            editor.remove("username");
                            editor.remove("password");
                            editor.remove("status");
                            editor.commit();
                            //clearfields
                            user.setText("");
                            ps.setText("");
                            cb.setChecked(false);
                        }
                        startActivity((new Intent(Activity_Vidu1.this, HomeActivity.class)));

                    } else {
                        Toast.makeText(Activity_Vidu1.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                }
        );



    }
}
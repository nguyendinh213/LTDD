package com.ldt.ledinhtri22110442_bt4_bai2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView) findViewById(R.id.acc);
        imageView.setImageDrawable(getDrawable(R.drawable.pro_trung));
    }


}
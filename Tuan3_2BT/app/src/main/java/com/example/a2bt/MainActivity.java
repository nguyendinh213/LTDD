package com.example.a2bt;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLayout;
    private Switch switchChangeBackground;
    private final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        mainLayout = findViewById(R.id.mainLayout);
        switchChangeBackground = findViewById(R.id.switchChangeBackground);

        // 1. Đổi màu nền mỗi khi mở app
        changeBackgroundRandom();

        // 2. Thay đổi hình nền khi bấm vào Switch
        switchChangeBackground.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mainLayout.setBackgroundColor(Color.BLACK);
            } else {
                changeBackgroundRandom();
            }
        });
    }

    // Hàm đổi hình nền ngẫu nhiên
    private void changeBackgroundRandom() {
        Random random = new Random();
        int colorIndex = random.nextInt(colors.length);
        mainLayout.setBackgroundColor(colors[colorIndex]);
    }
}

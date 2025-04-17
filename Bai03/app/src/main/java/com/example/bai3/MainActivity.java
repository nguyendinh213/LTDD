package com.example.bai3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imageView;
    private RelativeLayout layout;
    private CheckBox checkBox;
    private Switch wifiSwitch;
    private RadioGroup radioGroup;
    private Button randomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        imageView = findViewById(R.id.imageView);
        layout = findViewById(R.id.layout);
        checkBox = findViewById(R.id.checkBox);
        wifiSwitch = findViewById(R.id.switchWiFi);
        radioGroup = findViewById(R.id.radioGroup);
        randomButton = findViewById(R.id.randomButton);

        String[] suggestions = {"Android", "Java", "Kotlin", "XML", "Jetpack"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        autoCompleteTextView.setAdapter(adapter);

        randomButton.setOnClickListener(v -> {
            int number = new Random().nextInt(100);
            textView.setText("Random Number: " + number);
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layout.setBackgroundColor(Color.GREEN);
            } else {
                layout.setBackgroundColor(Color.WHITE);
            }
        });

        wifiSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "WiFi Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "WiFi Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}

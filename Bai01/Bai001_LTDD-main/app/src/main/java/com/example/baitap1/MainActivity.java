package com.example.baitap1;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private Button processButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // thiết lập giao diện
        setContentView(R.layout.activity_main);

        //ánh xạ các view
        inputEditText = findViewById(R.id.inputEditText);
        processButton = findViewById(R.id.processButton);
        resultTextView = findViewById(R.id.resultTextView);

        // xử lí khi người dùng nhấn nút
        processButton.setOnClickListener(v -> processString());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        processArray();

    }
    private void processArray() {
        Random random = new Random();
        int n = 10; // Số lượng phần tử ngẫu nhiên
        ArrayList<Integer> numbers = new ArrayList<>();

        // Sinh ra các số ngẫu nhiên
        for (int i = 0; i < n; i++) {
            numbers.add(random.nextInt(100)); // Sinh số ngẫu nhiên từ 0 đến 99
        }

        // Tạo ArrayList để lưu trữ các số lẻ và số chẵn
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();

        // Phân loại các số lẻ và số chẵn
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            } else {
                oddNumbers.add(num);
            }
        }

        // In kết quả ra console (Logcat)
        Log.d("EvenNumbers", evenNumbers.toString());
        Log.d("OddNumbers", oddNumbers.toString());
    }
    private void processString() {
        // Lấy chuỗi từ EditText
        String inputString = inputEditText.getText().toString();

        // Đảo ngược chuỗi và chuyển thành chữ hoa
        String reversedString = reverseString(inputString);
        String upperCaseString = reversedString.toUpperCase();

        // Hiển thị kết quả lên TextView
        resultTextView.setText(upperCaseString);

        // Hiển thị kết quả qua Toast
        Toast.makeText(this, upperCaseString, Toast.LENGTH_SHORT).show();

        // In ra Logcat
        Log.d("ProcessedString", upperCaseString);
    }

    private String reverseString(String input) {
        String[] words = input.split(" "); // Tách chuỗi thành các từ
        StringBuilder reversedString = new StringBuilder();

        // Đảo ngược các từ trong chuỗi
        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]).append(" ");
        }

        // Trả về chuỗi đảo ngược
        return reversedString.toString().trim();
    }

}
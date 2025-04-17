package com.example.gk_ltdd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gk_ltdd.api.UserAPI;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtUsername, edtEmail, edtPassword, edtOTP;
    private ImageView imgAvatar;
    private Button btnRegister,activateAccount;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ UI
        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnRegister = findViewById(R.id.btnRegister);
        edtOTP = findViewById(R.id.edtOTP);
        activateAccount = findViewById(R.id.activateAccount);

        userAPI = new UserAPI(this);

        activateAccount.setOnClickListener(v -> activate_Account());
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void activate_Account() {
        String otpInput = edtOTP.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (otpInput.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Vui lòng nhập OTP!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gọi API để kích hoạt tài khoản
        boolean isActivated = userAPI.activateUser(email, otpInput);

        if (isActivated) {
            Toast.makeText(RegisterActivity.this, "Tài khoản đã kích hoạt thành công!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, "OTP không hợp lệ hoặc đã hết hạn!", Toast.LENGTH_SHORT).show();
        }
    }


    private void registerUser() {
        String name = edtName.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // Kiểm tra thông tin đầu vào
        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra định dạng email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sinh mã OTP ngẫu nhiên
        String otp = generateOTP();
        Toast.makeText(this, "Mã OTP: " + otp, Toast.LENGTH_SHORT).show();

        // Chuyển đổi ảnh thành Base64 (nếu có)
        String avatarBase64 = getAvatarBase64();

        // Gửi dữ liệu đăng ký
        boolean isSuccess = userAPI.register(name, username, email, password, avatarBase64, otp);
        if (isSuccess) {
            Toast.makeText(this, "Đăng ký thành công! Vui lòng nhập OTP để kích hoạt tài khoản.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Lỗi đăng ký, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }

    // Sinh mã OTP 6 số ngẫu nhiên
    private String generateOTP() {
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000); // Tạo số từ 100000 - 999999
        return String.valueOf(otpNumber);
    }

    // Chuyển đổi ảnh Bitmap thành chuỗi Base64
    private String getAvatarBase64() {
        Drawable drawable = imgAvatar.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return null;
    }
}

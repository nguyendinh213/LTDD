package com.example.gk_ltdd.api;

import android.content.Context;
import com.example.gk_ltdd.DatabaseHelper;
import com.example.gk_ltdd.model.User;

public class UserAPI {
    private DatabaseHelper db;

    public UserAPI(Context context) {
        db = new DatabaseHelper(context);
    }

    // Đăng ký tài khoản với đầy đủ thông tin
    public boolean register(String name, String username, String email, String password, String image, String otp) {
        return db.insertUser(name, username, email, password, image, otp);
    }

    // Đăng nhập: Kiểm tra username, password và trạng thái kích hoạt
    public User login(String username, String password) {
        User user = db.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password) && user.isActivated()) {
            return user;
        }
        return null;
    }

    // Kích hoạt tài khoản bằng OTP
    public boolean activateUser(String email, String otp) {
        boolean isValidOTP = db.verifyOTP(email, otp);
        if (isValidOTP) {
            return db.activateUser(email);  // Cập nhật trạng thái isActivated = true
        }
        return false;
    }
}

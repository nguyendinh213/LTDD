package com.example.retrofit_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // Biến static để lưu đối tượng Retrofit
    private static Retrofit retrofit;

    // Phương thức để lấy đối tượng Retrofit
    public static Retrofit getRetrofit() {
        // Nếu chưa khởi tạo, khởi tạo Retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.iotstar.vn:8081/appfoods/") // Đường dẫn API của bạn
                    .addConverterFactory(GsonConverterFactory.create()) // Chuyển đổi dữ liệu JSON thành đối tượng Java
                    .build(); // Xây dựng Retrofit
        }
        // Trả về đối tượng Retrofit đã khởi tạo
        return retrofit;
    }
}

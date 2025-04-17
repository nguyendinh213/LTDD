package com.example.a21110939.api;

import com.example.a21110939.adapter.FoodReview;
import com.example.a21110939.model.Category;
import com.example.a21110939.model.FoodDetailResponse;
import com.example.a21110939.model.User;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoriesAll();

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<FoodReview>> getFoodCategory(@Field("idcategory") int idcategory);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<FoodDetailResponse> getFoodDetail(@Field("id") int id);
}

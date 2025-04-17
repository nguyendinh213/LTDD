package com.example.a21110939.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a21110939.Decoration.SpacesItemDecoration;
import com.example.a21110939.R;
import com.example.a21110939.adapter.FoodListAdapter;
import com.example.a21110939.adapter.FoodReview;
import com.example.a21110939.api.APIService;
import com.example.a21110939.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListActivity extends BaseActivity {

    RecyclerView rcFoodList;
    TextView cateName;
    List<FoodReview> foodList;
    APIService apiService;
    FoodListAdapter foodListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        initializeBottomBar();
        rcFoodList = findViewById(R.id.rc_food_list);
        cateName = findViewById(R.id.cate_name);

        foodList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rcFoodList.setLayoutManager(layoutManager);
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.margin_15);
        rcFoodList.addItemDecoration(new SpacesItemDecoration(spaceInPixels));
        rcFoodList.setHasFixedSize(true);
        foodListAdapter = new FoodListAdapter(FoodListActivity.this, foodList);
        rcFoodList.setAdapter(foodListAdapter);
        GetFoodList();

    }

    private void GetFoodList() {
        int categoryId = getIntent().getIntExtra("CATEGORY_ID", 0);
        String cateName = getIntent().getStringExtra("CATEGORY_NAME");
        this.cateName.setText(cateName);
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getFoodCategory(categoryId).enqueue(new Callback<List<FoodReview>>() {
            @Override
            public void onResponse(Call<List<FoodReview>> call, Response<List<FoodReview>> response) {
                if (response.isSuccessful()) {
                    foodList = response.body();
                    foodListAdapter.updateData(foodList);
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<FoodReview>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
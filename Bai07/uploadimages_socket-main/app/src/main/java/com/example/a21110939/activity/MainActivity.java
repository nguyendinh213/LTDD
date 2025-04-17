package com.example.a21110939.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.example.a21110939.R;
import com.example.a21110939.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> rowArrayList = new ArrayList<>();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        populateData();
        initAdapter();
        initScrollListener();
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(!isLoading) {
                    if(linearLayoutManager != null && linearLayoutManager.findFirstCompletelyVisibleItemPosition() == rowArrayList.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        rowArrayList.add(null);
        recyclerViewAdapter.notifyItemInserted(rowArrayList.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowArrayList.remove(rowArrayList.size() - 1);
                int scrollPosition = rowArrayList.size();
                recyclerViewAdapter.notifyItemRemoved(scrollPosition);
                int currSize = scrollPosition;
                int nextLimit = currSize + 10;

                while (currSize - 1 < nextLimit) {
                    rowArrayList.add("Item" + currSize);
                    currSize++;
                }
                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void initAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(rowArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void populateData() {
        int i = 0;
        while (i < 10) {
            rowArrayList.add("Item" + i);
            i++;
        }
    }
}
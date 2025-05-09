package com.ldt.a22110442bai5_rcv;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_CustomAdapterRecycleView extends AppCompatActivity {
    private RecyclerView rvSongs;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_custom_adapter_recycle_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("1", "Bài hát 1", "Lời bài hát 1", "Ca sĩ 1"));
        mSongs.add(new SongModel("2", "Bài hát 2", "Lời bài hát 2", "Ca sĩ 2"));
        mSongs.add(new SongModel("3", "Bài hát 3", "Lời bài hát 3", "Ca sĩ 3"));
        mSongs.add(new SongModel("4", "Bài hát 4", "Lời bài hát 4", "Ca sĩ 4"));
        mSongs.add(new SongModel("5", "Bài hát 5", "Lời bài hát 5", "Ca sĩ 5"));
        mSongs.add(new SongModel("6", "Bài hát 6", "Lời bài hát 6", "Ca sĩ 6"));
        mSongs.add(new SongModel("7", "Bài hát 7", "Lời bài hát 7", "Ca sĩ 7"));
        mSongs.add(new SongModel("8", "Bài hát 8", "Lời bài hát 8", "Ca sĩ 8"));
        mSongs.add(new SongModel("9", "Bài hát 9", "Lời bài hát 9", "Ca sĩ 9"));
        mSongs.add(new SongModel("10", "Bài hát 10", "Lời bài hát 10", "Ca sĩ 10"));
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng LinearLayoutManager hoặc GridLayoutManager
        SongAdapter adapter = new SongAdapter(this,mSongs); // data là danh sách dữ liệu cần hiển thị
        recyclerView.setAdapter(adapter);


    }
}
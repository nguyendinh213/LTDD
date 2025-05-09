package com.ldt.bai6tuan7;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ldt.bai6tuan7.databinding.ActivityHomeBinding;
import com.ldt.bai6tuan7.databinding.DemoUserBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHome(this);
        title.set("Danh sách người dùng");
        setData();
        listUserAdapter.setOnItemClickListener(this::itemClick);
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);
    }
    private void setData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User("Đình " + i, "Trí " + i));
        }
        listUserAdapter = new ListUserAdapter(userList);
    }

    private void itemClick(User user){
        Toast.makeText(this, "bạn vừa click" + user.getFirstName(), Toast.LENGTH_SHORT).show();
    }

}
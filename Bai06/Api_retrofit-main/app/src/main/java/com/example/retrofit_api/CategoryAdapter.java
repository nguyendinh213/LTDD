
package com.example.retrofit_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<Category> array;

    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, null);
        MyViewHolder holder = new MyViewHolder(view);// Sửa "R.Layout" thành "R.layout" và "root nulL" thành "parent, false"
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView tenSp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_cate);
            tenSp = itemView.findViewById(R.id.tvNameCategory);

            // Bắt sự kiện cho item trong MyViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý khi nhấp vào Item trên category
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) { // Kiểm tra vị trí hợp lệ
                        Category selectedCategory = array.get(position);
                        Toast.makeText(context, "Bạn đã chọn category: " + selectedCategory.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = array.get(position);
        holder.tenSp.setText(category.getName());

        // Load ảnh với Glide
        Glide.with(context)
                .load(category.getImages()) // Giả sử `getImages()` trả về URL hoặc URI của ảnh
                .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }
}
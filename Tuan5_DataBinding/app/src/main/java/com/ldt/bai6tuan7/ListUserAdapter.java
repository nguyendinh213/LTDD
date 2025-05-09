package com.ldt.bai6tuan7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;


import com.ldt.bai6tuan7.databinding.ItemListUserBinding;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {
    private List<User> userList;
    private OnItemClickListener onItemClickListener;

    public ListUserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListUserBinding itemListUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_user, parent, false);
        return new MyViewHolder(itemListUserBinding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(userList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ObservableField<String> stt = new ObservableField<>();
        public ObservableField<String> firstName = new ObservableField<>();
        public ObservableField<String> lastName = new ObservableField<>();
        private ItemListUserBinding itemListUserBinding;
        private OnItemClickListener onItemClickListener;
        private User user;

        public MyViewHolder(ItemListUserBinding itemView, OnItemClickListener onItemClickListener) {
            super(itemView.getRoot());
            this.itemListUserBinding = itemView;
            this.onItemClickListener = onItemClickListener;
            itemView.getRoot().setOnClickListener(this);
        }

        public void setBinding(User user, int position) {
            if (itemListUserBinding.getViewHolder() == null) {
                itemListUserBinding.setViewHolder(this);
            }
            this.user = user;
            stt.set(String.valueOf(position));
            firstName.set(user.getFirstName());
            lastName.set(user.getLastName());
        }

        public void onClick(View view) {
            this.onItemClickListener.itemClick(user);
        }
    }

    public interface OnItemClickListener {
        void itemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

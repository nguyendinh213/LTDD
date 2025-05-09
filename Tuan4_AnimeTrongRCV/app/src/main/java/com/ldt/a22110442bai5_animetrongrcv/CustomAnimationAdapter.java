package com.ldt.a22110442bai5_animetrongrcv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAnimationAdapter extends RecyclerView.Adapter<CustomAnimationAdapter.ViewHolder>{
    private List<String> mDatas;
    public CustomAnimationAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li=LayoutInflater.from(parent.getContext());
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animation, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item= mDatas.get(position);
        holder.tvItem.setText(item);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public  void addItem(String item){
        mDatas.add(item);
        notifyItemInserted(mDatas.size()-1);
    }
    public  void addItem(int position, String item){
        mDatas.add(position,item);
        notifyItemInserted(position);
    }
    public void removeItem(int adapterPosition, String item){
        int index=mDatas.indexOf(item);
        if(index<0){
            return;
        }
        mDatas.remove(index);
        notifyItemRemoved(index);
    }
    public void removeItem(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    public void replaceItem(String item, String newItem){
        int index=mDatas.indexOf(item);
        if(index<0){
            return;
        }
        mDatas.remove(index);
        mDatas.add(index, newItem);
        notifyItemChanged(index);
    }
    public void replaceItem(int position, String item){
        mDatas.remove(position);
        mDatas.add(position, item);
        notifyItemChanged(position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Removed Amimation ", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceItem(getAdapterPosition(), " item changed");
                    Toast.makeText(v.getContext(), "Add item " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   removeItem(getAdapterPosition(), " item changed");
                    Toast.makeText(v.getContext(), "Changed Animation " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

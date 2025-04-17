package com.example.bai4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bai4.R;
import com.example.bai4.model.ItemModel;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<ItemModel> items;

    public ListViewAdapter(Context context, ArrayList<ItemModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textView);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        ItemModel item = items.get(position);
        textView.setText(item.getName());
        imageView.setImageResource(item.getImageResource());

        return convertView;
    }
}

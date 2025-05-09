package com.ldt.bai6tuan7_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(Context context, int layout, List<NotesModel> noteList) {
        this.context = (MainActivity) context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.textViewNote = convertView.findViewById(R.id.textViewNameNode);
            viewHolder.imageViewDelete = convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NotesModel notes = noteList.get(i);
        viewHolder.textViewNote.setText(notes.getNameNote());
        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogCapNhat(notes.getNameNote(), notes.getIdNote());
            }
        });
        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(notes.getNameNote(), notes.getIdNote());
            }
        });
        return convertView;
    }


    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewDelete, imageViewEdit;
    }

}

package com.ldt.a22110442bai5_rcv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private static final String TAG = "SongAdapter";
    private List<SongModel> mSongs;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SongAdapter(Context context, List<SongModel> datas) {
        mContext = context;
//        mLayoutInflater= LayoutInflater.from(context);
        mSongs = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_song, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel song = mSongs.get(position);
        holder.tvCode.setText(song.getmCode());
        holder.tvTitle.setText(song.getmTitle());
        holder.tvArtist.setText(song.getmArtist());
        holder.tvLyric.setText(song.getmLyric());
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvLyric;
        private TextView tvArtist;
        //thiết lập sự kiện


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLyric = itemView.findViewById(R.id.tv_lyric);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            //thiết lập sự kiện
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SongModel song = mSongs.get(getAdapterPosition());
                    Toast.makeText(mContext, song.getmTitle(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


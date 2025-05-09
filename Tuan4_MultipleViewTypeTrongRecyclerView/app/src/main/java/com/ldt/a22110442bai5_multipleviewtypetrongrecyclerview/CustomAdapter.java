package com.ldt.a22110442bai5_multipleviewtypetrongrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mObjects;
    private static final int TEXT = 0;
    private static final int IMAGE = 1;
    private static final int USER = 2;

    public CustomAdapter(Context context, List<Object> Object) {
        mContext = context;
        mObjects = Object;
    }
    public class TextViewHoder extends RecyclerView.ViewHolder{
        private TextView tvText;

        public TextView getTvText() {
            return tvText;
        }

        public void setTvText(TextView tvText) {
            this.tvText = tvText;
        }

        public TextViewHoder(@NonNull View itemView) {
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mObjects.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImage;

        public ImageView getIvImage() {
            return ivImage;
        }

        public void setIvImage(ImageView ivImage) {
            this.ivImage = ivImage;
        }

        public ImageViewHolder(View itemView) {
            super(itemView);
            this.ivImage = (ImageView) itemView.findViewById(R.id.imv_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mObjects.get(getAdapterPosition()).toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvAddress;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, tvName.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
    @Override
    public int getItemViewType(int position) {
        if (mObjects.get(position) instanceof String)
            return TEXT;
        else if (mObjects.get(position) instanceof Integer)
            return IMAGE;
        else
            return USER;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li= LayoutInflater.from(mContext);
        switch (viewType) {
            case TEXT:
                View itemView0 = li.inflate(R.layout.row_text, parent, false);
                return new TextViewHoder(itemView0);
            case IMAGE:
                View itemView1 = li.inflate(R.layout.row_image, parent, false);
                return new ImageViewHolder(itemView1);
            case USER:
               View itemView2 = li.inflate(R.layout.row_user, parent, false);
                return new UserViewHolder(itemView2);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TEXT:
                TextViewHoder tvh = (TextViewHoder) holder;
                tvh.getTvText().setText((String) mObjects.get(position));

                break;
            case IMAGE:
                ImageViewHolder ivh = (ImageViewHolder) holder;
                ivh.getIvImage().setImageResource((Integer) mObjects.get(position));

                break;
            case USER:
                UserModel userModel = (UserModel) mObjects.get(position);
                UserViewHolder uvh = (UserViewHolder) holder;
                uvh.tvName.setText(userModel.getName());
                uvh.tvAddress.setText(userModel.getAddress());

                break;
        }
    }



    @Override
    public int getItemCount() {
        return mObjects.size();
    }

}

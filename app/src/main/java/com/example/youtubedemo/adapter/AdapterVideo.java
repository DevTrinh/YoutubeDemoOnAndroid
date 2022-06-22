package com.example.youtubedemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.R;
import com.example.youtubedemo.item.ItemVideoYoutube;

import java.util.List;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.VideoViewHolder>{

    List<ItemVideoYoutube> list;

    public AdapterVideo(List<ItemVideoYoutube> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_video_youtube, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivThumbnail;
        private TextView tvTitle;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mapping(itemView);

        }

        public void mapping(@NonNull View itemView){
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail_youtube);
            tvTitle = itemView.findViewById(R.id.tv_title_video);
        }
    }
}

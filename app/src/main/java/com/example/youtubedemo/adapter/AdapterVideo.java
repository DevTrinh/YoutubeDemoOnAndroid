package com.example.youtubedemo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.ListVideoYoutube;
import com.example.youtubedemo.R;
import com.example.youtubedemo.interfacee.InterfaceClickWithPosition;
import com.example.youtubedemo.item.ItemVideoYoutube;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.VideoViewHolder>{
    InterfaceClickWithPosition interfaceClickWithPosition;
    List<ItemVideoYoutube> list;
    public AdapterVideo(List<ItemVideoYoutube> list, InterfaceClickWithPosition interfaceClickWithPosition) {
        this.list = list;
        this.interfaceClickWithPosition = interfaceClickWithPosition;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_video_youtube, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemVideoYoutube itemVideoYoutube = list.get(position);
        if (itemVideoYoutube == null){
            return;
        }
        holder.tvTitleVideo.setText(itemVideoYoutube.getTitleVideo());
        holder.tvTitleChannel.setText(itemVideoYoutube.getTitleChannel());
        Picasso.get().load(itemVideoYoutube.getThumbnail()).into(holder.ivThumbnail);
        holder.clItemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickWithPosition.onClickWithPosition(position);
            }
        });
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
        private TextView tvTitleVideo, tvTitleChannel;
        private ConstraintLayout clItemVideo;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail_youtube);
            tvTitleVideo = itemView.findViewById(R.id.tv_title_video);
            tvTitleChannel = itemView.findViewById(R.id.tv_title_channel);
            clItemVideo = itemView.findViewById(R.id.cl_item_video);
        }
    }
}

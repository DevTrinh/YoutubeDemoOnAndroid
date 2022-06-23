package com.example.youtubedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.youtubedemo.adapter.AdapterVideo;
import com.example.youtubedemo.interfacee.InterfaceClickWithPosition;
import com.example.youtubedemo.interfacee.InterfaceValueDefault;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, InterfaceValueDefault {

    RecyclerView rvListVideo;
    YouTubePlayerView ypPlayerVideo;
    ListVideoYoutube listVideoYoutube;
    AdapterVideo adapterVideo = listVideoYoutube.adapterVideo;
    YouTubePlayer youTubePlayerChange;
    String ID_PLAY_VIDEO;
    int REQUEST_VIDEO = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        mapping();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListVideo.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvListVideo.addItemDecoration(itemDecoration);

        Intent intent = getIntent();
        ID_PLAY_VIDEO = intent.getStringExtra("ValueIdVideo");
        ypPlayerVideo.initialize(API_KEY,this);

        adapterVideo = new AdapterVideo(listVideoYoutube.listItemVideo, new InterfaceClickWithPosition() {
            @Override
            public void onClickWithPosition(int position) {
                ID_PLAY_VIDEO = listVideoYoutube.listItemVideo.get(position).getId();
                youTubePlayerChange.loadVideo(ID_PLAY_VIDEO);
            }
        });
        rvListVideo.setAdapter(adapterVideo);
    }

    public void mapping(){
        rvListVideo = findViewById(R.id.rv_list_video);
        ypPlayerVideo = findViewById(R.id.yp_view);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, @NonNull YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayerChange = youTubePlayer;
        youTubePlayerChange.loadVideo(ID_PLAY_VIDEO);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, @NonNull YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_VIDEO);
        } else {
            Toast.makeText(this, "ERRORRR !", Toast.LENGTH_SHORT).show();
        }
    }
}
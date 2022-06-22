package com.example.youtubedemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youtubedemo.interfacee.InterfaceValueDefault;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends YouTubeBaseActivity
//    STEP 3;
        implements InterfaceValueDefault, YouTubePlayer.OnInitializedListener {
    //STEP 1
    YouTubePlayerView ypView;
    String ID_VIDEO_DEMO_PLAY = "RgKAFK5djSk";
    int REQUEST_VIDEO = 123;
    String urlThumbnail = " ";
    String titleVideo = "";
    String idVideo = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        ypView.initialize(API_KEY, this);
        getJsonYoutube();
    }

    //STEP 2;
    public void mapping() {
        ypView = findViewById(R.id.yp_player_view);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        @NonNull YouTubePlayer youTubePlayer, boolean b) {
//        ID VIDEO PLAY = ...
        youTubePlayer.cueVideo(ID_VIDEO_DEMO_PLAY);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        @NonNull YouTubeInitializationResult youTubeInitializationResult) {
//        STEP 4;
//        CHECK FOR ERROR FROM USER
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_VIDEO);
        } else {
            Toast.makeText(this, "ERRORRR !", Toast.LENGTH_SHORT).show();
        }
    }

    //    STEP 5;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO) {
            ypView.initialize(API_KEY, this);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void getJsonYoutube() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, InterfaceValueDefault.URL_JSON_API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                titleVideo = jsonSnippet.getString("title");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                urlThumbnail = jsonMedium.getString("url");
//                                RESOURCE ID
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                                idVideo = (String) jsonResourceId.get("videoId");
                                Toast.makeText(MainActivity.this, idVideo+"", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}